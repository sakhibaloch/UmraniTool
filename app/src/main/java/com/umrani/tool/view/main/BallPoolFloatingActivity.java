package com.umrani.tool.view.main;

import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.content.ContextCompat;

import com.umrani.tool.NineBall;
import com.umrani.tool.Normal;
import com.umrani.tool.R;
import com.umrani.tool.Trickshot;


import java.util.Objects;


public class BallPoolFloatingActivity extends Service {



    public static String TITLE;
    int GLITCH_DELAY = 175;
    int GLITCH_LEN = 2;
    float density;
    int dpi;
    WindowManager.LayoutParams g_layoutParams;
    int height;
    RelativeLayout iconLayout;
    LinearLayout itemsLayout;
    RelativeLayout relativeLayout;
    ScrollView scrollView;
    TextView textTitle;
    private Normal normal;
    private RelativeLayout board;
    private Trickshot trickshot;
    private NineBall nineBall;
    int type;
    int width;
    WindowManager windowManager;
    public native void stringFromJNI();
    private SensorManager sensorManager;
    private View view;

    private MediaPlayer mediaPlayer;
    private float accel, accelCurrent, accelLast;
    float MENU_CORNER = 20.0f;
    private static GradientDrawable gd = new GradientDrawable();
    static Context ctx;
    private native String Icon();

    static {
        TITLE = "亗『UMRANI』亗 TOOL";
    }
    private void StartFloatingServicBall() {
        startService(new Intent(this, BallPoolFloatingActivity.class));
    }
    private void StopFloatingServiceBall() {
        stopService(new Intent(this, BallPoolFloatingActivity.class));
    }



