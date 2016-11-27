package ua.com.mediaportal.templates.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_recycler.*
import ua.com.mediaportal.R
import ua.com.mediaportal.adapters.DefaultRecyclerAdapter


class RecyclerFragment : Fragment(), DefaultRecyclerAdapter.DefaultAdapterSettings {

    private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> by lazy {DefaultRecyclerAdapter(R.layout.item_simple_text,this)}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.content_recycler,container,false)
        recycler_view.adapter = adapter
        return view
    }

    override fun bind(holder: RecyclerView.ViewHolder?, position: Int) {
        //BIND HOLDER
    }

    override fun getItemCount() = 0

}