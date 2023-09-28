package com.umrani.tool.view.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.umrani.tool.R;


public class AboutActivity extends AppCompatActivity {

  public static final String PACKAGE_AMAZE_UTILS = null;
  private Context mContext;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(1);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    getWindow().setStatusBarColor(Color.TRANSPARENT);
    setContentView(R.layout.activity_about);





  }}