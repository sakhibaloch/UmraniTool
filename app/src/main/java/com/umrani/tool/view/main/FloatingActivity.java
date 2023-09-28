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
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.content.ContextCompat;

import com.umrani.tool.NineBall;
import com.umrani.tool.R;
import com.umrani.tool.Trickshot;
import com.umrani.tool.aimbotFov;


public class FloatingActivity extends Service {


        public static String TITLE;
        int GLITCH_DELAY = 175;
        int GLITCH_LEN = 2;
        float density;
        int dpi;
        LayoutParams g_layoutParams;
        int height;
        RelativeLayout iconLayout;
        LinearLayout itemsLayout;
        RelativeLayout relativeLayout;
        ScrollView scrollView;
        TextView textTitle;
        int type;
        int width;
        WindowManager windowManager;
      //  public native void stringFromJNI();
        float MENU_CORNER = 20.0f;

    private aimbotFov aimbotFovv;
    private Trickshot trickshot;
    private NineBall nineBall;
    private RelativeLayout board;
    private Button btn_normal, btn_trickshot, btn_nineBall;
    private float accel, accelCurrent, accelLast;
    private SensorManager sensorManager;
    private View view;


        private static GradientDrawable gd = new GradientDrawable();

        //ESPView espLayout;
        static Context ctx;
        //private native String Icon();

        static {
            TITLE = "亗『UMRANI』亗 TOOL";
        }


