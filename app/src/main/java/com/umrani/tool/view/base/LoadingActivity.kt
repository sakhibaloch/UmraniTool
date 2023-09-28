package com.umrani.tool.view.base

import android.view.KeyEvent
import androidx.drawerlayout.widget.DrawerLayout
import com.roger.catloadinglibrary.CatLoadingView
import com.umrani.tool.R

/**
 *
 * @Description: loading activity
 * @Author: BlackBox
 * @CreateDate: 2022/3/2 21:49
 */
abstract class LoadingActivity : BaseActivity() {



    private lateinit var loadingView: CatLoadingView


    fun showLoading() {
        if (!this::loadingView.isInitialized) {
            loadingView = CatLoadingView()
        }

        if (!loadingView.isAdded) {
            loadingView.setBackgroundColor(R.color.primary)
            loadingView.show(supportFragmentManager, "")
            supportFragmentManager.executePendingTransactions()
            loadingView.setClickCancelAble(false)
            loadingView.dialog?.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
                    return@setOnKeyListener true
                }
                false
            }
        }
    }


    fun hideLoading() {
        if (this::loadingView.isInitialized) {
            loadingView.dismiss()
        }
    }
}