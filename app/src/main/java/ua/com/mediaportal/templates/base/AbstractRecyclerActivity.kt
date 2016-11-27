package ua.com.mediaportal.templates.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ua.com.mediaportal.R
import ua.com.mediaportal.adapters.DefaultRecyclerAdapter
import ua.com.mediaportal.templates.AbstractBaseActivity
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_RECYCLER


abstract class AbstractRecyclerActivity : AbstractBaseActivity(), DefaultRecyclerAdapter.DefaultAdapterSettings {

    @LayoutRes protected val DEFAULT_VIEWHOLDER_LAYOUT = R.layout.layout_viewholder_default

    override fun getContentType() = CONTENT_RECYCLER

    override fun initViews() {
        initRecyclerView()
    }

    protected fun initRecyclerView(){
        recyclerView?.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL,false)
        recyclerView?.adapter = getRecyclerAdapter()
    }

    abstract protected fun getRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

    protected fun getDefaultRecyclerAdapter() = DefaultRecyclerAdapter(DEFAULT_VIEWHOLDER_LAYOUT,this)

    /***DEFAULT ADAPTER ACTIONS***/
    override fun bind(holder: RecyclerView.ViewHolder?, position: Int) {}
    override fun getItemCount() = 0
}