package ua.com.mediaportal.templates

import android.support.annotation.LayoutRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_fab.*
import kotlinx.android.synthetic.main.layout_recycler.*
import kotlinx.android.synthetic.main.layout_viewpager.*
import ua.com.mediaportal.R
import ua.com.mediaportal.templates.ActivitySettings.ACTIVITY_TOOLBAR
import ua.com.mediaportal.templates.ActivitySettings.ACTIVITY_TOOLBAR_DRAWER
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_CUSTOM
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_RECYCLER
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_VIEWPAGER


abstract class AbstractBuilderActivity : AbstractToolbarActivity() {

    /*** VIEW ELEMENTS***/
    protected lateinit var fab_btn: FloatingActionButton
    protected lateinit var recycler : RecyclerView
    protected lateinit var viewpager : ViewPager

    override fun initSetting() {
        super.initSetting()
        initDefaultViews()
    }

    private fun initDefaultViews() {
        if (isFABEnabled()) {
            fab_btn = floating_action_button_view
            initFloatingActionBtn()
        }
        if(isRecylcerEnabled()) recycler = recycler_view
        if(isViewPagerEnabled())viewpager = viewpager_view

    }

    abstract protected fun getActivityType(): Int

    override fun getHolderLayout() = when (getActivityType()) {
        ACTIVITY_TOOLBAR -> R.layout.holder_toolbar
        ACTIVITY_TOOLBAR_DRAWER -> R.layout.holder_drawer
        else -> getCustomHolderLayout()
    }

    @LayoutRes protected fun getCustomHolderLayout(): Int = R.layout.holder_toolbar

    override fun getContentLayout() = when {
        isContentCustom() || getActivityType() == CONTENT_CUSTOM -> getCustomContentLayout()
        isFABEnabled() -> when (getContentType()) {
            CONTENT_RECYCLER -> R.layout.content_fab_recycler
            CONTENT_VIEWPAGER -> R.layout.content_fab_viewpager
            else -> R.layout.content_fab_blank
        }
        else -> when (getContentType()) {
            CONTENT_RECYCLER -> R.layout.content_recycler
            CONTENT_VIEWPAGER -> R.layout.content_viewpager
            else -> R.layout.content_blank
        }

    }

    abstract protected fun getContentType(): Int


    protected fun isContentCustom() = false

    @LayoutRes protected fun getCustomContentLayout() = R.layout.content_blank

    /***ACTIVITY CONTENT***/
    protected fun isRecylcerEnabled() = if (getActivityType() == CONTENT_RECYCLER) true else false

    protected fun isViewPagerEnabled() = if (getActivityType() == CONTENT_VIEWPAGER) true else false

    /***FAB***/
    abstract protected fun isFABEnabled(): Boolean

    private fun initFloatingActionBtn() {
        floating_action_button_view.setOnClickListener { floatingAction() }
    }

    protected fun floatingAction() {
    }
    /******/


}