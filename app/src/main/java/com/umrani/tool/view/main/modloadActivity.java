package com.umrani.tool.view.main;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.umrani.tool.R;
import com.umrani.tool.view.setting.SettingActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import pl.droidsonroids.gif.GifImageView;


public class modloadActivity extends AppCompatActivity {

    private Context mContext;
    Context context;
    Button open;
    private RecyclerView recyclerView;
    private GifImageView winners_btn;
    private ImageView whats_btn;
    private ImageView yt_btn;
    private ImageView video_btn;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    // button to show progress dialog
    Button btnShowProgress;
    // Progress Dialog
    private ProgressDialog pDialog;
    ImageView my_image;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    // File url to download
    private static String file_url = "https://github.com/sakhibaloch/UmraniChannels/blob/main/UmraniChannels?raw=true";

    private static final String TAG = "PERMISSION_TAG";
    public static int REQUEST_OVERLAY_PERMISSION = 5469;
    /////////gl
    private void StartFloatingService() {
        startService(new Intent(this, FloatingActivity.class));
    }
    private void StopFloatingService() {
        stopService(new Intent(this, FloatingActivity.class));
    }

    //////////bgmi
    private void StartFloatingServicBgmi() {
        startService(new Intent(this, BgmiFloatingActivity.class));
    }
    private void StopFloatingServiceBgmi() {
        stopService(new Intent(this, BgmiFloatingActivity.class));
    }

/////////lite

    //////////////8ball Pool
    private void StartFloatingServicBall() {
        startService(new Intent(this, BallPoolFloatingActivity.class));
    }
    private void StopFloatingServiceBall() {
        stopService(new Intent(this, BallPoolFloatingActivity.class));
    }

    private String path;
    private  String absolutePath;

    TextView gnametxtgl, pgversiontxtgl, pgstatustxtgl, gnametxtind, pgversiontxtind, pgstatustxtind,gnametxt8bp, pgversiontxt8bp, pgstatustxt8bp;
    ImageView imageViewgl,imageViewind,imageView8bp;
    private StorageReference storageReference;

