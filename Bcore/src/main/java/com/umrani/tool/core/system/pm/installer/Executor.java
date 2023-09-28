package com.umrani.tool.core.system.pm.installer;

import com.umrani.tool.core.system.pm.BPackageSettings;
import com.umrani.tool.entity.pm.InstallOption;

/**
 * Created by Milk on 4/24/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public interface Executor {
    public static final String TAG = "InstallExecutor";

    int exec(BPackageSettings ps, InstallOption option, int userId);
}
