package com.umrani.tool.view.main;

import android.content.Context;
import android.content.Intent;

import androidx.constraintlayout.solver.widgets.Helper;
import androidx.core.content.ContextCompat;

import com.umrani.tool.ViewService;

public class Control {
    static void start(Context context) {
//        Helper.startGame(context);
        ContextCompat.startForegroundService(context, new Intent(context, ViewService.class));
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, ViewService.class));
    }
}
