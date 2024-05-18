//
// Created by loskutnikov on 08.05.2024.
//

#pragma once

//#include "ggmorse-common-sdl2.h"

#include <thread>
#include <vector>

std::thread initMainAndRunCore();
//
void initMain();
void updateCore();
void renderMain();
void deinitMain();
