//
// Created by trs on 16-10-24.
//
#include <Base64.h>

/*编码表*/
static char ENCODE_TABLE[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                              'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                              'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                              '8', '9', '+', '/', '='};

/**
 * 编码
 * data   : byte数组
 * length : 数组长度
 */
const char *encode(const char *data, int length) {
    /* 将字符串数据转换成ascii码，再将ascii码变成二进制表示，
     * 将二进制中每3个8位的数据转换成4个6位的数据，
     * 并且在6位的数据前加‘00’，最终变成4个8位，
     * 操作完的4个8位二进制再变成4个十进制数字，并根据数字对应的base64表获取编码，
     * 如果不满足3个8位，则在其后补0直至到3个8位，再分割成4个6位，其中补完的数据用'='表示*/

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

    const char *result;//输出结果用指针
    char *p;//活动指针，用于循环取数据
    char temp1, temp2, temp3;//辅助取数
    //编码后的长度计算：3×8=4×6->4×8，即 y=((x/3)+1)*4
    int encodeLength = ((length / 3) + 1) << 2;

    result = p = (char *) malloc(encodeLength * sizeof(char));
    int i;
    for (i = 0; i < length; i += 3) {
        //每次增长3个，若能进入循环，第一个字节肯定存在
        temp1 = data[i];
        temp2 = i + 1 >= length ? 0 : data[i + 1];
        temp3 = i + 2 >= length ? 0 : data[i + 2];

        //第一个转码取第一个字节的前6位，再通过0x3F(0011 1111)提取出
        *p = ENCODE_TABLE[(temp1 >> 2) & 0x3F];
        //第二个转码取第一个字节的7、8位(0x3F)和第二个字节的前4位(0x0F)
        *(p + 1) = ENCODE_TABLE[((temp1 << 4) & 0x3F) + ((temp2 >> 4) & 0x0F)];
        //第三个转码取第二个字节的5、6、7、8位(0x3C)和第三个字节的前2位(0x03)
        //假设只有一个字节，那么必定可以转换出第一、二个转码，所以只需要判断第二个字节是否为空
        //若第二字节为空，则用‘=’（64）号代替
        *(p + 2) = ENCODE_TABLE[temp2 == 0 ? 64 : (((temp2 << 2) & 0x3C) + ((temp3 >> 6) & 0x03))];
        //第四个转码直接取第三个字节的后6位即可
        *(p + 3) = ENCODE_TABLE[temp3 == 0 ? 64 : (temp3 & 0x3F)];
        p = p + 4;
    }
    *p = '\0';//最后要加结束符号，否则输出结果有误
    return result;
}

JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_encodeByByte(JNIEnv *env, jclass clazz, jbyteArray byteArray) {
    jbyte *data = env->GetByteArrayElements(byteArray, JNI_FALSE);
    jint len = env->GetArrayLength(byteArray);
    const char *buffer = encode((char *) data, len);
    len = strlen(buffer);
    env->ReleaseByteArrayElements(byteArray, data, 0);

    jbyteArray result = env->NewByteArray(len);
    env->SetByteArrayRegion(result, 0, len, (const jbyte *) buffer);
    return result;
}

JNIEXPORT jstring JNICALL Java_vite_base64_Base64_encodeByByte2String(JNIEnv *env, jclass clazz, jbyteArray byteArray) {
    jbyte *data = env->GetByteArrayElements(byteArray, JNI_FALSE);
    jint len = env->GetArrayLength(byteArray);
    const char *buffer = encode((char *) data, len);
    len = strlen(buffer);
    env->ReleaseByteArrayElements(byteArray, data, 0);

    jbyteArray result = env->NewByteArray(len);
    env->SetByteArrayRegion(result, 0, len, (const jbyte *) buffer);

    jclass strClass = env->FindClass("java/lang/String");
    jmethodID strInitId = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jstring str = env->NewStringUTF("utf-8");

    return (jstring) env->NewObject(strClass, strInitId, result, str);
}

JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_encodeByString(JNIEnv *env, jclass clazz, jstring str) {
    const char *data = env->GetStringUTFChars(str, JNI_FALSE);
    jsize len = env->GetStringLength(str);
    const char *buffer = encode(data, len);
    len = strlen(buffer);
    env->ReleaseStringUTFChars(str, data);

    jbyteArray result = env->NewByteArray(len);
    env->SetByteArrayRegion(result, 0, len, (const jbyte *) buffer);
    return result;
}

JNIEXPORT jstring JNICALL Java_vite_base64_Base64_encodeByString2String(JNIEnv *env, jclass clazz, jstring str) {
    const char *data = env->GetStringUTFChars(str, JNI_FALSE);
    jsize len = env->GetStringLength(str);
    const char *buffer = encode(data, len);
    len = strlen(buffer);
    env->ReleaseStringUTFChars(str, data);

    jbyteArray result = env->NewByteArray(len);
    env->SetByteArrayRegion(result, 0, len, (const jbyte *) buffer);

    jclass strClass = env->FindClass("java/lang/String");
    jmethodID strInitId = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jstring strResult = env->NewStringUTF("utf-8");

    return (jstring) env->NewObject(strClass, strInitId, result, strResult);
}

