package com.umrani.tool.core.system.pm.installer;

import com.umrani.tool.core.env.BEnvironment;
import com.umrani.tool.core.system.pm.BPackageSettings;
import com.umrani.tool.entity.pm.InstallOption;
import com.umrani.tool.utils.FileUtils;

/**
 * Created by Milk on 4/27/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class RemoveAppExecutor implements Executor {
    @Override
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        FileUtils.deleteDir(BEnvironment.getAppDir(ps.pkg.packageName));
        return 0;
    }
}
