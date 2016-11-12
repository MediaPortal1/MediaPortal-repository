package ua.com.mediaportal.templates

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_app_bar.*
import kotlinx.android.synthetic.main.layout_viewstub.*
import ua.com.mediaportal.R


abstract class AbstractToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSetting()
    }

    @LayoutRes protected open fun getHolderLayout() = R.layout.holder_toolbar

    protected open fun initSetting() {
        initContent()
        initToolbar()
        if(isBackButtonEnabled()) setBackToogle()
        initViews()
    }

    private fun initContent() {
        setContentView(getHolderLayout())
        initViewStub()
    }

    private fun initViewStub() {
        viewstub_view.layoutResource = getContentLayout()
        viewstub_view.inflate()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_view)
        supportActionBar?.setTitle(getActivityTitle())
    }

    protected fun setBackToogle() {
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    @LayoutRes protected abstract fun getContentLayout(): Int

    @IdRes protected abstract fun getActivityTitle(): Int

    abstract protected fun isBackButtonEnabled():Boolean

    abstract protected fun initViews()


}