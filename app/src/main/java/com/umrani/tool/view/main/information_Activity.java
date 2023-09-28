package com.umrani.tool.view.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.umrani.tool.R;

public class information_Activity extends Activity {

    // text view to display information
    private TextView textViewSetInformation;
    private TextView tv1;
    private TextView tv2;


    // button to get information
    private Button buttonGetInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.information);
        //initializing the views
        initViews();
        // initializing the listeners
        initListeners();





    }

    /**
     * method to initialize the views
     */
    private void initViews() {

        textViewSetInformation = (TextView) findViewById(R.id.textViewSetInformation);

        buttonGetInformation = (Button) findViewById(R.id.buttonGetInformation);

    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        buttonGetInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get hardware and software information
                String information = getHardwareAndSoftwareInfo();

                // set information to text view
                textViewSetInformation.setText(information);

            }
        });

    }

    /**
     * method to fetch hardware and software information
     *
     * @return information
     */
    private String getHardwareAndSoftwareInfo() {

        return
                        getString(R.string.manufacturer) + " " + Build.MANUFACTURER + "\n\n" +
                        getString(R.string.brand) + " " + Build.BRAND + "\n\n" +
                        getString(R.string.model) + " " + Build.MODEL + "\n\n" +
                        getString(R.string.versioncode) + " " + Build.VERSION.RELEASE +"\n\n" +
                        getString(R.string.serial) + " " + Build.SERIAL + "\n\n" +
                        getString(R.string.incremental) + " " + Build.VERSION.INCREMENTAL + "\n\n" +
                        getString(R.string.board) + " " + Build.BOARD + "\n\n" +
                        getString(R.string.sdk) + " " + Build.VERSION.SDK + "\n\n" +
                        getString(R.string.id) + " " + Build.ID + "\n\n" +
                        getString(R.string.fingerprint) + " " + Build.FINGERPRINT +"\n\n" +
                        getString(R.string.host) + " " + Build.HOST + "\n\n" +
                        getString(R.string.typee) + " " + Build.TYPE + "\n\n" +
                        getString(R.string.user) + " " + Build.USER + "\n\n" +
                        getString(R.string.base) + " " + Build.VERSION_CODES.BASE + "\n\n" ;

    }



}
