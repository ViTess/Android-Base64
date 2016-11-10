//
// Created by trs on 16-10-24.
//
#include "Base64.h"

/**
 * 判断是否为空
 */
bool isByteArrayNull(JNIEnv *env, jbyteArray byteArray) {
    if (byteArray == NULL) {
        jclass npe = env->FindClass("java/lang/NullPointerException");
        env->ThrowNew(npe, "byte[] is null!");
        return true;
    }
    return false;
}

/**
 * 判断参数是否异常
 */
bool isOutOfRange(JNIEnv *env, int realLength, int offset, int length) {
    if (offset < 0) {
        jclass lae = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(lae, "offset was a minus!");
        return true;
    }
    if (length < 0 && LEN_DEFAULT != length) {
        jclass lae = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(lae, "length was a minus!");
        return true;
    }
    if (length > realLength) {
        jclass lae = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(lae, "array length is less than length!");
        return true;
    }
    if (offset > realLength) {
        jclass lae = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(lae, "offset is more than array length!");
        return true;
    }
    if ((offset + length) > realLength && LEN_DEFAULT != length) {
        jclass lae = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(lae, "offset and length is more than array length!");
        return true;
    }
    return false;
}

/**
 * 计算、获取编码长度
 */
int getEncodeLength(int realLength, int length, Flags *flags) {
    //编码后的长度计算：3×8=4×6->4×8

    int tempLength = realLength;
    if (length != LEN_DEFAULT)
        tempLength = length;

    int encodeLength = 0;
    if (realLength > 0) {
        int remainder = tempLength % 3;

        encodeLength = (remainder == 0 ? ((tempLength / 3) << 2) : (((tempLength / 3) + 1) << 2));
        if (flags->isWrap) {
            if (!flags->isCRLF) //编码后每76个字符插入'\n'换行
                encodeLength = encodeLength + 1 + encodeLength / LINE_CHARS;
            else //编码后每76个字符插入'\r\n'换行
                encodeLength = encodeLength + (1 + encodeLength / LINE_CHARS) * 2;
        }
        //没有padding，不需要在后面补'='
        if (!flags->isPadding && remainder > 0)
            encodeLength -= (3 - remainder);
    }

    return encodeLength;
}

/**
 * 计算解码后的数据长度
 */
int getDecodeLength(const char *data, int realLength, int offset, int length, Flags *flags) {

    char log[256];

    int decodeLength = 0;
    int tempLength = realLength;
    if (length != LEN_DEFAULT)
        tempLength = length;

    sprintf(log, "tempLength:%d", tempLength);
    LOGV(log);

    //当数据应该有'='存在时，数据长度少于4，则无法被解码
    //反之，不应该存在'='时，数据长度少于2，则同上
    if (flags->isPadding && tempLength < 4) {
        return -1;
    } else if (!flags->isPadding && tempLength < 2) {
        return -2;
    }

    //计算数据尾部的换行符占位数（若存在）
    int endSpace = 0;
    if (length == LEN_DEFAULT || (length + offset == realLength)) {
        if (flags->isCRLF) {
            if (data[realLength - 2] == '\r')
                endSpace = 2;
        } else if (data[realLength - 1] == '\n')
            endSpace = 1;
    }

    sprintf(log, "endSpace:%d", endSpace);
    LOGV(log);

    //计算换行符个数
    int enters = 0;
    if (flags->isWrap) {
        enters = tempLength / (flags->isCRLF ? 78 : 77);
    }

    sprintf(log, "enters:%d", enters);
    LOGV(log);

    tempLength -= endSpace;
    decodeLength = tempLength;

    int t = tempLength - enters;
    tempLength = t >= 0 ? t : tempLength;
    bool isRemainder = false;
    int equals = 0;//记录'='号个数（若存在）
    int remainder = 0;//记录余数（若存在）

    sprintf(log, "tempLength--:%d", tempLength);
    LOGV(log);

    /*
     * 多判断一层，存在padding时，解码数据不足4位且不能被4整除的throw exception，
     * 不存在padding时，最少解码数据是2位，数据不能被4整除时，若余数为1、2，则数据完整可被解码
     */

    if (flags->isPadding) {
        if (tempLength % 4 != 0) {
            return -3;
        }

        if (data[decodeLength - 2] == '=') {
            equals = 2;
            isRemainder = true;
        }
        else if (data[decodeLength - 1] == '=') {
            equals = 1;
            isRemainder = true;
        }

    } else {
        int d = tempLength % 4;
        if (d > 2) {
            return -4;
        } else if (d != 0) {
            remainder = d;
            isRemainder = true;
        }
    }

    if (isRemainder) {
        decodeLength -= enters;
        decodeLength += remainder;
        decodeLength = decodeLength * DECODE_CONST - 3;
        decodeLength += (3 - equals);
    } else {
        decodeLength -= enters;
        decodeLength = decodeLength * DECODE_CONST;
    }
    sprintf(log, "decodeLength--:%d", decodeLength);
    LOGV(log);
    return decodeLength;
}

