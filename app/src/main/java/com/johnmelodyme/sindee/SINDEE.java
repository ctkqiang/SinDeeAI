package com.johnmelodyme.sindee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @開發人員 : JOHN MELODY MELISSA 鐘智強
 * @版权 : ALL RIGHT RESERVED  ©  JOHN MELODY MELISSA COPYRIGHT || 保留所有权利 © 鐘智強 版权
 * @项目名: SinDee, {Is a Virtual Assistant Name after My GF} || 我根據我的女朋友創建的 AI 安卓 ❤️
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

        if (command.contains("誰")){
            欣蒂說("我是欣蒂");
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
                    文字转语音.setLanguage(Locale.CHINESE);
                    String 欣蒂開始說;
                    欣蒂開始說 = "嗨， 我是欣蒂，您的虛擬助手。";
                    欣蒂說(欣蒂開始說);
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
        Toast.makeText(getApplicationContext(),
                "什麼我可以幫到的嗎？",
                Toast.LENGTH_SHORT)
                .show();
        Intent INT_语音识别;
        INT_语音识别 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        INT_语音识别.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        INT_语音识别.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        语音识别.startListening(INT_语音识别);
    }

    protected void onPause(){
        super.onPause();
        文字转语音.shutdown();
    }
}
