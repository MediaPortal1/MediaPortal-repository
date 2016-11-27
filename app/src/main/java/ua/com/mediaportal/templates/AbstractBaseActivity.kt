package ua.com.mediaportal.templates

import android.content.Intent
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.holder_drawer.*
import kotlinx.android.synthetic.main.holder_toolbar.*
import kotlinx.android.synthetic.main.layout_fab.*
import kotlinx.android.synthetic.main.layout_navigation.*
import kotlinx.android.synthetic.main.layout_recycler.*
import kotlinx.android.synthetic.main.layout_viewpager.*
import ua.com.mediaportal.R
import ua.com.mediaportal.templates.ActivitySettings.ACTIVITY_TOOLBAR
import ua.com.mediaportal.templates.ActivitySettings.ACTIVITY_TOOLBAR_DRAWER
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_CUSTOM
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_RECYCLER
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_VIEWPAGER
import ua.com.mediaportal.util.OnAction


abstract class AbstractBaseActivity : AbstractToolbarActivity() {

    /*** VIEW ELEMENTS***/
    protected var floatingButton: FloatingActionButton? = null
    protected var recyclerView: RecyclerView? = null
    protected var viewPager: ViewPager? = null
    protected var drawerLayout: DrawerLayout? = null
    protected var navigationView: NavigationView? = null

    override fun initSetting() {
        super.initSetting()
        initDefaultViews()
    }

    private fun initDefaultViews() {
        if (isFABEnabled()) {
            floatingButton = floating_action_button_view
            initFloatingActionBtn()
        }
        if (isRecyclerEnabled()) recyclerView = recycler_view
        if (isViewPagerEnabled()) viewPager = viewpager_view
        if (isDrawerEnabled()) {
            drawerLayout = drawer_view
            navigationView = navigation_view
        }

    }

    protected fun getActivityType() = when {
        isDrawerEnabled() -> ACTIVITY_TOOLBAR_DRAWER
        else -> ACTIVITY_TOOLBAR
    }

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

    protected fun getActivityRootView() = when (getActivityType()) {
        ACTIVITY_TOOLBAR -> root_view
        else -> drawerLayout

    }

    abstract protected fun getContentType(): Int

    abstract protected fun isDrawerEnabled(): Boolean

    open protected fun isContentCustom() = false

    open @LayoutRes protected fun getCustomContentLayout() = R.layout.content_blank


    /***ACTIVITY CONTENT***/
    protected fun isRecyclerEnabled() = getContentType() == CONTENT_RECYCLER

    protected fun isViewPagerEnabled() = getContentType() == CONTENT_VIEWPAGER


    /***FAB***/
    abstract protected fun isFABEnabled(): Boolean

    private fun initFloatingActionBtn() {
        floating_action_button_view.setOnClickListener { floatingAction() }
    }

    protected fun floatingAction() {
    }
    /******/


    /***DRAWER***/
    protected fun setDrawer(status: Boolean = false) {
        if (status) drawerLayout?.openDrawer(navigationView)
        else drawerLayout?.closeDrawers()
    }

    private fun initNavigationViewItemListener() {
        navigationView?.setNavigationItemSelectedListener { i -> onNavigationItemSelected(i.itemId)}
    }

    protected fun onNavigationItemSelected(@IdRes menuId: Int): Boolean = false

    /******/

    /***MESSAGES***/
    protected fun showSnackBar(@IdRes messageText: Int,
                               @IdRes actionText: Int,
                               action: OnAction, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(getActivityRootView()?.rootView ?: findViewById(android.R.id.content), messageText, duration)
                .setAction(actionText, { action.onAction() }).show()
    }

    protected fun showToast(@IdRes text: Int, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(baseContext, text, duration)
    }
    /******/

}