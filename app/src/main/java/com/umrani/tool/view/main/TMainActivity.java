package com.umrani.tool.view.main;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.umrani.tool.BuildConfig;
import com.umrani.tool.R;
import com.umrani.tool.view.setting.SettingActivity;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class TMainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Context mContext;
    Context context;
    CardView boosram;
    TabLayout tabLayout;
    private Object CardView;
    String GameID = "4679307";
    String ADID = "UmraniToolVideoAds";
    Boolean TestMode = false; // Change true to false if you are using Real Ads
    //PERMISSION request constant, assign any value
    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final String TAG = "PERMISSION_TAG";
    private TextView versionTextView;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE = 10;
    public static int REQUEST_OVERLAY_PERMISSION = 5469;
    public TMainActivity() {
    }

    // @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmain_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        ///////////////////storage permissions//////////////
        // Check if the permission is already granted
        if (SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
               // startActivity(new Intent(this, TMainActivity.class));
            } else { //request for the permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        } else {
            //below android 11=======
           /// startActivity(new Intent(this, TMainActivity.class));
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }


///////////////////unityADS///////////////////////
        // SDK Setting.....
        UnityAds.initialize(TMainActivity.this,GameID, TestMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

                // Toast.makeText(MainActivity.this, "Umrani Tool Services Working", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
                Toast.makeText(TMainActivity.this, "Umrani Tool Services Not Working", Toast.LENGTH_SHORT).show();
            }
        });
////////////////////////////////////////////////////////////////////

        ///////////////load ads on timer///////////////////////
        int TIME_SPLASH = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID

                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        }, TIME_SPLASH);

