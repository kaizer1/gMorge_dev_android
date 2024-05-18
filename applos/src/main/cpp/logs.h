//
// Created by loskutnikov on 16.05.2024.
//

#ifndef GMORGE_LOGS_H
#define GMORGE_LOGS_H


#include <android/log.h>
#define  logRun(...) ((void)__android_log_print(ANDROID_LOG_INFO, "RunGame", __VA_ARGS__))


#endif //GMORGE_LOGS_H
