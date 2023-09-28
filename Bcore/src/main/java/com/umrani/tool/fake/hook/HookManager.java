package com.umrani.tool.fake.hook;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import com.umrani.tool.BlackBoxCore;
import com.umrani.tool.fake.delegate.AppInstrumentation;
import com.umrani.tool.fake.service.HCallbackProxy;
import com.umrani.tool.fake.service.IAccessibilityManagerProxy;
import com.umrani.tool.fake.service.IAccountManagerProxy;
import com.umrani.tool.fake.service.IActivityClientProxy;
import com.umrani.tool.fake.service.IActivityManagerProxy;
import com.umrani.tool.fake.service.IActivityTaskManagerProxy;
import com.umrani.tool.fake.service.IAlarmManagerProxy;
import com.umrani.tool.fake.service.IAppOpsManagerProxy;
import com.umrani.tool.fake.service.IAppWidgetManagerProxy;
import com.umrani.tool.fake.service.IAutofillManagerProxy;
import com.umrani.tool.fake.service.IConnectivityManagerProxy;
import com.umrani.tool.fake.service.IContextHubServiceProxy;
import com.umrani.tool.fake.service.IDeviceIdentifiersPolicyProxy;
import com.umrani.tool.fake.service.IDevicePolicyManagerProxy;
import com.umrani.tool.fake.service.IDisplayManagerProxy;
import com.umrani.tool.fake.service.IFingerprintManagerProxy;
import com.umrani.tool.fake.service.IGraphicsStatsProxy;
import com.umrani.tool.fake.service.IJobServiceProxy;
import com.umrani.tool.fake.service.ILauncherAppsProxy;
import com.umrani.tool.fake.service.ILocationManagerProxy;
import com.umrani.tool.fake.service.IMediaRouterServiceProxy;
import com.umrani.tool.fake.service.IMediaSessionManagerProxy;
import com.umrani.tool.fake.service.INetworkManagementServiceProxy;
import com.umrani.tool.fake.service.INotificationManagerProxy;
import com.umrani.tool.fake.service.IPackageManagerProxy;
import com.umrani.tool.fake.service.IPermissionManagerProxy;
import com.umrani.tool.fake.service.IPersistentDataBlockServiceProxy;
import com.umrani.tool.fake.service.IPhoneSubInfoProxy;
import com.umrani.tool.fake.service.IPowerManagerProxy;
import com.umrani.tool.fake.service.IShortcutManagerProxy;
import com.umrani.tool.fake.service.IStorageManagerProxy;
import com.umrani.tool.fake.service.IStorageStatsManagerProxy;
import com.umrani.tool.fake.service.ISystemUpdateProxy;
import com.umrani.tool.fake.service.ITelephonyManagerProxy;
import com.umrani.tool.fake.service.ITelephonyRegistryProxy;
import com.umrani.tool.fake.service.IUserManagerProxy;
import com.umrani.tool.fake.service.IVibratorServiceProxy;
import com.umrani.tool.fake.service.IVpnManagerProxy;
import com.umrani.tool.fake.service.IWifiManagerProxy;
import com.umrani.tool.fake.service.IWifiScannerProxy;
import com.umrani.tool.fake.service.IWindowManagerProxy;
import com.umrani.tool.fake.service.context.ContentServiceStub;
import com.umrani.tool.fake.service.context.RestrictionsManagerStub;
import com.umrani.tool.fake.service.libcore.OsStub;
import com.umrani.tool.utils.Slog;
import com.umrani.tool.utils.compat.BuildCompat;

