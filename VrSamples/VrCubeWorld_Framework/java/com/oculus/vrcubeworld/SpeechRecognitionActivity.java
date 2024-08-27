package com.oculus.sdk.vrcubeworldfw;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import java.util.ArrayList;


public class SpeechRecognitionActivity extends Activity {


    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");


        // Set up the RecognitionListener
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                // You can add logging here if needed
            }


            @Override
            public void onBeginningOfSpeech() {
                // You can add logging here if needed
            }


            @Override
            public void onRmsChanged(float rmsdB) {
                // Handle volume changes during speech recognition (optional)
            }


            @Override
            public void onBufferReceived(byte[] buffer) {
                // Handle audio buffer (optional)
            }


            @Override
            public void onEndOfSpeech() {
                // You can add logging here if needed
            }


            @Override
            public void onError(int error) {
                // Handle errors here
            }


            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String recognizedText = matches.get(0);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("recognizedText", recognizedText);
                    setResult(Activity.RESULT_OK, resultIntent);
                } else {
                    setResult(Activity.RESULT_CANCELED);
                }
                finish(); // Close the activity when done
            }


            @Override
            public void onPartialResults(Bundle partialResults) {
                // Handle partial recognition results if needed
            }


            @Override
            public void onEvent(int eventType, Bundle params) {
                // Handle specific events if needed
            }
        });


        // Start listening for speech
        speechRecognizer.startListening(speechRecognizerIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
