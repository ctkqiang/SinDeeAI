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
import java.util.Locale;

/**
 * @AUTHOR : JOHN MELODY MELISSA
 * @COPYRIGHT : JOHN MELODY MELISSA COPYRIGHT 2020
 * @PROJECT_NAME: SinDee, {Is a Virtual Assistant Name after My GF}
 */

public class SINDEE extends AppCompatActivity {
    ImageView 欣蒂按键;
    TextToSpeech 文字转语音;
    SpeechRecognizer 语音识别;

    public void onStart(){
        super.onStart();
        INIT();
    }

    // onStart Declaration:
    private void INIT() {
        欣蒂按键 = findViewById(R.id.initial);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        文字轉語音開始();
        語音識別開始();
    }

    private void 語音識別開始() {
        if (SpeechRecognizer.isRecognitionAvailable(SINDEE.this)){
            语音识别 = SpeechRecognizer.createSpeechRecognizer(SINDEE.this);
            语音识别.setRecognitionListener(new RecognitionListener() {
                @Override public void onReadyForSpeech(Bundle params) {

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
                    处理结果(results);
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

    private void 处理结果(ArrayList<? extends String> command) {
        //command = command.toLowerCase();
        // ALL THE COMMAND HERE

        if (command.contains("who")){
            if(command.contains("are you")){
                欣蒂說("I am Sin Dee");
            }
        }
    }

    private void 文字轉語音開始() {
        文字转语音 = new TextToSpeech(SINDEE.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (文字转语音.getEngines().size() == 0){
                    String Msg;
                    Msg = "Please Install a TTS Engine";
                    Toast.makeText(SINDEE.this, Msg,
                            Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
                else {
                    文字转语音.setLanguage(Locale.US);
                    String SINDEEMSG;
                    SINDEEMSG = "Hello, I am Sin Dee.  Your Personal Assistant. What can I help you?";
                    欣蒂說(SINDEEMSG);
                }
            }
        });
    }

    private void 欣蒂說(String command) {
        if (Build.VERSION.SDK_INT > 0b10011){
            文字转语音.speak(command, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            文字转语音.speak(command, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void mic(View v){
        Toast.makeText(getApplicationContext(), "Speak", Toast.LENGTH_SHORT).show();
    }

    protected void onPause(){
        super.onPause();
        文字转语音.shutdown();
    }
}
