package com.tjoeun.basicappandthread0628;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HandlerActivity extends AppCompatActivity {
    TextView handlerDisp;
    Button handlerBtn;
    int idx;
    int [] colors ={Color.RED, Color.BLUE, Color.GREEN};


    //넘겨받은 obj를 텍스트 뷰에 출력하는 핸들러
    Handler handler = new Handler(){
      @Override
      public void handleMessage(Message msg){
          Integer data = (Integer)msg.obj;
          handlerDisp.setTextColor(colors[idx%3]);
          handlerDisp.setText("data:" + data);
      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handlerDisp = (TextView)findViewById(R.id.handlerdisplay);
        handlerBtn = (Button)findViewById(R.id.handlerbtn);
       /*
        handlerBtn.setOnClickListener((view) ->{
            Thread th = new Thread(){
                @Override
                public void run(){
                    for(int i=0; i<10; i=i+1){
                        try {
                            Thread.sleep(1000);
                            //메시지를 생성
                            Message msg = new Message();
                            //데이터를 대입
                            //어떤 속성의 값을 바로 대입한 것
                            msg.obj = idx;
                            idx = idx + 1;
                            //핸들러에게 메시지를 전송
                            handler.sendMessage(msg);
                        }catch (Exception e){

                        }
                    }

                }
            };
            th.start();
        });
        */

     handlerBtn.setOnClickListener((view) ->{
         //Handler를 post를 이용해서 호출
         Thread th = new Thread(){
             //비어있는 핸들러 생성
             Handler handler = new Handler();
             public void run() {
                 //반복문
                 for(int i=0; i<10; i=i+1){
                     try{
                         //다음날 설정하는법 (월은 -1, 데이트는 1900년을 빼야해, 일은 상관없음)
                         Calendar cal = new GregorianCalendar(2019, 5, 29, 16,53, 0);
                         long nextDay = cal.getTimeInMillis();

                         Thread.sleep(1000);
                         //handler.post(new Runnable() { -->postAtTime으로 바꿔주고
                         handler.postAtTime(new Runnable() {
                         public void run() {
                             handlerDisp.setText(idx + "");
                         }
                         //}); -> 사이에 nextDay집어넣기
                        }, nextDay);
                         idx = idx + 1;
                     }catch(Exception e){}
                 }
             }
         };
         th.start();

     });
    }
}