/**
 * 获取选项值
 */
Flags *getFlags(int flag) {
    Flags *flags = (Flags *) malloc(sizeof(Flags));
    flags->isPadding = !(flag & NO_PADDING);
    flags->isWrap = !(flag & NO_WRAP);
    flags->isCRLF = (flag & CRLF);
    flags->isUrlSafe = (flag & URL_SAFE);

//    char log[256];
//    sprintf(log, "flag:%d", flag);
//    LOGV(log);
//    sprintf(log, "padding:%d , wrap:%d , crlf:%d , safe:%d", flags->isPadding, flags->isWrap, flags->isCRLF, flags->isUrlSafe);
//    LOGV(log);

    return flags;
}

/**
 * 编码
 * data       : byte数组
 * realLength : 数组长度
 * offset     : 偏移量
 * length     : 从偏移量开始截取的长度
 * flag       : 选项
 */
EncodeData *encode(const char *data, int realLength, int offset, int length, int flag) {
    /* 将字符串数据转换成ascii码，再将ascii码变成二进制表示，
     * 将二进制中每3个8位的数据转换成4个6位的数据，
     * 并且在6位的数据前加‘00’，最终变成4个8位，
     * 操作完的4个8位二进制再变成4个十进制数字，并根据数字对应的base64表获取编码，
     * 如果不满足3个8位，则在其后补0直至到3个8位，再分割成4个6位，其中补完的数据用'='表示*/

    /*android的base64库中，默认换行用/n，不用/r/n*/

    /* e.g
     * char          : 's'
     * binary        : 01110011
     * split         : 011100 | 11
     * complete 0    : 011100 | 110000 | 000000 | 000000
     * add 0 to head : 00011100 | 00110000 | 00000000 | 00000000
     * decimal       : 28 | 48 | 64(complete) | 64(complete)
     * encode        : c | w | = | =
     * result        : cw==
     * */

    EncodeData *encodeData = (EncodeData *) malloc(sizeof(EncodeData));
    Flags *flags = getFlags(flag);//根据选项值获取对应选项
    const char *table = flags->isUrlSafe ? ENCODE_WEB_TABLE : ENCODE_TABLE;
    char *result;//输出结果用指针
    char *p;//活动指针，用于循环取数据
    char temp1, temp2, temp3;//辅助取数

    int encodeLength = getEncodeLength(realLength, length, flags);

    result = p = (char *) malloc(encodeLength * sizeof(char));
    int i;
    int count = LINE_GROUPS;

    int len = realLength;
    if (length != LEN_DEFAULT)
        len = length;

    len += offset;

    for (i = offset; i < len; i += 3) {

        bool isSecondCharEmpty = (i + 1 >= len);
        bool isThirdCharEmpty = (i + 2 >= len);

        //每次增长3个，若能进入循环，第一个字节肯定存在
        temp1 = data[i];
        temp2 = isSecondCharEmpty ? 0 : data[i + 1];
        temp3 = isThirdCharEmpty ? 0 : data[i + 2];

        //第一个转码取第一个字节的前6位，再通过0x3F(0011 1111)提取出
        *(p++) = table[(temp1 >> 2) & 0x3F];
        //第二个转码取第一个字节的7、8位(0x3F)和第二个字节的前4位(0x0F)
        *(p++) = table[((temp1 << 4) & 0x3F) + ((temp2 >> 4) & 0x0F)];
        //第三个转码取第二个字节的5、6、7、8位(0x3C)和第三个字节的前2位(0x03)
        //假设只有一个字节，那么必定可以转换出第一、二个转码，所以只需要判断第二个字节是否为空
        //若第二字节为空，则用‘=’（64）号代替
        if (isSecondCharEmpty && flags->isPadding)
            *(p++) = table[64];
        else if (!isSecondCharEmpty)
            *(p++) = table[(((temp2 << 2) & 0x3C) + ((temp3 >> 6) & 0x03))];
        //第四个转码直接取第三个字节的后6位即可
        if (isThirdCharEmpty && flags->isPadding)
            *(p++) = table[64];
        else if (!isThirdCharEmpty)
            *(p++) = table[(temp3 & 0x3F)];

        if (flags->isWrap && (--count) == 0) {
            if (flags->isCRLF) *(p++) = '\r';
            *(p++) = '\n';
            count = LINE_GROUPS;
        }
    }
    if (flags->isWrap) {
        if (flags->isCRLF) *(p++) = '\r';
        *p = '\n';//最后要加结束符号
    }

    encodeData->data = result;
    encodeData->length = encodeLength;
    return encodeData;
}

