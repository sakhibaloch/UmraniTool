package com.umrani.tool.view.list

import androidx.lifecycle.MutableLiveData
import com.umrani.tool.bean.InstalledAppBean
import com.umrani.tool.data.AppsRepository
import com.umrani.tool.view.base.BaseViewModel

/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/4/29 22:36
 */
class ListViewModel(private val repo: AppsRepository) : BaseViewModel() {

    val appsLiveData = MutableLiveData<List<InstalledAppBean>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    fun previewInstalledList() {
        launchOnUI{
            repo.previewInstallList()
        }
    }

    fun getInstallAppList(userID:Int){
        launchOnUI {
            repo.getInstalledAppList(userID,loadingLiveData,appsLiveData)
        }
    }

    fun getInstalledModules() {
        launchOnUI {
            repo.getInstalledModuleList(loadingLiveData, appsLiveData)
        }
    }

}