    public modloadActivity() throws FileNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.modload);
        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        new DownloadFileFromURL().execute(file_url);
        ///////////////////google firebae get data/////////////
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FloatingPermissions();
        ///////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////pubg india info//////////////
        gnametxtind = findViewById(R.id.gnametxtind);
        pgversiontxtind = findViewById(R.id.pgversiontxtind);
        pgstatustxtind = findViewById(R.id.pgstatustxtind);
        imageViewind= findViewById(R.id.imageViewind);
        DatabaseReference gnametxtIND = database.getReference("GamesInfo/gnametxtind");
        gnametxtIND.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                gnametxtind.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference pgversiontxtIND = database.getReference("GamesInfo/pgversiontxtind");
        pgversiontxtIND.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                pgversiontxtind.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference pgstatustxtIND = database.getReference("GamesInfo/pgstatustxtind");
        pgstatustxtIND.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                pgstatustxtind.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        StorageReference imageRefIND = storageReference.child("GameImages/pubgindia.jpg");
        imageRefIND.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get()
                        .load(uri.toString())
                        .fit()
                        .centerCrop()
                        .into(imageViewind, new Callback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(modloadActivity.this, "Game loaded successfully", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(modloadActivity.this, "Error loading Game: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }});
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(modloadActivity.this, "Error retrieving download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////All pubg global info//////////////
        gnametxtgl = findViewById(R.id.gnametxtgl);
        pgversiontxtgl = findViewById(R.id.pgversiontxtgl);
        pgstatustxtgl = findViewById(R.id.pgstatustxtgl);
        imageViewgl= findViewById(R.id.imageViewgl);
        DatabaseReference gnametxtGL = database.getReference("GamesInfo/gnametxtgl");
        gnametxtGL.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                gnametxtgl.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference pgversiontxtGL = database.getReference("GamesInfo/pgversiontxtgl");
        pgversiontxtGL.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                pgversiontxtgl.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference pgstatustxtGL = database.getReference("GamesInfo/pgstatustxtgl");
        pgstatustxtGL.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                pgstatustxtgl.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        StorageReference imageRefGL = storageReference.child("GameImages/pubgglobal.jpg");
        imageRefGL.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get()
                        .load(uri.toString())
                        .fit()
                        .centerCrop()
                        .into(imageViewgl, new Callback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(modloadActivity.this, "Game loaded successfully", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(modloadActivity.this, "Error loading Game: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }});
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(modloadActivity.this, "Error retrieving download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////All pubg global info//////////////
        gnametxt8bp = findViewById(R.id.gnametxt8bp);
        pgversiontxt8bp = findViewById(R.id.pgversiontxt8bp);
        pgstatustxt8bp = findViewById(R.id.pgstatustxt8bp);
        imageView8bp= findViewById(R.id.imageView8bp);
        DatabaseReference gnametxt8B = database.getReference("GamesInfo/gnametxt8bp");
        gnametxt8B.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                gnametxt8bp.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference pgversiontxt8B = database.getReference("GamesInfo/pgversiontxt8bp");
        pgversiontxt8B.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                pgversiontxt8bp.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference pgstatustxt8B = database.getReference("GamesInfo/pgstatustxt8bp");
        pgstatustxt8B.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);
                pgstatustxt8bp.setText(m);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        StorageReference imageRef8B = storageReference.child("GameImages/8ballpool.jpg");
        imageRef8B.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get()
                        .load(uri.toString())
                        .fit()
                        .centerCrop()
                        .into(imageView8bp, new Callback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(modloadActivity.this, "Game loaded successfully", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(modloadActivity.this, "Error loading Game: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }});
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(modloadActivity.this, "Error retrieving download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        ///////////Update//////////////
        ImageView update = (ImageView) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("https://umranistore.blogspot.com/"));
                modloadActivity.this.getApplicationContext().startActivity(intent);
                Toast.makeText(modloadActivity.this, "\uD83D\uDE0EUPDATE UMRANI TOOL\uD83D\uDE0E", Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////////////////////

        ///////////All pubg//////////////
        ConstraintLayout constraintLayoutgl = (ConstraintLayout)findViewById(R.id.constraintLayoutgl);
        constraintLayoutgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartFloatingService();
            }
        });
        ////////////////////////////////////////

        ///////////Bgmi//////////////
        ConstraintLayout constraintLayoutind = (ConstraintLayout)findViewById(R.id.constraintLayoutind);
        constraintLayoutind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartFloatingServicBgmi();
            }
        });
        ////////////////////////////////////////

        ///////////8 ball pool//////////////
        ConstraintLayout constraintLayout8bp = (ConstraintLayout)findViewById(R.id.constraintLayout8bp);
        constraintLayout8bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartFloatingServicBall();
            }
        });
        ////////////////////////////////////////


    }

///////////////////////////////Downloading ProogressBar//////////////////////////////////////////
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Loading New Files Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////Download file///////////////////////////////
    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String folder_main = "UmraniHacksFiles";
                File f = new File(Environment.getExternalStorageDirectory(), folder_main);
                if (!f.exists()) {
                    f.mkdirs();
                }
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                // Output stream to write file
                OutputStream output = new FileOutputStream("/data/data/com.umrani.tool/files/UmraniChannels");
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));
                    // writing data to file
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Override
    protected void onStart() {
        super.onStart();
    }

    private void FloatingPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setMessage(
                        "💙TELEGRAM: @UMRANICHANNELS\n💕JOIN FOR DAILY UPDATES\n\n➡️CLICK OK AND PlEASE ENABLE 1ST ALL PERMISSIONS↘️.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface p1, int p2) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    FloatingPermissions();
                }
            }
        }
    }




    }