/**
 * 解码
 * data       : byte数组
 * realLength : 数组长度
 * offset     : 偏移量
 * length     : 从偏移量开始截取的长度
 * flag       : 选项
 */
DecodeData *decode(const char *data, int realLength, int offset, int length, int flag) {

    DecodeData *decodeData = (DecodeData *) malloc(sizeof(DecodeData));
    Flags *flags = getFlags(flag);
    const char *table = flags->isUrlSafe ? DECODE_WEB_TABLE : DECODE_TABLE;
    char *result, *p;
    char temp1, temp2, temp3, temp4;//辅助取数
    char r1, r2, r3;//辅助记录

    int len = realLength;
    if (length != LEN_DEFAULT)
        len = length;

    int decodeLength = getDecodeLength(data, realLength, offset, length, flags);
    if (decodeLength < 0) {
        decodeData->length = decodeLength;
        return decodeData;
    }
    result = p = (char *) malloc(decodeLength * sizeof(char));

    for (int i = offset; i < len; i += 4) {

        temp1 = data[i];
        if (temp1 == 10) {
            i++;
            temp1 = i >= (len + offset) ? 0 : data[i];
        }
        temp2 = i + 1 >= (len + offset) ? 0 : data[i + 1];
        temp3 = i + 2 >= (len + offset) ? 0 : data[i + 2];
        temp4 = i + 3 >= (len + offset) ? 0 : data[i + 3];

        temp1 = table[temp1];
        temp2 = table[temp2];
        temp3 = table[temp3];
        temp4 = table[temp4];

        //第一个解码取第一个字节的第2~8位，第二个字节的第3~4位
        r1 = (temp1 << 2) + (temp2 >> 4);
        //第二个解码取第二个字节的后4位，第三个字节的第2~6位
        r2 = (temp2 << 4) + (temp3 >> 2);
        //第三个解码取第三个字节的后2位，第四个字节的第0~6位
        r3 = (temp3 << 6) + temp4;

        if (r1 > 0)
            *(p++) = r1;
        if (r2 > 0)
            *(p++) = r2;
        if (r3 > 0)
            *(p++) = r3;
    }

    decodeData->data = result;
    decodeData->length = decodeLength;
    return decodeData;
}

JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_nativeEncode
        (JNIEnv *env, jclass clazz, jbyteArray byteArray, jint offset, jint length, jint flag) {
    if (isByteArrayNull(env, byteArray))
        return NULL;
    jbyte *data = env->GetByteArrayElements(byteArray, JNI_FALSE);
    jint realLen = env->GetArrayLength(byteArray);
    if (isOutOfRange(env, realLen, offset, length)) {
        return NULL;
    }

    EncodeData *encodeData = encode((char *) data, realLen, offset, length, flag);
    realLen = encodeData->length;

    env->ReleaseByteArrayElements(byteArray, data, 0);

    jbyteArray result = env->NewByteArray(realLen);
    env->SetByteArrayRegion(result, 0, realLen, (const jbyte *) encodeData->data);
    return result;
}

/**
 * 另一种方法解码，不预先计算解码长度
 */
