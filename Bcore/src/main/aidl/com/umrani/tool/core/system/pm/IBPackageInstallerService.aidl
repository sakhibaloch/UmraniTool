// IBPackageInstallerService.aidl
package com.umrani.tool.core.system.pm;

import com.umrani.tool.core.system.pm.BPackageSettings;
import com.umrani.tool.entity.pm.InstallOption;

// Declare any non-default types here with import statements

interface IBPackageInstallerService {
    int installPackageAsUser(in BPackageSettings ps, int userId);
    int uninstallPackageAsUser(in BPackageSettings ps, boolean removeApp, int userId);
    int clearPackage(in BPackageSettings ps, int userId);
    int updatePackage(in BPackageSettings ps);
}
