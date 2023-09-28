package com.umrani.tool.view.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.umrani.tool.R;


public class boostr extends Activity {


    private Context mContext;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.boostrlayout);

        int TIME_SPLASH = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), TMainActivity.class));
                finish();
                Toast.makeText(boostr.this, "\uD83E\uDD29 AMAZING RAM BOOSTED \uD83E\uDD29", Toast.LENGTH_LONG).show();

            }
        }, TIME_SPLASH);
    }

}

