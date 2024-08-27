// Copyright (c) Facebook Technologies, LLC and its affiliates. All Rights reserved.
package com.oculus.sdk.vrcubeworldfw;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;


/**
 * When using NativeActivity, we currently need to handle loading of dependent shared libraries
 * manually before a shared library that depends on them is loaded, since there is not currently a
 * way to specify a shared library dependency for NativeActivity via the manifest meta-data.
 *
 * <p>The simplest method for doing so is to subclass NativeActivity with an empty activity that
 * calls System.loadLibrary on the dependent libraries, which is unfortunate when the goal is to
 * write a pure native C/C++ only Android activity.
 *
 * <p>A native-code only solution is to load the dependent libraries dynamically using dlopen().
 * However, there are a few considerations, see:
 * https://groups.google.com/forum/#!msg/android-ndk/l2E2qh17Q6I/wj6s_6HSjaYJ
 *
 * <p>1. Only call dlopen() if you're sure it will succeed as the bionic dynamic linker will
 * remember if dlopen failed and will not re-try a dlopen on the same lib a second time.
 *
 * <p>2. Must remember what libraries have already been loaded to avoid infinitely looping when
 * libraries have circular dependencies.
 */
public class MainActivity extends android.app.NativeActivity {
  static {
    System.loadLibrary("vrapi");
    System.loadLibrary("vrcubeworldfw");
  }
  private static final int SPEECH_REQUEST_CODE = 100;


  public void startSpeechRecognition() {
    Intent intent = new Intent(this, SpeechRecognitionActivity.class);
    startActivityForResult(intent, SPEECH_REQUEST_CODE);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
      String recognizedText = data.getStringExtra("recognizedText");
      // Handle the recognized text in your native activity
    }
  }

}
