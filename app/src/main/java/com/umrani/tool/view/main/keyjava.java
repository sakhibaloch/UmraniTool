package com.umrani.tool.view.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.umrani.tool.R;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

public class keyjava extends AppCompatActivity {

    private Context mContext;
    private LinearLayout layoutView;
    Button t1;
    Button t2;
    Button t3;
    Button t4;
    Button t5;
    Button t6;
    Button t7;
    Button t8;
    Button t9;
    TextView TextView;

    String GameID = "4679307";
    String ADID = "UmraniToolVideoAds";
    Boolean TestMode = false; // Change true to false if you are using Real Ads

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS, LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.key);





///////////////////unityADS///////////////////////
        // SDK Setting.....
        UnityAds.initialize(keyjava.this,GameID, TestMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

                Toast.makeText(keyjava.this, "Umrani Tool Services Working", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
                Toast.makeText(keyjava.this, "Umrani Tool Services Not Working", Toast.LENGTH_SHORT).show();
            }
        });
////////////////////////////////////////////////////////////////////


        t2=(Button)findViewById(R.id.t2);
        t2.setVisibility(View.GONE);

        t3=(Button)findViewById(R.id.t3);
        t3.setVisibility(View.GONE);

        t4=(Button)findViewById(R.id.t4);
        t4.setVisibility(View.GONE);

        t5=(Button)findViewById(R.id.t5);
        t5.setVisibility(View.GONE);

        t6=(Button)findViewById(R.id.t6);
        t6.setVisibility(View.GONE);

        TextView= (TextView) findViewById(R.id.TextView);
        TextView.setVisibility(View.GONE);






        Button t1 = findViewById(R.id.t1);
        t1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setVisibility(View.VISIBLE);
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
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
                UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
                Toast.makeText(keyjava.this, "TASK 1 COMPLETED✅", Toast.LENGTH_SHORT).show();
            }
        });



        Button t2 = findViewById(R.id.t2);
        t2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                t3.setVisibility(View.VISIBLE);
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
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
                UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
                Toast.makeText(keyjava.this, "TASK 2 COMPLETED✅", Toast.LENGTH_SHORT).show();
            }
        });

        Button t3 = findViewById(R.id.t3);
        t3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                t4.setVisibility(View.VISIBLE);
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
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
                UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
                Toast.makeText(keyjava.this, "TASK 3 COMPLETED✅", Toast.LENGTH_SHORT).show();
            }
        });

        Button t4 = findViewById(R.id.t4);
        t4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                t5.setVisibility(View.VISIBLE);
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
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
                UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
                Toast.makeText(keyjava.this, "TASK 4 COMPLETED✅", Toast.LENGTH_SHORT).show();
            }
        });

        Button t5 = findViewById(R.id.t5);
        t5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                t6.setVisibility(View.VISIBLE);
                TextView.setVisibility(View.VISIBLE);
                ////////////get text from firebase//////////////
                TextView=(TextView)findViewById(R.id.TextView);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LOADING");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String m=snapshot .getValue(String.class);
                        TextView.setText(m);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ///////////////////////////////////
                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
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
                UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
                Toast.makeText(keyjava.this, "LAST TASK COMPLETED✅", Toast.LENGTH_SHORT).show();
            }
        });

        Button t6 = findViewById(R.id.t6);
        t6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", TextView.getText());
                manager.setPrimaryClip(clipData);

                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                        UnityAds.load(ADID); // Add Placement ID
                        UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
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
                UnityAds.show(keyjava.this,ADID); // Context and Add Placement ID
                Toast.makeText(keyjava.this, "\uD83D\uDD13PASSWORD COPIED✅", Toast.LENGTH_SHORT).show();
            }
        });



    }}