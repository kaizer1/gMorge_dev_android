

cmake_minimum_required(VERSION 3.22.1)


project("gmorge")

add_subdirectory(SDL2-2.30.3)
add_subdirectory(gmorge/ggmorse)
add_subdirectory(gmorge/ggmorse/examples/third-party/imgueNew/imgui/cmakeL) # was imgui

set(GGMORSE_SUPPORT_SDL2 1)

add_library(${CMAKE_PROJECT_NAME} SHARED

        src/main/cpp/native-lib.cpp
        src/main/cpp/common.cpp
        src/main/cpp/interface.cpp
        src/main/cpp/interface-emscripten.cpp
        src/main/cpp/interface-unix.cpp
        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c
        gmorge/ggmorse/examples/ggmorse-common-sdl2.cpp
        gmorge/ggmorse/examples/ggmorse-common.cpp
)


set(CMAKE_SHARED_LINKER_FLAGS
  "${CMAKE_SHARED_LINKER_FLAGS} -u ANativeActivity_onCreate"
)


target_include_directories(${CMAKE_PROJECT_NAME} PRIVATE
   gmorge/ggmorse/include
   gmorge/ggmorse/examples
   #gmorge/ggmorse/examples/third-party/imgui/
   gmorge/ggmorse/examples/third-party
   gmorge/ggmorse/examples/third-party/imgueNew
   gmorge/ggmorse/examples/third-party/imgueNew/imgui
   gmorge/ggmorse/examples/third-party/imgueNew/imgui/backends
   src/main/cpp/
   SDL2-2.30.3/include
   ${ANDROID_NDK}/sources/android/native_app_glue
   gmorge/ggmorse/examples
 )


target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        ggmorse
        ImGuiELos
        GLESv3
        SDL2::SDL2-static
        EGL
        log)