DecodeData *decode2(const char *data, int realLength, int offset, int length, int flag) {
    DecodeData *decodeData = (DecodeData *) malloc(sizeof(DecodeData));
    Flags *flags = getFlags(flag);
    const char *table = flags->isUrlSafe ? DECODE_WEB_TABLE : DECODE_TABLE;
    char temp1, temp2, temp3, temp4;//辅助取数
    char r1, r2, r3;//辅助记录
    char *p;

    int index = offset;
    int len = realLength;
    if (length != LEN_DEFAULT)
        len = length;
    len += offset;

    decodeData->data = p = (char *) malloc(sizeof(char) * len);
    int decodeLength = 0;
    bool isUndefine = false;//辅助判断是否遭遇情况4
    int undefineCount = 0;

    while (index < len) {
        //循环去除无效字符，直到数据结尾
        while (!isUndefine && index + 4 <= len) {
//            temp1 = data[index];
//            temp2 = data[index + 1];
//            temp3 = data[index + 2];
//            temp4 = data[index + 3];

            temp1 = table[data[index]];
            temp2 = table[data[index + 1]];
            temp3 = table[data[index + 2]];
            temp4 = table[data[index + 3]];

            if (temp1 < 0 || temp2 < 0 || temp3 < 0 || temp4 < 0) {
                isUndefine = true;
                break;
            }

            //第一个解码取第一个字节的第2~8位，第二个字节的第3~4位
            r1 = (temp1 << 2) + (temp2 >> 4);
            //第二个解码取第二个字节的后4位，第三个字节的第2~6位
            r2 = (temp2 << 4) + (temp3 >> 2);
            //第三个解码取第三个字节的后2位，第四个字节的第0~6位
            r3 = (temp3 << 6) + temp4;

            if (r1 > 0) {
                *(p++) = r1;
                decodeLength++;
            }
            if (r2 > 0) {
                *(p++) = r2;
                decodeLength++;
            }
            if (r3 > 0) {
                *(p++) = r3;
                decodeLength++;
            }

            index += 4;
        }

        //从上面while循环跳出来的情况有四种
        //1.正常结束，index==len，此时直接跳出外循环
        //2.指针后面还有数据，但不足4个，len-index>=1&&len-index<=3
        //  2.1 剩余1个：判断是否为无效字符，是则正常跳出外循环；为'='，则异常；否则报异常
        //  2.2 剩余2个：判断是否都为无效字符，是的话则正常；为'=='，则异常;有效字符下，2个字符可解码成一个字符；其余报异常
        //  2.3 剩余3个：都为有效字符时，直接解码成2个字符；都为无效字符时直接输出；存在'='号下都为异常
        //      还有排列情况：xx(-1),(-1)xx,x(-1)x，这种情况去掉无效字符，剩余2个字符解码成一个字符
        //3.存在无效字符，此时index自增再进入循环，直到去除无效字符
        //4.数据中包含无效字符而跳出来，比如x(-1)xx，x(-1)(-1)xx
        if (isUndefine) {
            switch (undefineCount) {
                case 0:
                    temp1 = table[data[index]];
                    if (temp1 >= 0) {
                        undefineCount++;
                        index++;
                        if (index > len) {
                            //TODO 异常
                        }
                    } else if (temp1 == DECODE_EQUALS) {
                        //TODO 异常
                    }
                    break;
                case 1:
                    temp2 = table[data[index]];
                    if (temp2 >= 0) {
                        undefineCount++;
                        index++;
                        if (index > len) {
                            //TODO 异常
                        }
                    } else if (temp2 == DECODE_EQUALS) {
                        //TODO 异常
                    }
                    break;
                case 2:
                    temp3 = table[data[index]];
                    if (temp3 >= 0) {
                        undefineCount++;
                        index++;
                        if (index > len) {
                            //TODO 异常
                        }
                    } else if (temp3 == DECODE_EQUALS) {
                        //TODO 解码temp1和temp2，然后结束
                    } else if (temp3 != DECODE_SKIP) {
                        //TODO 异常
                    }
                    //为无效字符时，不处理
                    break;
                case 3:
                    temp4 = table[data[index]];
                    if (temp4 >= 0) {
                        //TODO 解码

                        undefineCount = 0;
                        isUndefine = false;
                        index++;
                    } else if (temp4 == DECODE_EQUALS) {
                        //TODO 解码
                    } else if (temp4 != DECODE_SKIP) {
                        //TODO 异常
                    }
                    break;
            }
        } else {
            //非undefine出来的
            switch (len - index) {
                case 0:
                    decodeData->length = decodeLength;
                    return decodeData;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default://当做异常
                    break;
            }
        }
    }

    for (int i = offset; i < len; i += 4) {

        //temp为0则表示到了数据尽头
        temp1 = data[i];
        if (temp1 == 10) {
            i++;
            temp1 = i >= len ? 0 : data[i];
        }
        temp2 = i + 1 >= len ? 0 : data[i + 1];
        temp3 = i + 2 >= len ? 0 : data[i + 2];
        temp4 = i + 3 >= len ? 0 : data[i + 3];

//        if (temp1 == 0) {
//            decodeData->length = -1;
//            return decodeData;
//        } else if (temp2 == 0) {
//        } else if (temp3 == 0) {
//
//        } else if (temp4 == 0) {
//
//        }

        //table[0]也是0
        temp1 = table[temp1];
        temp2 = table[temp2];
        temp3 = table[temp3];
        temp4 = table[temp4];

        //第一个解码取第一个字节的第2~8位，第二个字节的第3~4位
        r1 = (temp1 << 2) + (temp2 >> 4);
        //第二个解码取第二个字节的后4位，第三个字节的第2~6位
        r2 = (temp2 << 4) + (temp3 >> 2);
        //第三个解码取第三个字节的后2位，第四个字节的第0~6位
        r3 = (temp3 << 6) + temp4;

        if (r1 > 0) {
            *(p++) = r1;
            decodeLength++;
        }
        if (r2 > 0) {
            *(p++) = r2;
            decodeLength++;
        }
        if (r3 > 0) {
            *(p++) = r3;
            decodeLength++;
        }
    }
    char log[256];
    sprintf(log, "decodeLength:%d", decodeLength);
    LOGV(log);
    sprintf(log, "len:%d", len);
    LOGV(log);
    sprintf(log, "offset:%d", offset);
    LOGV(log);

    decodeData->length = decodeLength;
    return decodeData;
}

