//
// Created by trs on 16-10-24.
//
#include <stdio.h>
#include <string.h>
#include <jni.h>
#include <android/log.h>
/* Header for class vite_base64_Base64 */

#ifndef BASE64_BASE64_H
#define BASE64_BASE64_H
#ifdef __cplusplus
extern "C" {
#endif

#define LOG_TAG "vite.Base64"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO ,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR ,LOG_TAG,__VA_ARGS__)
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE ,LOG_TAG,__VA_ARGS__)

/* 每76个字符换行，每次解码生成4个字符，生成19组数组后即可换行 */
#define LINE_GROUPS 19
#define LINE_CHARS 76

typedef struct {
    int length;
    char *data;
} EncodeData;

/*编码表*/
static char ENCODE_TABLE[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                              'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                              'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                              '8', '9', '+', '/', '='};

/*
 * Class:     vite_base64_Base64
 * Method:    encode
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_encode
        (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     vite_base64_Base64
 * Method:    encode2String
 * Signature: ([B)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_vite_base64_Base64_encode2String
        (JNIEnv *, jclass, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
