
package com.umrani.tool.view.main;

import static android.os.Build.VERSION.SDK_INT;
import static android.provider.Settings.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.documentfile.provider.DocumentFile;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.umrani.tool.R;
import com.umrani.tool.view.setting.MyFileHelper;
/*import com.umrani.tool.ui.activities.Prefs;
import com.umrani.tool.ui.activities.SketchwareUtil;*/

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/*import java.awt.*;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Desktop;
import java.io.File;
import javax.imageio.ImageIO;*/

public class LoginActivity extends Activity {


    public LoginActivity() {
    }

    /* static {
            System.loadLibrary("Umrani");
        }*/
    static native boolean Check();

    private FirebaseAuth mAuth;
    private native String loginURL();
    private String USER = "USER";
    private String PASS = "PASS";
    private Prefs prefs;
    private Button startButton;
    private Context mContext;
    Context context;
    private ImageButton storage;
    private ImageButton storage2;
    private ImageButton insatllfile;

    private Button stopButton;
    private String duser, dpass;
    private EditText mail, pass;
    private Boolean is_conect = false;
    private CheckBox rememberme;
    private static final String TAG = "PERMISSION_TAG";
    final static int REQUEST_CODE = 333;
    private  Uri uri2;
    private  Uri muri;
    private  Uri parenturi;
    private  DocumentFile mfile;
    private  DocumentFile mfile1;
   // private  static final int NEW_FOLDER_REQUEST_CODE = 43;
    private Intent i = new Intent();
    private  SharedPreferences sp;

  //  private LinearLayout linear1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    // @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(LoginActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //initialize(_savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        //   rememberme = findViewById(R.id.rememberme);
        startButton = findViewById(R.id.startButton);

        prefs = Prefs.with(this);
        mail.setText(prefs.read(USER, ""));
        pass.setText(prefs.read(PASS, ""));

        CardView telrgrm = findViewById(R.id.telrgrm);
        telrgrm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("https://t.me/umranichannels"));
                LoginActivity.this.getApplicationContext().startActivity(intent);
                Toast.makeText(LoginActivity.this, "\uD83D\uDC95JOIN\uD83D\uDC95", Toast.LENGTH_SHORT).show();
            }
        });

        CardView key = findViewById(R.id.key);
        key.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), keyjava.class));
            }
        });



        startButton.setOnClickListener(new OnClickListener() {
           @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
               MyFileHelper.deleteFile("/data/data/com.umrani.tool/files/UmraniChannels");
               // startActivity(new Intent(getApplicationContext(), TMainActivity.class));
                if (!mail.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    signIn(mail, pass);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Enter key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void signIn(EditText email, EditText password) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
        } else {
            //	Toast.makeText(getApplicationContext(), "You have not login", Toast.LENGTH_LONG).show();
            final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
            progress.setMax(100);
            progress.setMessage("Checking key");
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            if (!progress.isShowing()) {
                progress.show();
            }
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                        private String TAG;

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                if (progress.isShowing())
                                    progress.dismiss();
                                prefs.write(USER, "");
                                prefs.write(PASS, "");
                                int TIME_SPLASH = 1500;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        FirebaseAuth.getInstance().signOut();
                                    }
                                }, TIME_SPLASH);
                                Log.d(TAG, "createUserWithEmail:success");
                               startActivity(new Intent(getApplicationContext(), TMainActivity.class));
                                Toast.makeText(getApplicationContext(), "Umrani Tool Login Successful", Toast.LENGTH_LONG).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                if (progress.isShowing())
                                    progress.dismiss();
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Login Failed Click Gey Key", Toast.LENGTH_SHORT)
                                        .show();


                            }
                        }
                    });

        }

    }


    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }


    public static void start(@NotNull WelcomeActivity welcomeActivity) {
        
    }
}