/**
 * Created by Milk on 3/30/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class HookManager {
    public static final String TAG = "HookManager";

    private static final HookManager sHookManager = new HookManager();

    private final Map<Class<?>, IInjectHook> mInjectors = new HashMap<>();

    public static HookManager get() {
        return sHookManager;
    }

    public void init() {
        if (BlackBoxCore.get().isBlackProcess() || BlackBoxCore.get().isServerProcess()) {
            addInjector(new IDisplayManagerProxy());
            addInjector(new OsStub());
            addInjector(new IActivityManagerProxy());
            addInjector(new IPackageManagerProxy());
            addInjector(new ITelephonyManagerProxy());
            addInjector(new HCallbackProxy());
            addInjector(new IAppOpsManagerProxy());
            addInjector(new INotificationManagerProxy());
            addInjector(new IAlarmManagerProxy());
            addInjector(new IAppWidgetManagerProxy());
            addInjector(new ContentServiceStub());
            addInjector(new IWindowManagerProxy());
            addInjector(new IUserManagerProxy());
            addInjector(new RestrictionsManagerStub());
            addInjector(new IMediaSessionManagerProxy());
            addInjector(new ILocationManagerProxy());
            addInjector(new IStorageManagerProxy());
            addInjector(new ILauncherAppsProxy());
            addInjector(new IJobServiceProxy());
            addInjector(new IAccessibilityManagerProxy());
            addInjector(new ITelephonyRegistryProxy());
            addInjector(new IDevicePolicyManagerProxy());
            addInjector(new IAccountManagerProxy());
            addInjector(new IConnectivityManagerProxy());
            addInjector(new IPhoneSubInfoProxy());
            addInjector(new IMediaRouterServiceProxy());
            addInjector(new IPowerManagerProxy());
            addInjector(new IContextHubServiceProxy());
            addInjector(new IVibratorServiceProxy());
            addInjector(new IPersistentDataBlockServiceProxy());
            addInjector(AppInstrumentation.get());
            /*
            * It takes time to test and enhance the compatibility of WifiManager
            * (only tested in Android 10).
            * commented by BlackBoxing at 2022/03/08
            * */
            addInjector(new IWifiManagerProxy());
            addInjector(new IWifiScannerProxy());
            // 12.0
            if (BuildCompat.isS()) {
                addInjector(new IActivityClientProxy(null));
                addInjector(new IVpnManagerProxy());
            }
            // 11.0
            if (BuildCompat.isR()) {
                addInjector(new IPermissionManagerProxy());
            }
            // 10.0
            if (BuildCompat.isQ()) {
                addInjector(new IActivityTaskManagerProxy());
            }
            // 9.0
            if (BuildCompat.isPie()) {
                addInjector(new ISystemUpdateProxy());
            }
            // 8.0
            if (BuildCompat.isOreo()) {
                addInjector(new IAutofillManagerProxy());
                addInjector(new IDeviceIdentifiersPolicyProxy());
                addInjector(new IStorageStatsManagerProxy());
            }
            // 7.1
            if (BuildCompat.isN_MR1()) {
                addInjector(new IShortcutManagerProxy());
            }
            // 7.0
            if (BuildCompat.isN()) {
                addInjector(new INetworkManagementServiceProxy());
            }
            // 6.0
            if (BuildCompat.isM()) {
                addInjector(new IFingerprintManagerProxy());
                addInjector(new IGraphicsStatsProxy());
            }
            // 5.0
            if (BuildCompat.isL()) {
                addInjector(new IJobServiceProxy());
            }
        }
        injectAll();
    }

    public void checkEnv(Class<?> clazz) {
        IInjectHook iInjectHook = mInjectors.get(clazz);
        if (iInjectHook != null && iInjectHook.isBadEnv()) {
            Log.d(TAG, "checkEnv: " + clazz.getSimpleName() + " is bad env");
            iInjectHook.injectHook();
        }
    }

    public void checkAll() {
        for (Class<?> aClass : mInjectors.keySet()) {
            IInjectHook iInjectHook = mInjectors.get(aClass);
            if (iInjectHook != null && iInjectHook.isBadEnv()) {
                Log.d(TAG, "checkEnv: " + aClass.getSimpleName() + " is bad env");
                iInjectHook.injectHook();
            }
        }
    }

    void addInjector(IInjectHook injectHook) {
        mInjectors.put(injectHook.getClass(), injectHook);
    }

    void injectAll() {
        for (IInjectHook value : mInjectors.values()) {
            try {
                Slog.d(TAG, "hook: " + value);
                value.injectHook();
            } catch (Exception e) {
                Slog.d(TAG, "hook error: " + value);
            }
        }
    }
}
