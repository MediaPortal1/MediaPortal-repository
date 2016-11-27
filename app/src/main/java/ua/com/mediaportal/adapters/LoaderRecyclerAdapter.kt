package ua.com.mediaportal.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.com.mediaportal.R

abstract class LoaderRecyclerAdapter<Items>(recyclerView: RecyclerView, adapterCallback: LoaderRecyclerCallback, var items: List<Items>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var isLoading = false

    private val HOLDER_LOADING = 1
    private val HOLDER_CONTENT = 2

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var previousTotal = 0
            private val visibleThreshold = 5 // VISIBLE HOLDERS
            internal var firstVisibleItem: Int = 0
            internal var visibleItemCount: Int = 0
            internal var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = recyclerView.childCount
                totalItemCount = linearLayoutManager.itemCount
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

                if (adapterCallback.isLastPage()) return

                if (totalItemCount !in 0..1) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false
                            previousTotal = totalItemCount
                        }
                    }
                    if (!isLoading && firstVisibleItem + visibleThreshold >= totalItemCount - visibleItemCount) {
                        adapterCallback.load()
                        isLoading = false
                    }
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = when (viewType) {
        HOLDER_LOADING -> object : RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_loading, parent, false)) {}
        HOLDER_CONTENT -> createContentHolder(parent)
        else -> null
    }

    abstract fun createContentHolder(parent: ViewGroup?): RecyclerView.ViewHolder

    override fun getItemViewType(position: Int) = if (isLoading) HOLDER_LOADING else HOLDER_CONTENT

    override fun getItemCount() = if (isLoading) items.size + 1 else items.size

}

interface LoaderRecyclerCallback {
    fun load()
    fun isLastPage(): Boolean
}