        private void AddButtonClose(String string, OnClickListener onClickListener) {
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
        private void AddSeekbar(final String string, int n3,final OnSeekBarChangeListener onSeekBarChangeListener) {
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
            seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

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

   /* private void showNormal() {
        aimbotFovv.setVisibility(View.VISIBLE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        aimbotFovv.setPositionCircle((widthCanvas / 2f), (heightCanvas / 2f));
        aimbotFovv.setRotation(0);
    }

    private void hideNormal() {
        aimbotFovv.setVisibility(View.GONE);
        float widthCanvas = (int) getResources().getDimension(R.dimen.canvasWidth);
        float heightCanvas = (int) getResources().getDimension(R.dimen.canvasHeight);
        aimbotFovv.setPositionCircle((widthCanvas / 2f), (heightCanvas / 2f));
        aimbotFovv.setRotation(0);
    } */

        private void AddToggle(String string, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            Switch switch_ = new Switch((Context)this);
            switch_.setText((CharSequence)string);
            switch_.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
            switch_.setTextSize(1, 14.0f);
            switch_.setTextColor(-1);
            //switch_.setShadowLayer(4,4,6,Color.parseColor("#000000"));
            switch_.setOnClickListener(new OnClickListener(){

                public void onClick(View view) {
                }
            });
            //    ColorStateList colorStateList = new ColorStateList((int[][])new int[][]{{-16842910}, {16842912}, new int[0]}, new int[]{-16776961, -16711936, -65536});
            //    ColorStateList colorStateList2 = new ColorStateList((int[][])new int[][]{{-16842910}, new int[0]}, new int[]{-7829368, -3355444});
            if (Build.VERSION.SDK_INT >= 23) {
                if (Build.VERSION.SDK_INT >= 24) {
                    //      switch_.setTrackTintList(colorStateList2);
                    //          switch_.setTrackTintMode(PorterDuff.Mode.OVERLAY);
                }
                //       switch_.setThumbTintList(colorStateList);
            } else if (Build.VERSION.SDK_INT >= 21) {
                //      switch_.getThumbDrawable().setTintList(colorStateList);
                //        switch_.getTrackDrawable().setTintList(colorStateList2);
            }
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
            switch_.setOnClickListener(new OnClickListener(){

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
/*        static native void Switch(int i, boolean k);
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
        /*public static native void DrawOn(ESPView espView, Canvas canvas);
        public void CreateCanvas() {
            ESPView eSPView;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, this.type, 56, -3);
            if (Build.VERSION.SDK_INT >= 28) {
                layoutParams.layoutInDisplayCutoutMode = 1;
            }
            layoutParams.x = 0;
            layoutParams.y = 0;
            layoutParams.gravity = 51;
            this.espLayout = eSPView = new ESPView((Context)this);
            this.windowManager.addView((View)eSPView, (ViewGroup.LayoutParams)layoutParams);
        }*/
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


            /* view = LayoutInflater.from(this).inflate(R.layout.layout_aimbot, null);
            board = view.findViewById(R.id.board);
            aimbotFovv = view.findViewById(R.id.aimbotFovv);
            trickshot = view.findViewById(R.id.trickshot);
            nineBall = view.findViewById(R.id.nineBall);

            btn_normal = view.findViewById(R.id.btn_normal);
            btn_trickshot = view.findViewById(R.id.btn_trickshot);
            btn_nineBall = view.findViewById(R.id.btn_nineBall);
            Button btn_hide = view.findViewById(R.id.btn_hide);

            layoutParams();
            sensorManager(); */



            final RelativeLayout relativeLayout;
            TextView textView;
            startAnimation();
            ScrollView scrollView;
            super.onCreate();
            //System.loadLibrary("RubelMod");
           // Switch2(0);
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
            LayoutParams layoutParams2 = new LayoutParams(n, this.convertSizeToDp(300.0f), this.type, 8, -3);
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
            //button.setText("🚫");
            button.setBackground(this.getResources().getDrawable(R.drawable.close));
          //  button.setTextColor(Color.parseColor("#000000"));
          //  button.setTextSize(2, 8.0f);
           // button.setBackgroundColor(Color.parseColor("#20000000"));
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
           // byte[] arrby = Base64.decode(Icon(), 0);
            imageView.setImageResource(R.drawable.iconfloating);


            final LayoutParams layoutParams4 = new LayoutParams(-2, -2, this.type, 8, -3);
            layoutParams4.gravity = 51;
            layoutParams4.x = 0;
            layoutParams4.y = 0;
            this.windowManager.addView((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams4);
            this.windowManager.addView((View)this.relativeLayout, (ViewGroup.LayoutParams)layoutParams2);
            this.relativeLayout.setVisibility(View.GONE);
            frameLayout.setOnTouchListener(new OnTouchListener() {
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
                        this.deltaX = (float)FloatingActivity.this.g_layoutParams.x - motionEvent.getRawX();
                        this.deltaY = (float)FloatingActivity.this.g_layoutParams.y - motionEvent.getRawY();
                        this.pressedX = motionEvent.getRawX();
                        this.pressedY = motionEvent.getRawY();
                        FloatingActivity.this.scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                    if (n == 1) {
                        float f;
                        float f2;
                        float f3;
                        float f4;
                        this.maxX = FloatingActivity.this.width - FloatingActivity.this.relativeLayout.getWidth();
                        this.maxY = FloatingActivity.this.height - FloatingActivity.this.relativeLayout.getHeight();
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
                        FloatingActivity.this.g_layoutParams.x = (int)this.newX;
                        FloatingActivity.this.g_layoutParams.y = (int)this.newY;
                        FloatingActivity.this.windowManager.updateViewLayout((View)FloatingActivity.this.relativeLayout, (ViewGroup.LayoutParams)FloatingActivity.this.g_layoutParams);
                        //FloatingModMenuService.this.relativeLayout.setAlpha(0.7f);
                        FloatingActivity.this.scrollView.requestDisallowInterceptTouchEvent(false);
                        return true;
                    }
                    if (n == 2) {
                        float f;
                        float f5;
                        this.newX = motionEvent.getRawX() + this.deltaX;
                        this.newY = motionEvent.getRawY() + this.deltaY;
                        this.maxX = FloatingActivity.this.width - FloatingActivity.this.relativeLayout.getWidth();
                        this.maxY = f5 = (float)(FloatingActivity.this.height - FloatingActivity.this.relativeLayout.getHeight());
                        float f6 = this.newX;
                        if (f6 >= 0.0f && f6 <= this.maxX && (f = this.newY) >= 0.0f && f <= f5) {
                            //FloatingModMenuService.this.relativeLayout.setAlpha(0.7f);
                            FloatingActivity.this.g_layoutParams.x = (int)this.newX;
                            FloatingActivity.this.g_layoutParams.y = (int)this.newY;
                            FloatingActivity.this.windowManager.updateViewLayout((View)FloatingActivity.this.relativeLayout, (ViewGroup.LayoutParams)FloatingActivity.this.g_layoutParams);
                        }
                        //FloatingModMenuService.this.relativeLayout.setAlpha(0.5f);
                        FloatingActivity.this.g_layoutParams.x = (int)this.newX;
                        FloatingActivity.this.g_layoutParams.y = (int)this.newY;
                        FloatingActivity.this.windowManager.updateViewLayout((View)FloatingActivity.this.relativeLayout, (ViewGroup.LayoutParams)FloatingActivity.this.g_layoutParams);
                    }
                    return false;
                }
            });

            relativeLayout.setOnTouchListener(new OnTouchListener(){
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
                            FloatingActivity.this.relativeLayout.setVisibility(0);
                            relativeLayout.setVisibility(8);
                        }
                        return true;
                    }
                    if (n == 2) {
                        this.newX = motionEvent.getRawX() + this.deltaX;
                        this.newY = motionEvent.getRawY() + this.deltaY;
                        float f = FloatingActivity.this.width - view.getWidth();
                        float f2 = FloatingActivity.this.height - view.getHeight();
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
                        FloatingActivity.this.windowManager.updateViewLayout((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams4);
                    }
                    return false;
                }
            });
            button.setOnClickListener(new OnClickListener(){

                public void onClick(View view) {
                    FloatingActivity.this.relativeLayout.setVisibility(8);
                    relativeLayout.setVisibility(0);
                }
            });

            this.AddTextRED("ALL PUBG");
            this.AddText("");
            this.AddTextGREEN("UMRANI BYPASS MENU");
            AddToggle("Logo Bypass", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 1111", "@UmraniChannels");
                }
                }
            });
            AddToggle("Loby Bypass", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 2222", "@UmraniChannels");
                    }
                }
            });


            this.AddTextYELLOW("HACKS MENU");
            AddToggle("No Recoil", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl)
                    {
                        ExecZLX("/UmraniChannels 13", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 14", "@UmraniChannels");
                    }
                }

            });
            AddToggle("Headshot", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl)
                    {
                        ExecZLX("/UmraniChannels 3", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 4", "@UmraniChannels");
                    }
                }

            });


            AddToggle("MegicBullet", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 5", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 6", "@UmraniChannels");
                    }
                }
            });
            AddToggle("High Demage", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 5", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 6", "@UmraniChannels");
                    }
                }
            });

            AddToggle("Bullet Track", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 1", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 2", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Aimbot Fov", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                       // showNormal();
                        ExecZLX("/UmraniChannels 25", "@UmraniChannels");
                    }else{
                        //hideNormal();
                        ExecZLX("/UmraniChannels 26", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Instant Hit", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 3", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 4", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Fast Shoot", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 3", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 4", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Xhit Effect", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 23", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 24", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Ipad View", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 57", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 58", "@UmraniChannels");
                    }
                }
            });
            AddToggle("No Grass", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 17", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 18", "@UmraniChannels");
                    }
                }
            });
            AddToggle("No Fog", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 11", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 12", "@UmraniChannels");
                    }
                }
            });
            AddToggle("No Tree", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 9", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 10", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Small Croshier", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 21", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 22", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Black Body", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 19", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 20", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Black Sky", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 15", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 16", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Night Mode", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 15", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 16", "@UmraniChannels");
                    }
                }
            });

            AddToggle("Long Jump", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 31", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 32", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Car Jump", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 32", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 33", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Knock Speed", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 28", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 27", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Flash Speed", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 27", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 28", "@UmraniChannels");
                    }
                }
            });
            AddToggle("Car Speed", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    if (bl) {
                        ExecZLX("/UmraniChannels 29", "@UmraniChannels");
                    }else{
                        ExecZLX("/UmraniChannels 30", "@UmraniChannels");
                    }
                }
            });
            AddButtonClose("CLOSE FLOATING MENU", new OnClickListener(){
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((Context)FloatingActivity.this, 5);
                    builder.setMessage("Do You Want To Close Umrani Tool ?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialogInterface, int n) {
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
   /* private void layoutParams() {
        float boardMarginBottom = getResources().getDimension(R.dimen.boardMarginBottom);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
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

            if (accel > 8 && accel < 15) {
                board.setVisibility(View.VISIBLE);
                board.setAlpha(0.0f);
                board.animate().setDuration(1000).alpha(1.0f).setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        board.animate().setListener(null);
                    }
                });
            }
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
    } */

    public void ExecZLX(String path, String toastHZ) {
        try {
            // ROOT
            ExecuteElf("su -c chmod 777 " + getFilesDir() + path);
            ExecuteElf("su -c " + getFilesDir() + path);
            // VIRTUAL
            ExecuteElf("chmod 777 " + getFilesDir() + path);
            ExecuteElf("" + getFilesDir() + path);
            Toast.makeText(FloatingActivity.this, toastHZ, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
    }

    private void ExecuteElf(String shell) {
        String s = shell;
        try {
            Runtime.getRuntime().exec(s, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void onDestroy() {
            //this.windowManager.removeViewImmediate((View)this.espLayout);
            this.windowManager.removeViewImmediate((View)this.iconLayout);
            this.windowManager.removeViewImmediate((View)this.relativeLayout);
            super.onDestroy();
        }
        public static interface OnListChoosedListener {
            public void onChoosed(int var1);
        }

    }




