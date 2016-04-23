package com.example.wuyin.mpandroidchart;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new MyOnclickLinstener());

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);



       // mVehicle = component.provideVehicle();


    }

    /**
     * 在这里使用了AsyncTask来进行异步任务
     */
   private class MyAsync extends AsyncTask<Void,Integer,Void>{

        //刚开始的进度
        int current = 0;

        @Override
        protected Void doInBackground(Void... params) {
            do{

                current +=10;

                //更新进度条
                publishProgress(current);

                try {
                    //睡眠1秒
                    Thread.sleep(1000);

                    //得到当前进度条的值
                    if (mProgressBar.getProgress() >=100){

                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }while (mProgressBar.getProgress() <=100);


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
            if (values[0]>=100){
                //当进度完成的时候弹出吐司
                Toast.makeText(MainActivity.this, "进度条更新完毕", Toast.LENGTH_SHORT).show();
            }
        }


    }

    //自定义的一个内部类，实现了OnClickListener接口
    class MyOnclickLinstener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            new MyAsync().execute();
        }
    }
}

