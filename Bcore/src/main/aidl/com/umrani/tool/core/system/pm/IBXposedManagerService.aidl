// IBXposedManagerService.aidl

package com.umrani.tool.core.system.pm;

import java.util.List;
import com.umrani.tool.entity.pm.InstalledModule;

interface IBXposedManagerService {
    boolean isXPEnable();
    void setXPEnable(boolean enable);
    boolean isModuleEnable(String packageName);
    void setModuleEnable(String packageName, boolean enable);
    List<InstalledModule> getInstalledModules();
}