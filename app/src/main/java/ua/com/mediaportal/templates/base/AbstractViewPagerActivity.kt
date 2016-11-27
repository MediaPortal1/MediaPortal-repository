package ua.com.mediaportal.templates.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import ua.com.mediaportal.adapters.FragmentViewPagerAdapter
import ua.com.mediaportal.templates.AbstractBaseActivity
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_VIEWPAGER

abstract class AbstractViewPagerActivity : AbstractBaseActivity() {

    val pagesAdapter: FragmentViewPagerAdapter by lazy { getViewPagerAdapter() }

    override fun isDrawerEnabled() = false

    override fun getContentType() = CONTENT_VIEWPAGER

    override fun initSetting() {
        super.initSetting()
        initViewPager()
    }

    fun initViewPager(){
        viewPager?.adapter = pagesAdapter
    }

    protected fun getViewPagerAdapter() = FragmentViewPagerAdapter(supportFragmentManager,getPages())

    abstract fun getPages(): List<Fragment>

}
