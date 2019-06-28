package com.tjoeun.basicappandthread0628;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.security.PublicKey;

public class ThreadActivity extends AppCompatActivity {
    TextView display;
    Button increment;
    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        display = (TextView)findViewById(R.id.display);
        increment = (Button)findViewById(R.id.increment);

        increment.setOnClickListener((view)->{
           /*
            for(int i=0; i<10; i=i+1){
                try{
                    Thread.sleep(1000);
                    display.setText("idx:" + idx);
                    idx = idx + 1;
                }catch (Exception e){

                }
            }
            */

           /*
            //Thread 클래스를 상속받은 클래스를 이용해서
            //스레드로 작업

            Thread th = new Thread(){
                public void run(){
                    for(int i=0; i<10; i=i+1) {
                        try {
                            Thread.sleep(1000);
                            display.setText("idx:" + idx);
                            idx = idx + 1;
                        } catch(Exception e) {
                        }
                    }
                }
            };

           th.start();
           */

           Runnable r = new Runnable(){
               @Override
               public void run() {
                       for (int i = 0; i < 10; i = i + 1) {
                           try {
                               Thread.sleep(1000);
                               display.setText("idx:" + idx);
                               idx = idx + 1;
                           } catch (Exception e) {}
                       }
                   }
               };

               Thread th = new Thread(r);
               th.start();

        });
    }
}
