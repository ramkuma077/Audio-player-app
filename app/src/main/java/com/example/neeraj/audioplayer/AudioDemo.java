package com.example.neeraj.audioplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AudioDemo extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    TextView t1,t2;
    SeekBar s1;
    MediaPlayer mp;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_demo);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button5);
        b5=(Button)findViewById(R.id.button6);

        s1=(SeekBar)findViewById(R.id.seekBar);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        final Intent intent=getIntent();

        final String s=intent.getStringExtra("key1");
        Toast.makeText(AudioDemo.this,"" +s,Toast.LENGTH_SHORT).show();

        mp=new MediaPlayer();


        try {
            mp.setDataSource(s);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


        long t=mp.getDuration();
        long m=(t/1000)/60;
        long se=(t/1000)%60;


        String s3=String.valueOf(m);
        String s4=String.valueOf(se);
        String s5=s3.concat(":"+s4);
        t2.setText(s5);

        Toast.makeText(AudioDemo.this, "mins"+m+"seconds:"+se, Toast.LENGTH_SHORT).show();

        s1.setMax(mp.getDuration());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              //  if (fromUser){


              // mp.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h=new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        s1.setProgress(mp.getCurrentPosition());
                        long li=mp.getCurrentPosition();
                        long m=(li/1000)/60;
                        long se=(li/1000)%60;


                        String s3=String.valueOf(m);
                        String s4=String.valueOf(se);
                        String s5=s3.concat(":"+s4);

                        t2.setText(s3);
                        h.postDelayed(this,100);



                    }
                },100);
                mp.start();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                try {
                    mp.reset();
                    mp.setDataSource(s);
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cur,dur,temp;
                cur=mp.getCurrentPosition();
                dur=mp.getDuration();
                temp=cur;
                if (temp+5000<=dur)
                {
                    cur=cur+5000;
                    mp.seekTo(cur);
                    Toast.makeText(AudioDemo.this,"jump to 5sec",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(AudioDemo.this,"not Jump",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


}
