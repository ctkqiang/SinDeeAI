package com.johnmelodyme.sindee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @開發人員 : JOHN MELODY MELISSA 鐘智強
 * @版权 : ALL RIGHT RESERVED  ©  JOHN MELODY MELISSA COPYRIGHT || 保留所有权利 © 鐘智強 版权
 * @项目名: SinDee, {Is a Virtual Assistant Name after My GF} || 我根據我的女朋友創建的 AI 安卓 ❤️
 */

public class SINDEE extends AppCompatActivity {
    Button 欣蒂按键;
    TextToSpeech 文字转语音;
    SpeechRecognizer 语音识别;
    Date 日期時間;
    Calendar 日曆;

    public void onStart(){
        super.onStart();
        INIT();
    }

    // onStart Declaration:
    private void INIT() {
        欣蒂按键 = findViewById(R.id.欣蒂按键);
        日期時間 = Calendar.getInstance().getTime();
        日曆 = Calendar.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        文字轉語音開始();
        語音識別開始();

    }

    // 語音識別開始功能:
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
                    if (results != null) {
                        处理结果(results);
                    }
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

    // 处理结果功能:
    private void 处理结果(ArrayList<? extends String> 命令) {
        // 命令::

        // 時間设置:
        String 時間, 日期, SECOND_秒, HOUR_時, MINUTES_分, DAY_天;
        int 秒, 時, 分, 天;
        秒 = 日曆.get(Calendar.SECOND);
        時 = 日曆.get(Calendar.HOUR);
        分 = 日曆.get(Calendar.MINUTE);
        天 = 日曆.get(Calendar.HOUR_OF_DAY);
        // int 改造成 String:
        SECOND_秒 = Integer.toString(秒);
        HOUR_時 = Integer.toString(時);
        MINUTES_分 = Integer.toString(分);
        DAY_天 = Integer.toString(天);

        //日期 = String.valueOf(new SimpleDateFormat("HH:MM", Locale.getDefault()));
        //時間 = String.format(日期, new Date());

        if (命令.indexOf("嗨") == -1){
            欣蒂說("怎麼了");
        }

        if (命令.contains("hello")){
            欣蒂說("嗨");
        }

        if (命令.contains("你是誰") || 命令.contains("誰")){
            欣蒂說("我是欣蒂");
        }

        if (命令.contains("幾點了")){
            欣蒂說("現在是" + SECOND_秒);
        }

        else {
            欣蒂不明白();
        }

    }

    // 文字轉語音開始功能:
    private void 文字轉語音開始() {
        文字转语音 = new TextToSpeech(SINDEE.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (文字转语音.getEngines().size() == 0){
                    String Msg, URL;
                    Intent 安裝文字转语音器;
                    Msg = "請安裝文字转语音器";
                    URL = "https://bit.ly/2spFOjd";
                    Toast.makeText(SINDEE.this, Msg,
                            Toast.LENGTH_SHORT)
                            .show();
                    安裝文字转语音器 = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(安裝文字转语音器);
                    finish();
                }
                else {
                    文字转语音.setLanguage(Locale.CHINESE);
                    String 欣蒂開始說;
                    欣蒂開始說 = "嗨， 我是欣蒂，您的虛擬助手, 什麼事我可以幫到的嗎？";
                    欣蒂說(欣蒂開始說);
                }
            }
        });
    }

    // 欣蒂說話功能：
    private void 欣蒂說(String command) {
        if (Build.VERSION.SDK_INT > 0b10011){
            文字转语音.speak(command, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            文字转语音.speak(command, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    // 麥克風功能:
    public void 麥克風(View v){
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

    private void 欣蒂不明白(){
        欣蒂說("我不明白");
    }

    protected void onPause(){
        super.onPause();
        文字转语音.shutdown();
    }
}
