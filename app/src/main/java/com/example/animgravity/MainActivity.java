package com.example.animgravity;

import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

     LinearLayout linearLayout;
     ImageView stone1, stone2, stone3, stone4, stone5;
     private int screenHeight, circlePosY=500;

       private int click = 0;

    public void showToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stone1 = (ImageView) findViewById(R.id.yellowStone);
        stone2 = (ImageView) findViewById(R.id.blueStone);
        stone3 = (ImageView) findViewById(R.id.purpleStone);
        stone4 = (ImageView) findViewById(R.id.redStone);
        stone5 = (ImageView) findViewById(R.id.brownStone);

        stone1.setX(0);
        stone2.setX(75);
        stone3.setX(150);
        stone4.setX(225);
        stone5.setX(300);


        stone1.setY(circlePosY);
        stone2.setY(circlePosY);
        stone3.setY(circlePosY);
        stone4.setY(circlePosY);
        stone5.setY(circlePosY);



        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;

        linearLayout = (LinearLayout) findViewById(R.id.lLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++click;
                if (click == 1)
                      runnableUp.run();
                 if(click%2==1){
                     handler.removeCallbacks(runnableDown);
                     runnableUp.run();
                 }
                 else {
                     handler.removeCallbacks(runnableUp);
                     runnableDown.run();
                }
            }
        });
    }

        final Runnable runnableDown =new Runnable() {
            @Override
            public void run() {
              try {
                  positionDown();
              }
               finally {
                  handler.postDelayed(runnableDown, 5);
              }

            }
        };

    final Runnable runnableUp = new Runnable() {
        @Override
        public void run() {
          try {
              positionUp();
          }
          finally {
              handler.postDelayed(runnableUp, 25);

          }
        }
    };


    public void positionUp(){
        if(stone1.getY()<-100)
            circlePosY=-100;
               else
            circlePosY -= 5;

            stone1.setY(circlePosY);
        stone2.setY(circlePosY);
        stone3.setY(circlePosY);
        stone4.setY(circlePosY);
        stone5.setY(circlePosY);
    }

    public void positionDown() {
        if (stone1.getY()> screenHeight-150)
            circlePosY = screenHeight-150;
        else
            circlePosY += 5;

            stone1.setY(circlePosY);
        stone2.setY(circlePosY);
        stone3.setY(circlePosY);
        stone4.setY(circlePosY);
        stone5.setY(circlePosY);
    }
}