JNIEXPORT jstring JNICALL Java_vite_base64_Base64_nativeEncode2String
        (JNIEnv *env, jclass clazz, jbyteArray byteArray, jint offset, jint length, jint flag) {
    if (isByteArrayNull(env, byteArray))
        return NULL;
    jbyte *data = env->GetByteArrayElements(byteArray, JNI_FALSE);
    jint realLen = env->GetArrayLength(byteArray);
    if (isOutOfRange(env, realLen, offset, length)) {
        return NULL;
    }

    EncodeData *encodeData = encode((char *) data, realLen, offset, length, flag);
    realLen = encodeData->length;

    env->ReleaseByteArrayElements(byteArray, data, 0);

    jbyteArray result = env->NewByteArray(realLen);
    env->SetByteArrayRegion(result, 0, realLen, (const jbyte *) encodeData->data);

    jclass strClass = env->FindClass("java/lang/String");
    jmethodID strInitId = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jstring str = env->NewStringUTF("utf-8");

    return (jstring) env->NewObject(strClass, strInitId, result, str);
}

JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_nativeDecode
        (JNIEnv *env, jclass clazz, jbyteArray byteArray, jint offset, jint length, jint flag) {
    if (isByteArrayNull(env, byteArray))
        return NULL;
    jbyte *data = env->GetByteArrayElements(byteArray, JNI_FALSE);
    jint realLen = env->GetArrayLength(byteArray);
    if (isOutOfRange(env, realLen, offset, length)) {
        return NULL;
    }

    DecodeData *decodeData = decode2((const char *) data, realLen, offset, length, flag);
    if (decodeData->length == -1) {
        jclass lae = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(lae, "Data can't be decoded!");
        return NULL;
    }
    realLen = decodeData->length;

    env->ReleaseByteArrayElements(byteArray, data, 0);

    jbyteArray result = env->NewByteArray(realLen);
    env->SetByteArrayRegion(result, 0, realLen, (const jbyte *) decodeData->data);
    return result;
}