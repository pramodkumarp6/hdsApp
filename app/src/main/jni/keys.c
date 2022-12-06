#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_pramod_hdsapp_api_AuthIntercepter_getApi(JNIEnv *env, jobject instance) {

    return (*env)-> NewStringUTF(env, "1234567");
}