    private void AddButtonClose(String string, View.OnClickListener onClickListener) {
        Button button = new Button((Context)this);
        button.setText((CharSequence)string);
        button.setX((float)this.convertSizeToDp(10.0f));
        button.setY(10.0f + button.getY());
        button.setTextSize(1, 12.5f);
        button.setOnClickListener(onClickListener);
        button.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        button.setTextColor(Color.parseColor("#FF000000"));
        button.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(this.g_layoutParams.width - 2* this.convertSizeToDp(10.0f), -2));
        this.itemsLayout.addView((View)button);
    }
    private void AddSeekbar(final String string, int n3,final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        final TextView textView = new TextView((Context)this);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(": ");
        stringBuilder.append(n3);
        textView.setText((CharSequence)stringBuilder.toString());
        textView.setTextSize(1, 12.5f);
        textView.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        textView.setTextColor(-1);
        textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        SeekBar seekBar = new SeekBar((Context)this);
        seekBar.setMax(n3);
        //seekBar.setShadowLayer(4,4,6,Color.parseColor("#000000"));
        seekBar.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                TextView textView2 = textView;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(string);
                stringBuilder.append(": ");
                stringBuilder.append(n);
                textView2.setText((CharSequence)stringBuilder.toString());
                onSeekBarChangeListener.onProgressChanged(seekBar, n, bl);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.itemsLayout.addView((View)textView);
        this.itemsLayout.addView((View)seekBar);
    }

    private void AddText(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 12.5f);
        textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        textView.setTextColor(-1);
        textView.setShadowLayer(4,4,6,Color.parseColor("#000000"));
        this.itemsLayout.addView((View)textView);
    }

    private void AddTextRED(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 15.0f);
        //   textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setTypeface(null, Typeface.BOLD);
        //  textView.setPadding(this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        this.itemsLayout.addView((View)textView);
    }


    private void AddTextYELLOW(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 15.0f);
        //   textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setTypeface(null, Typeface.BOLD);
        //  textView.setPadding(this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.YELLOW);
        this.itemsLayout.addView((View)textView);
    }

    private void AddTextGREEN(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 15.0f);
        //   textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setTypeface(null, Typeface.BOLD);
        //  textView.setPadding(this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.GREEN);
        this.itemsLayout.addView((View)textView);
    }

    private void AddTextBLUE(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 15.0f);
        //   textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setTypeface(null, Typeface.BOLD);
        //  textView.setPadding(this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        this.itemsLayout.addView((View)textView);
    }

    private void showNormal() {
        trickshot.setVisibility(View.GONE);
        nineBall.setVisibility(View.GONE);
        normal.setVisibility(View.VISIBLE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        normal.setPositionCircle((widthCanvas / 2f), (heightCanvas / 2f));
        normal.setRotation(0);
    }

    private void showTrickshot() {
        normal.setVisibility(View.GONE);
        nineBall.setVisibility(View.GONE);
        trickshot.setVisibility(View.VISIBLE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        trickshot.setPositionCircleOne((widthCanvas / 2f) - 200, (heightCanvas / 2f));
        trickshot.setPositionCircleTwo((widthCanvas / 2f) + 200, (heightCanvas / 2f));
        trickshot.setPositionControls(widthCanvas - 200, 200);
        trickshot.setRotation(0);
    }

    private void showNineBall() {
        normal.setVisibility(View.GONE);
        trickshot.setVisibility(View.GONE);
        nineBall.setVisibility(View.VISIBLE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        // Start line
        float left = widthCanvas - 327;
        float top = heightCanvas - 300;
        // End line
        float right = widthCanvas - 290;
        float bottom = heightCanvas - 282.5f;
        nineBall.setPositionCircleOne((widthCanvas / 2f) + 345.5f, (heightCanvas / 2f) + 39f);
        nineBall.setPositionCircleTwo((widthCanvas / 2f) - 254, (heightCanvas / 2f) - 136.5f);
        nineBall.setPositionLine(left, top, right, bottom);
        nineBall.setRotation(0);
    }

    private void HideNormal() {
        trickshot.setVisibility(View.GONE);
        nineBall.setVisibility(View.GONE);
        normal.setVisibility(View.GONE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        normal.setPositionCircle((widthCanvas / 2f), (heightCanvas / 2f));
        normal.setRotation(0);
    }

    private void HideTrickshot() {
        trickshot.setVisibility(View.GONE);
        nineBall.setVisibility(View.GONE);
        normal.setVisibility(View.GONE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        trickshot.setPositionCircleOne((widthCanvas / 2f) - 200, (heightCanvas / 2f));
        trickshot.setPositionCircleTwo((widthCanvas / 2f) + 200, (heightCanvas / 2f));
        trickshot.setPositionControls(widthCanvas - 200, 200);
        trickshot.setRotation(0);
    }

    private void HideNineBall() {
        trickshot.setVisibility(View.GONE);
        nineBall.setVisibility(View.GONE);
        normal.setVisibility(View.GONE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        // Start line
        float left = widthCanvas - 327;
        float top = heightCanvas - 300;
        // End line
        float right = widthCanvas - 290;
        float bottom = heightCanvas - 282.5f;
        nineBall.setPositionCircleOne((widthCanvas / 2f) + 345.5f, (heightCanvas / 2f) + 39f);
        nineBall.setPositionCircleTwo((widthCanvas / 2f) - 254, (heightCanvas / 2f) - 136.5f);
        nineBall.setPositionLine(left, top, right, bottom);
        nineBall.setRotation(0);
    }


    private void AddToggle(String string, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        Switch switch_ = new Switch((Context)this);
        switch_.setText((CharSequence)string);
        switch_.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        switch_.setTextSize(1, 14.0f);
        switch_.setTextColor(-1);
        //switch_.setShadowLayer(4,4,6,Color.parseColor("#000000"));
        switch_.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
            }
        });
        switch_.setOnCheckedChangeListener(onCheckedChangeListener);
        switch_.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        this.itemsLayout.addView((View)switch_);
    }

    private void AddToggleDefTrue(String string, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        Switch switch_ = new Switch((Context)this);
        switch_.setText((CharSequence)string);
        switch_.setChecked(true);
        switch_.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        switch_.setTextSize(1, 12.5f);
        switch_.setTextColor(-1);
        switch_.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
            }
        });
        ColorStateList colorStateList = new ColorStateList((int[][])new int[][]{{-16842910}, {16842912}, new int[0]}, new int[]{-16776961, -16711936, -65536});
        ColorStateList colorStateList2 = new ColorStateList((int[][])new int[][]{{-16842910}, new int[0]}, new int[]{-7829368, -3355444});
        if (Build.VERSION.SDK_INT >= 23) {
            if (Build.VERSION.SDK_INT >= 24) {
                switch_.setTrackTintList(colorStateList2);
                switch_.setTrackTintMode(PorterDuff.Mode.OVERLAY);
            }
            switch_.setThumbTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 21) {
            switch_.getThumbDrawable().setTintList(colorStateList);
            switch_.getTrackDrawable().setTintList(colorStateList2);
        }
        switch_.setOnCheckedChangeListener(onCheckedChangeListener);
        switch_.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        this.itemsLayout.addView((View)switch_);
    }
