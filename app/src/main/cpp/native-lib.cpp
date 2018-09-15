#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_ir_laitec_kotlinsample_Apps_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
