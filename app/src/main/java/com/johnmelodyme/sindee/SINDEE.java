package com.johnmelodyme.sindee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @AUTHOR : JOHN MELODY MELISSA
 * @COPYRIGHT : 2020
 * @PROJECT_NAME: SinDee, {Is a Virtual Assistant Name after My GF}
 */

public class SINDEE extends AppCompatActivity {
    ImageView SINDEE_VIEW;
    TextToSpeech textToSpeech;
    SpeechRecognizer speechRecognizer;

    public void onStart(){
        super.onStart();
        INIT();
    }

    // onStart Declaration:
    private void INIT() {
        SINDEE_VIEW = findViewById(R.id.initial);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        START_TEXT_TO_SPEECH();
        START_SPEECH_RECOGNICTION();

        // FUNCTION FOR ON PRESSED:
    }

    private void START_SPEECH_RECOGNICTION() {
        if (SpeechRecognizer.isRecognitionAvailable(SINDEE.this)){
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(SINDEE.this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    ArrayList<? extends String> results;
                    results = bundle.getParcelableArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    processRESULT(results);
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }

    private void processRESULT(ArrayList<? extends String> command) {
        //command = command.toLowerCase();
        // ALL THE COMMAND HERE

        if (command.contains("who")){
            if(command.contains("are you")){
                SINDEESAY("I am Sin Dee");
            }
        }
    }

    private void START_TEXT_TO_SPEECH() {
        textToSpeech = new TextToSpeech(SINDEE.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (textToSpeech.getEngines().size() == 0){
                    String Msg;
                    Msg = "Please Install a TTS Engine";
                    Toast.makeText(SINDEE.this, Msg,
                            Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
                else {
                    textToSpeech.setLanguage(Locale.US);
                    String SINDEEMSG;
                    SINDEEMSG = "Hello, I am Sin Dee.  Your Personal Assistant. What can I help you?";
                    SINDEESAY(SINDEEMSG);
                }
            }
        });
    }

    private void SINDEESAY(String command) {
        if (Build.VERSION.SDK_INT > 0b10011){
            textToSpeech.speak(command, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(command, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void mic(View v){
        Toast.makeText(getApplicationContext(), "Speak", Toast.LENGTH_SHORT).show();
    }

    protected void onPause(){
        super.onPause();
        textToSpeech.shutdown();
    }
}
