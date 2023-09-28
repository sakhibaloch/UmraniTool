package com.umrani.tool.view.fake

import androidx.lifecycle.MutableLiveData
import com.umrani.tool.entity.location.BLocation
import com.umrani.tool.bean.FakeLocationBean
import com.umrani.tool.data.FakeLocationRepository
import com.umrani.tool.view.base.BaseViewModel

/**
 *
 * @Author: BlackBoxing
 * @CreateDate: 2022/3/14
 */
class FakeLocationViewModel(private val mRepo: FakeLocationRepository) : BaseViewModel() {

    val appsLiveData = MutableLiveData<List<FakeLocationBean>>()


    fun getInstallAppList(userID: Int) {
        launchOnUI {
            mRepo.getInstalledAppList(userID, appsLiveData)
        }
    }

    fun setPattern(userId: Int, pkg: String, pattern: Int) {
        launchOnUI {
            mRepo.setPattern(userId, pkg, pattern)
        }
    }

    fun setLocation(userId: Int, pkg: String, location: BLocation) {
        launchOnUI {
            mRepo.setLocation(userId, pkg, location)
        }
    }

}