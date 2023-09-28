package com.umrani.tool.view.gms

import androidx.lifecycle.MutableLiveData
import com.umrani.tool.bean.GmsBean
import com.umrani.tool.bean.GmsInstallBean
import com.umrani.tool.data.GmsRepository
import com.umrani.tool.view.base.BaseViewModel

/**
 *
 * @Description: gms viewModel
 * @Author: BlackBox
 * @CreateDate: 2022/3/2 21:11
 */
class GmsViewModel(private val mRepo: GmsRepository) : BaseViewModel() {

    val mInstalledLiveData = MutableLiveData<List<GmsBean>>()

    val mUpdateInstalledLiveData = MutableLiveData<GmsInstallBean>()

    fun getInstalledUser() {
        launchOnUI {
            mRepo.getGmsInstalledList(mInstalledLiveData)
        }
    }

    fun installGms(userID: Int) {
        launchOnUI {
            mRepo.installGms(userID,mUpdateInstalledLiveData)
        }
    }

    fun uninstallGms(userID: Int) {
        launchOnUI {
            mRepo.uninstallGms(userID,mUpdateInstalledLiveData)
        }
    }
}