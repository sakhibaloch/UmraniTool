package com.umrani.tool.view.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.umrani.tool.BlackBoxCore
import com.umrani.tool.R
import com.umrani.tool.app.App
import com.umrani.tool.app.AppManager
import com.umrani.tool.databinding.ActivityMainBinding
import com.umrani.tool.util.Resolution
import com.umrani.tool.util.inflate
import com.umrani.tool.view.apps.AppsFragment
import com.umrani.tool.view.base.LoadingActivity
import com.umrani.tool.view.list.ListActivity


class MainActivity : LoadingActivity() {

    private val viewBinding: ActivityMainBinding by inflate()

    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    private val fragmentList = mutableListOf<AppsFragment>()

    private var currentUser = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private val navigation_view: NavigationView? = null


    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //initToolbar(viewBinding.toolbarLayout.toolbar, R.string.app_name)
        initViewPager()
        initFab()
        // initToolbarSubTitle()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.TRANSPARENT

        setSupportActionBar(viewBinding.toolbaar)


    }



    /*    private fun initToolbarSubTitle() {
            updateUserRemark(0)
            //hack code
            viewBinding.toolbarLayout.toolbar.getChildAt(1).setOnClickListener {
                MaterialDialog(this).show {
                    title(res = R.string.userRemark)
                    input(
                        hintRes = R.string.userRemark,
                        prefill = viewBinding.toolbarLayout.toolbar.subtitle
                    ) { _, input ->
                        AppManager.mRemarkSharedPreferences.edit {
                            putString("Remark$currentUser", input.toString())
                            viewBinding.toolbarLayout.toolbar.subtitle = input
                        }
                    }
                    positiveButton(res = R.string.done)
                    negativeButton(res = R.string.cancel)
                }
            }
        }*/

    private fun initViewPager() {

        val userList = BlackBoxCore.get().users
        userList.forEach {
            fragmentList.add(AppsFragment.newInstance(it.id))
        }

        currentUser = userList.firstOrNull()?.id ?: 0
        fragmentList.add(AppsFragment.newInstance(userList.size))

        mViewPagerAdapter = ViewPagerAdapter(this)
        mViewPagerAdapter.replaceData(fragmentList)
        viewBinding.viewPager.adapter = mViewPagerAdapter
        viewBinding.dotsIndicator.setViewPager2(viewBinding.viewPager)
        viewBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentUser = fragmentList[position].userID
                updateUserRemark(currentUser)
                showFloatButton(true)
            }
        })

    }

    private fun initFab() {
        viewBinding.fab.setOnClickListener {
            val userId = viewBinding.viewPager.currentItem
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("userID", userId)
            apkPathResult.launch(intent)
        }
    }

    fun showFloatButton(show: Boolean) {
        val tranY: Float = Resolution.convertDpToPixel(120F, App.getContext())
        val time = 200L
        if (show) {
            viewBinding.fab.animate().translationY(0f).alpha(1f).setDuration(time)
                .start()
        } else {
            viewBinding.fab.animate().translationY(tranY).alpha(0f).setDuration(time)
                .start()
        }
    }

    fun scanUser() {
        val userList = BlackBoxCore.get().users

        if (fragmentList.size == userList.size) {
            fragmentList.add(AppsFragment.newInstance(fragmentList.size))
        } else if (fragmentList.size > userList.size + 1) {
            fragmentList.removeLast()
        }

        mViewPagerAdapter.notifyDataSetChanged()

    }

    private fun updateUserRemark(userId: Int) {
        var remark = AppManager.mRemarkSharedPreferences.getString("Remark$userId", "User $userId")
        if (remark.isNullOrEmpty()) {
            remark = "User $userId"
        }

        // viewBinding.toolbarLayout.toolbar.subtitle = remark
    }

    private val apkPathResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                it.data?.let { data ->
                    val userId = data.getIntExtra("userID", 0)
                    val source = data.getStringExtra("source")
                    if (source != null) {
                        fragmentList[userId].installApk(source)
                    }
                }

            }
        }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }*/

    /*    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.main_setting -> {
                    SettingActivity.start(this)
                }
                R.id.fake_location -> {
    //                toast("Still Developing")
                    val intent = Intent(this, FakeManagerActivity::class.java)
                    intent.putExtra("userID", 0)
                    startActivity(intent)
                }
            }

            return true
        }*/

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = binding.drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val drawerLayout: DrawerLayout = binding.drawer
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            if (drawer?.isDrawerOpen(GravityCompat.START) == true) {
                drawer!!.closeDrawer(GravityCompat.START)
            } else {
                drawer?.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }*/


}