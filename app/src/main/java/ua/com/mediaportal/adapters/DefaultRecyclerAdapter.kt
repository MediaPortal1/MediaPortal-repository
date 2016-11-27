package ua.com.mediaportal.adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class DefaultRecyclerAdapter(@LayoutRes val holderLayout: Int, val adapterSettings: DefaultAdapterSettings) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = object : RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).inflate(holderLayout,parent,false)) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        adapterSettings.bind(holder,position)
    }

    override fun getItemCount() = adapterSettings.getItemCount()

    interface DefaultAdapterSettings {
        fun bind(holder: RecyclerView.ViewHolder?, position: Int)
        fun getItemCount(): Int
    }
}