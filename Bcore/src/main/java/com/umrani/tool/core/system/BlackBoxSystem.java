package com.umrani.tool.core.system;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.umrani.tool.BlackBoxCore;
import com.umrani.tool.core.env.AppSystemEnv;
import com.umrani.tool.core.env.BEnvironment;
import com.umrani.tool.core.system.accounts.BAccountManagerService;
import com.umrani.tool.core.system.am.BActivityManagerService;
import com.umrani.tool.core.system.am.BJobManagerService;
import com.umrani.tool.core.system.location.BLocationManagerService;
import com.umrani.tool.core.system.notification.BNotificationManagerService;
import com.umrani.tool.core.system.os.BStorageManagerService;
import com.umrani.tool.core.system.pm.BPackageInstallerService;
import com.umrani.tool.core.system.pm.BPackageManagerService;
import com.umrani.tool.core.system.pm.BXposedManagerService;
import com.umrani.tool.core.system.user.BUserHandle;
import com.umrani.tool.core.system.user.BUserManagerService;
import com.umrani.tool.entity.pm.InstallOption;
import com.umrani.tool.utils.FileUtils;

import static com.umrani.tool.core.env.BEnvironment.EMPTY_JAR;
import static com.umrani.tool.core.env.BEnvironment.JUNIT_JAR;

/**
 * Created by Milk on 4/22/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class BlackBoxSystem {
    private static BlackBoxSystem sBlackBoxSystem;
    private final List<ISystemService> mServices = new ArrayList<>();
    private final static AtomicBoolean isStartup = new AtomicBoolean(false);

    public static BlackBoxSystem getSystem() {
        if (sBlackBoxSystem == null) {
            synchronized (BlackBoxSystem.class) {
                if (sBlackBoxSystem == null) {
                    sBlackBoxSystem = new BlackBoxSystem();
                }
            }
        }
        return sBlackBoxSystem;
    }

    public void startup() {
        if (isStartup.getAndSet(true))
            return;
        BEnvironment.load();

        mServices.add(BPackageManagerService.get());
        mServices.add(BUserManagerService.get());
        mServices.add(BActivityManagerService.get());
        mServices.add(BJobManagerService.get());
        mServices.add(BStorageManagerService.get());
        mServices.add(BPackageInstallerService.get());
        mServices.add(BXposedManagerService.get());
        mServices.add(BProcessManagerService.get());
        mServices.add(BAccountManagerService.get());
        mServices.add(BLocationManagerService.get());
        mServices.add(BNotificationManagerService.get());

        for (ISystemService service : mServices) {
            service.systemReady();
        }

        List<String> preInstallPackages = AppSystemEnv.getPreInstallPackages();
        for (String preInstallPackage : preInstallPackages) {
            try {
                if (!BPackageManagerService.get().isInstalled(preInstallPackage, BUserHandle.USER_ALL)) {
                    PackageInfo packageInfo = BlackBoxCore.getPackageManager().getPackageInfo(preInstallPackage, 0);
                    BPackageManagerService.get().installPackageAsUser(packageInfo.applicationInfo.sourceDir, InstallOption.installBySystem(), BUserHandle.USER_ALL);
                }
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
        initJarEnv();
    }

    private void initJarEnv() {
        try {
            InputStream junit = BlackBoxCore.getContext().getAssets().open("junit.jar");
            FileUtils.copyFile(junit, JUNIT_JAR);

            InputStream empty = BlackBoxCore.getContext().getAssets().open("empty.jar");
            FileUtils.copyFile(empty, EMPTY_JAR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
