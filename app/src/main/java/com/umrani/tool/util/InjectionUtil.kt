package com.umrani.tool.util

import com.umrani.tool.data.AppsRepository
import com.umrani.tool.data.FakeLocationRepository
import com.umrani.tool.data.GmsRepository
import com.umrani.tool.data.XpRepository
import com.umrani.tool.view.apps.AppsFactory
import com.umrani.tool.view.fake.FakeLocationFactory
import com.umrani.tool.view.gms.GmsFactory
import com.umrani.tool.view.list.ListFactory
import com.umrani.tool.view.xp.XpFactory

/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/4/29 22:38
 */
object InjectionUtil {

    private val appsRepository = AppsRepository()

    private val xpRepository = XpRepository()

    private val gmsRepository = GmsRepository()

    private val fakeLocationRepository = FakeLocationRepository()

    fun getAppsFactory() : AppsFactory {
        return AppsFactory(appsRepository)
    }

    fun getListFactory(): ListFactory {
        return ListFactory(appsRepository)
    }

    fun getXpFactory():XpFactory{
        return XpFactory(xpRepository)
    }

    fun getGmsFactory():GmsFactory{
        return GmsFactory(gmsRepository)
    }

    fun getFakeLocationFactory():FakeLocationFactory{
        return FakeLocationFactory(fakeLocationRepository)
    }
}