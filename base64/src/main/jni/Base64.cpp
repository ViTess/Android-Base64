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

    for (i = offset; i < len; i += 3) {

        bool isSecondCharEmpty = (i + 1 >= (len + offset));
        bool isThirdCharEmpty = (i + 2 >= (len + offset));

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