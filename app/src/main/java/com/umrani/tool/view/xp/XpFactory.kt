package com.umrani.tool.view.xp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umrani.tool.data.XpRepository

/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/5/2 20:56
 */
@Suppress("UNCHECKED_CAST")
class XpFactory(private val repo:XpRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return XpViewModel(repo) as T
    }
}