//////////////////////////////////////////////////

        /////////sahre/////////////
        CardView Share = (CardView)findViewById(R.id.Share);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "Enjoy Best Tool for android mobile and best pubg hack latest very no ban. https://umranistore.blogspot.com/2020/10/umrani-gg-download.html";
                String sub = "Your Subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });
        /////////////////////////////////

        /////////rootcheck////////////
        CardView rootcheck = (CardView)findViewById(R.id.rootcheck);
        rootcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityRoot.class));
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        });
        /////////////////////////////////

        /////////virtualbtn//////////
        CardView virtualbtn = (CardView)findViewById(R.id.virtualbtn);
        virtualbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
        /////////////////////////////////

        /////////app about //////////
        CardView appabout = (CardView)findViewById(R.id.appabout);
        appabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),  AboutActivity.class));
            }
        });
        /////////////////////////////////

        /////////file manager//////////
        CardView filemgr = (CardView)findViewById(R.id.filemgr);
        filemgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),  fileActivity.class));
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        });
        /////////////////////////////////

        /////////DeviceInfo/////////////
        CardView dvsinfo = (CardView)findViewById(R.id.dvsinfo);
        dvsinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),  information_Activity.class));
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        });
        /////////////////////////////////

        /////////Whatsapp Hack //////////
        CardView whatsapp = (CardView)findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),  ActivityWhatsapp.class));
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        });
        /////////////////////////////////

        /////////Whatsapp Hack //////////
        CardView imeicheck = (CardView)findViewById(R.id.imeicheck);
        imeicheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),  otphack.class));
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        });
        /////////////////////////////////


        /////////Follow/////////////
        CardView follow = (CardView)findViewById(R.id.follow);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TMainActivity.this);
                builder.setTitle("FOLLOW ME ON SOCIAL MEDIA");
                builder.setInverseBackgroundForced(true);
                builder.setView(R.layout.gif_background);
                String[] animals = {"\uD83D\uDC8C JOIN ON TELEGRAM", "\uD83D\uDC8C SUBSCRIBE ON YOUTUBE", "\uD83D\uDC8C FOLLOW ON INSTAGRAM", "\uD83D\uDC8C FOLLOW ON TWITTER", "\uD83D\uDC8C FOLLOW ON TIKTOK"};
                builder.setItems(animals, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intentt = new Intent("android.intent.action.VIEW");
                                intentt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intentt.setData(Uri.parse("https://t.me/umranichannels"));
                                TMainActivity.this.getApplicationContext().startActivity(intentt);
                                Toast.makeText(TMainActivity.this, "\uD83D\uDC95JOIN CHANNEL\uD83D\uDC95", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Intent intenty = new Intent("android.intent.action.VIEW");
                                intenty.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intenty.setData(Uri.parse("https://www.youtube.com/channel/UC8XWwVIVrVziWiUgn0g-UiQ"));
                                TMainActivity.this.getApplicationContext().startActivity(intenty);
                                Toast.makeText(TMainActivity.this, "\uD83D\uDC95SUBSCRIBE CHANNEL\uD83D\uDC95", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Intent intenti = new Intent("android.intent.action.VIEW");
                                intenti.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intenti.setData(Uri.parse("https://www.instagram.com/sagheer_umrani"));
                                TMainActivity.this.getApplicationContext().startActivity(intenti);
                                Toast.makeText(TMainActivity.this, "\uD83D\uDC95FOLLOW INSTA\uD83D\uDC95", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Intent intenttw = new Intent("android.intent.action.VIEW");
                                intenttw.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intenttw.setData(Uri.parse("https://twitter.com/sagheer_umrani"));
                                TMainActivity.this.getApplicationContext().startActivity(intenttw);
                                Toast.makeText(TMainActivity.this, "\uD83D\uDC95FOLLOW TWITTER\uD83D\uDC95", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Intent intenttk = new Intent("android.intent.action.VIEW");
                                intenttk.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intenttk.setData(Uri.parse("https://www.tiktok.com/@sagheer_umrani?_t=8VPDN735QLS&_r=1"));
                                TMainActivity.this.getApplicationContext().startActivity(intenttk);
                                Toast.makeText(TMainActivity.this, "\uD83D\uDC95FOLLOW TIKTOK\uD83D\uDC95", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        /////////////////////////////////

        /////////StartTool/////////////
        CardView startbtn = (CardView)findViewById(R.id.startbtn);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getApplicationContext(), modloadActivity.class));
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
                    }
                    @Override
                    public void onUnityAdsShowStart(String s) {
                    }
                    @Override
                    public void onUnityAdsShowClick(String s) {
                    }
                    @Override
                    public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                    }
                };
                UnityAds.load(ADID); // Add Placement ID
                UnityAds.show(TMainActivity.this,ADID); // Context and Add Placement ID
            }
        });

        /////////////////////////////////

        ///////////ramClean//////////////
        CardView boosram = (CardView)findViewById(R.id.boosram);
        boosram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanRAM();
                MediaPlayer player = MediaPlayer.create(TMainActivity.this, R.raw.rocketsound);
                player.start();
                startActivity(new Intent(getApplicationContext(), boostr.class));
            }
        });
        ////////////////////////////////////////


        ///////////Stylish text//////////////
        CardView stylihtxt = (CardView)findViewById(R.id.stylihtxt);
        stylihtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SpecialCharacterActivity.class));
            }
        });
        ////////////////////////////////////////

        ///////////Settings//////////////
        CardView Settings = (CardView)findViewById(R.id.Settings);
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            }
        });
        ////////////////////////////////////////

        ////////////Update/////////////////
 /*       CardView updatebtn = (CardView)findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("https://umranistore.blogspot.com/"));
                TMainActivity.this.getApplicationContext().startActivity(intent);
                Toast.makeText(TMainActivity.this, "\uD83D\uDE0EUPDATE UMRANI TOOL\uD83D\uDE0E", Toast.LENGTH_SHORT).show();
            }
        });*/
        ///////////////////////////////////








    }

    private void cleanRAM() {
        // Close background processes
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            activityManager.killBackgroundProcesses(getPackageName());
        }

        // Clear cache
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            File cacheDir = new File(applicationInfo.dataDir + "/cache");
            if (cacheDir.isDirectory()) {
                File[] cacheFiles = cacheDir.listFiles();
                if (cacheFiles != null) {
                    for (File cacheFile : cacheFiles) {
                        cacheFile.delete();
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "RAM cleaned", Toast.LENGTH_SHORT).show();
    }
    public void ExecZLX(String path, String toastHZ) {
        try {
            ExecuteElf("chmod 777 " + getFilesDir() + path);
            ExecuteElf(getFilesDir() + path);
            ExecuteElf("su -c chmod 777 " + getFilesDir() + path);
            ExecuteElf("su -c " + getFilesDir() + path);

            Toast.makeText(TMainActivity.this, toastHZ, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                // You can perform your desired action here
            } else {
                // Permission denied
                // Handle the situation where the user denied the permission
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                // Check if the MANAGE_EXTERNAL_STORAGE permission is granted after the settings activity
                if (Environment.isExternalStorageManager()) {
                    // Permission granted
                    // Start your activity or perform the action that requires storage permission
                } else {
                    // Permission denied
                    // Handle the situation where the user denied the permission
                }
            }
        }
    }

    public static void start(@NotNull WelcomeActivity welcomeActivity) {

    }

    @Override
    public void onBackPressed() {
        showConfirmationDialog();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit umrani tool?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Finish the activity and exit the app
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and continue with the current activity
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}