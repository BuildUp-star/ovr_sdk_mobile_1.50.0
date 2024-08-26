# Native VR Sample code and Setups

## Environment setup
Please follow [the link](https://developer.oculus.com/documentation/native/android/mobile-studio-setup-android/).

## Changes to build samples successfully
1, in cflag.mk, I have to comment out "LOCAL_CFLAGS	+= -Werror			# error on warnings"

2, in VrApp.gradle, 
```
runApk(device, new File(output.processManifest.manifestOutputDirectory.get().asFile, "AndroidManifest.xml")) 
```
is changed to 
```
runApk(device, new File(output.processManifest.outputs.files.singleFile, "AndroidManifest.xml"))
```
3, in Appl.cpp
`ALooper_pollAll` is replaced by `ALooper_pollOnce`

4, Added following to build.gradle in VrCubeWorld_Framework
```
buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:8.1.1'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
```

## Build tools' version
SDK: 26

NDK: ndkVersion "18.1.5063045". [Download r18b here](https://github.com/android/ndk/wiki/Unsupported-Downloads)

## How to build

I am not an Android Studio expert and failed to build the projects on Android. However, I successfully build it by running:
`python build.py` in `ovr_sdk_mobile_1.50.0\VrSamples\VrCubeWorld_Framework\Projects\Android`
