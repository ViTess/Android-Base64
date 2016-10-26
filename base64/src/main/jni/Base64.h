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

/*
 * Class:     vite_base64_Base64
 * Method:    encodeByByte
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_encodeByByte
        (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     vite_base64_Base64
 * Method:    encodeByByte2String
 * Signature: ([B)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_vite_base64_Base64_encodeByByte2String
        (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     vite_base64_Base64
 * Method:    encodeByString
 * Signature: (Ljava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_vite_base64_Base64_encodeByString
        (JNIEnv *, jclass, jstring);

/*
 * Class:     vite_base64_Base64
 * Method:    encodeByString2String
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_vite_base64_Base64_encodeByString2String
        (JNIEnv *, jclass, jstring);

#ifdef __cplusplus
}
#endif
#endif