/*    static native void Switch(int i, boolean k);
    static native void Switch2(int i);
    static native void TextSize(int var0);*/

    private int getLayoutType() {
        if (Build.VERSION.SDK_INT >= 26) {
            return 2038;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return 2002;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return 2005;
        }
        return 2003;
    }

    public int convertSizeToDp(float f) {
        return Math.round((float) TypedValue.applyDimension((int)1, (float)f, (DisplayMetrics)this.getResources().getDisplayMetrics()));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startAnimation() {
        final int start = ContextCompat.getColor(this, R.color.background_color);
        final int middle = Color.GRAY;
        final int end = ContextCompat.getColor(this, R.color.background_color);

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        gd.setCornerRadius(0f);
        gd.setOrientation(GradientDrawable.Orientation.TL_BR);
        final GradientDrawable gradient = gd;

        ValueAnimator animator = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float fraction = valueAnimator.getAnimatedFraction();
                int newStart = (int) evaluator.evaluate(fraction, start, end);
                int newMiddle = (int) evaluator.evaluate(fraction, end, middle);
                int newEnd = (int) evaluator.evaluate(fraction, end, start);
                int[] newArray = {newStart, newMiddle, newEnd};
                gradient.setColors(newArray);
            }
        });

        animator.start();
    }

    @SuppressLint("WrongConstant")
    public void onCreate() {

        view = LayoutInflater.from(this).inflate(R.layout.main, null);
        normal = view.findViewById(R.id.normal);
        trickshot = view.findViewById(R.id.trickshot);
        nineBall = view.findViewById(R.id.nineBall);
        mediaPlayer = MediaPlayer.create(this, R.raw.touch);


        layoutParams();
        sensorManager();

        final RelativeLayout relativeLayout;
        TextView textView;
        startAnimation();
        ScrollView scrollView;
        super.onCreate();

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        this.relativeLayout = new RelativeLayout((Context)this);
        this.type = this.getLayoutType();
        Display display = this.windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        this.width = point.x;
        this.height = point.y;
        this.dpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        this.density = Resources.getSystem().getDisplayMetrics().density;
        this.itemsLayout = new LinearLayout((Context)this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.itemsLayout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.relativeLayout.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
        this.scrollView = scrollView = new ScrollView((Context)this);
        scrollView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        this.scrollView.setPadding(0, (int)((float)this.dpi / 5.5f), 0, 0);
        int n = this.convertSizeToDp(210.0f);
        int n1 = this.convertSizeToDp(30.0f);
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(n, this.convertSizeToDp(300.0f), this.type, 8, -3);
        this.relativeLayout.setBackgroundColor(Color.parseColor("#00000000"));
        this.relativeLayout.setBackground(gd);
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        layoutParams2.gravity = 51;
        this.g_layoutParams = layoutParams2;
        FrameLayout frameLayout = new FrameLayout((Context)this);
        frameLayout.setClickable(true);
        frameLayout.setFocusable(true);
        frameLayout.setFocusableInTouchMode(true);
        frameLayout.setBackgroundColor(Color.parseColor("#00000000"));
        frameLayout.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(n, (int)((float)this.dpi / 5.5f)));


        Button button = new Button((Context)this);
        button.setBackground(this.getResources().getDrawable(R.drawable.close));
        button.setX(n - dpi / 5.5f);
        button.setLayoutParams(new RelativeLayout.LayoutParams((int)((dpi / 5.5f)), n1));



        frameLayout.addView((View)button);
        this.textTitle = textView = new TextView((Context)this);
        textView.setText((CharSequence)TITLE);
        this.textTitle.setGravity(19);
        textView.setShadowLayer(4,4,6,Color.parseColor("#000000"));
        textView.setTextColor(Color.parseColor("#ffffff"));
        this.textTitle.setTypeface(null, Typeface.BOLD);
        textView.setPadding(convertSizeToDp(5.0f),convertSizeToDp(5.0f),convertSizeToDp(5.0f),convertSizeToDp(5.0f));
        this.textTitle.setTextSize(1, 15.0f);
        frameLayout.addView((View)this.textTitle);
        this.relativeLayout.addView((View)this.scrollView);
        this.relativeLayout.addView((View)frameLayout);
        //this.relativeLayout.setAlpha(0.7f);
        this.itemsLayout.setOrientation(LinearLayout.VERTICAL);
        this.scrollView.addView((View)this.itemsLayout);
        this.iconLayout = relativeLayout = new RelativeLayout((Context)this);
        relativeLayout.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));


        ImageView imageView = new ImageView((Context)this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(this.convertSizeToDp(60.0f), this.convertSizeToDp(60.0f)));
        relativeLayout.addView((View)imageView);
        imageView.setImageResource(R.drawable.iconfloating);


        final WindowManager.LayoutParams layoutParams4 = new WindowManager.LayoutParams(-2, -2, this.type, 8, -3);
        layoutParams4.gravity = 51;
        layoutParams4.x = 0;
        layoutParams4.y = 0;
        this.windowManager.addView((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams4);
        this.windowManager.addView((View)this.relativeLayout, (ViewGroup.LayoutParams)layoutParams2);
        this.relativeLayout.setVisibility(View.GONE);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            float deltaX;
            float deltaY;
            float maxX;
            float maxY;
            float newX;
            float newY;
            float pressedX;
            float pressedY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int n = motionEvent.getActionMasked();
                if (n == 0) {
                    this.deltaX = (float)BallPoolFloatingActivity.this.g_layoutParams.x - motionEvent.getRawX();
                    this.deltaY = (float)BallPoolFloatingActivity.this.g_layoutParams.y - motionEvent.getRawY();
                    this.pressedX = motionEvent.getRawX();
                    this.pressedY = motionEvent.getRawY();
                    BallPoolFloatingActivity.this.scrollView.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
                if (n == 1) {
                    float f;
                    float f2;
                    float f3;
                    float f4;
                    this.maxX = BallPoolFloatingActivity.this.width - BallPoolFloatingActivity.this.relativeLayout.getWidth();
                    this.maxY = BallPoolFloatingActivity.this.height - BallPoolFloatingActivity.this.relativeLayout.getHeight();
                    if (this.newX < 0.0f) {
                        this.newX = 0.0f;
                    }
                    if ((f = this.newX) > (f3 = this.maxX)) {
                        this.newX = (int)f3;
                    }
                    if (this.newY < 0.0f) {
                        this.newY = 0.0f;
                    }
                    if ((f4 = this.newY) > (f2 = this.maxY)) {
                        this.newY = (int)f2;
                    }
                    BallPoolFloatingActivity.this.g_layoutParams.x = (int)this.newX;
                    BallPoolFloatingActivity.this.g_layoutParams.y = (int)this.newY;
                    BallPoolFloatingActivity.this.windowManager.updateViewLayout((View)BallPoolFloatingActivity.this.relativeLayout, (ViewGroup.LayoutParams)BallPoolFloatingActivity.this.g_layoutParams);
                    //FloatingModMenuService.this.relativeLayout.setAlpha(0.7f);
                    BallPoolFloatingActivity.this.scrollView.requestDisallowInterceptTouchEvent(false);
                    return true;
                }
                if (n == 2) {
                    float f;
                    float f5;
                    this.newX = motionEvent.getRawX() + this.deltaX;
                    this.newY = motionEvent.getRawY() + this.deltaY;
                    this.maxX = BallPoolFloatingActivity.this.width - BallPoolFloatingActivity.this.relativeLayout.getWidth();
                    this.maxY = f5 = (float)(BallPoolFloatingActivity.this.height - BallPoolFloatingActivity.this.relativeLayout.getHeight());
                    float f6 = this.newX;
                    if (f6 >= 0.0f && f6 <= this.maxX && (f = this.newY) >= 0.0f && f <= f5) {
                        //FloatingModMenuService.this.relativeLayout.setAlpha(0.7f);
                        BallPoolFloatingActivity.this.g_layoutParams.x = (int)this.newX;
                        BallPoolFloatingActivity.this.g_layoutParams.y = (int)this.newY;
                        BallPoolFloatingActivity.this.windowManager.updateViewLayout((View)BallPoolFloatingActivity.this.relativeLayout, (ViewGroup.LayoutParams)BallPoolFloatingActivity.this.g_layoutParams);
                    }
                    //FloatingModMenuService.this.relativeLayout.setAlpha(0.5f);
                    BallPoolFloatingActivity.this.g_layoutParams.x = (int)this.newX;
                    BallPoolFloatingActivity.this.g_layoutParams.y = (int)this.newY;
                    BallPoolFloatingActivity.this.windowManager.updateViewLayout((View)BallPoolFloatingActivity.this.relativeLayout, (ViewGroup.LayoutParams)BallPoolFloatingActivity.this.g_layoutParams);
                }
                return false;
            }
        });

        relativeLayout.setOnTouchListener(new View.OnTouchListener(){
            float deltaX;
            float deltaY;
            float newX;
            float newY;
            float pressedX;
            float pressedY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int n = motionEvent.getActionMasked();
                if (n == 0) {
                    this.deltaX = (float)layoutParams4.x - motionEvent.getRawX();
                    this.deltaY = (float)layoutParams4.y - motionEvent.getRawY();
                    this.pressedX = motionEvent.getRawX();
                    this.pressedY = motionEvent.getRawY();
                    return false;
                }
                if (n == 1) {
                    int n2 = (int)(motionEvent.getRawX() - this.pressedX);
                    int n3 = (int)(motionEvent.getRawY() - this.pressedY);
                    if (n2 == 0 && n3 == 0) {
                        BallPoolFloatingActivity.this.relativeLayout.setVisibility(0);
                        relativeLayout.setVisibility(8);
                    }
                    return true;
                }
                if (n == 2) {
                    this.newX = motionEvent.getRawX() + this.deltaX;
                    this.newY = motionEvent.getRawY() + this.deltaY;
                    float f = BallPoolFloatingActivity.this.width - view.getWidth();
                    float f2 = BallPoolFloatingActivity.this.height - view.getHeight();
                    if (this.newX < 0.0f) {
                        this.newX = 0.0f;
                    }
                    if (this.newX > f) {
                        this.newX = (int)f;
                    }
                    if (this.newY < 0.0f) {
                        this.newY = 0.0f;
                    }
                    if (this.newY > f2) {
                        this.newY = (int)f2;
                    }
                    layoutParams4.x = (int)this.newX;
                    layoutParams4.y = (int)this.newY;
                    BallPoolFloatingActivity.this.windowManager.updateViewLayout((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams4);
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                BallPoolFloatingActivity.this.relativeLayout.setVisibility(8);
                relativeLayout.setVisibility(0);
            }
        });

        this.AddTextRED("8 BAll POOL");
        this.AddText("");
        this.AddTextGREEN("UMRANI BYPASS MENU");
        AddToggle("Logo Bypass", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Bypass 1 Actived", Toast.LENGTH_SHORT).show();
                }else{
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Bypass 1 Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AddToggle("Loby Bypass", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Bypass 2 Actived", Toast.LENGTH_SHORT).show();
                }else{
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Bypass 2 Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.AddTextYELLOW("HACKS MENU");
        AddToggle("One Lines", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    mediaPlayer.start();
                    showTrickshot();
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.start();
                    HideNormal();
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AddToggle("Two Lines", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_SHORT).show();
                }else{
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AddToggle("Six Lines", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    mediaPlayer.start();
                    showNormal();
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_SHORT).show();
                }else{
                    mediaPlayer.start();
                    HideTrickshot();
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AddToggle("Aimbot Fov", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_LONG).show();
                }
            }
        });

        AddToggle("Fast Shoot", new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "@UmraniChannels", Toast.LENGTH_LONG).show();
                }
            }
        });

        AddButtonClose("CLOSE FLOATING MENU", new View.OnClickListener(){
            public void onClick(View view) {
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                        PixelFormat.TRANSLUCENT
                );
                params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)BallPoolFloatingActivity.this, 5);
                builder.setMessage("Do You Want To Close Umrani Tool ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int n) {
                        StopFloatingServiceBall();
                        stopSelf();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                if (Build.VERSION.SDK_INT >= 26) {
                    Window window = alertDialog.getWindow();
                    if (window != null) {
                        window.setType(2038);
                    }
                } else {
                    Window window = alertDialog.getWindow();
                    if (window != null) {
                        window.setType(2002);
                    }
                }
                alertDialog.show();
            }

        });




    }
    private void layoutParams() {
        float boardMarginBottom = getResources().getDimension(R.dimen.boardMarginBottom);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        params.verticalMargin = boardMarginBottom;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(view, params);
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float y = event.values[1];
            float z = event.values[2];
            accelLast = accelCurrent;
            accelCurrent = (float) Math.sqrt(y * y + z * z);
            float delta = accelCurrent - accelLast;
            accel = accel * 0.9f + delta;
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    public void sensorManager() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager)
                .registerListener(
                        mSensorListener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                        SensorManager.SENSOR_DELAY_NORMAL
                );
        accel = 100f;
        accelCurrent = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;
    }


    public void onDestroy() {
        sensorManager.unregisterListener(mSensorListener);
        this.windowManager.removeViewImmediate((View)this.iconLayout);
        this.windowManager.removeViewImmediate((View)this.relativeLayout);
        super.onDestroy();
    }
    public static interface OnListChoosedListener {
        public void onChoosed(int var1);
    }



}




