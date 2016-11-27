package ua.com.mediaportal.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*


class FragmentViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val list: ArrayList<Fragment> by lazy { ArrayList<Fragment>() }

    constructor(fm: FragmentManager, pages: List<Fragment>) : this(fm) {
        list.addAll(pages)
    }

    override fun getItem(position: Int) = list[position]

    override fun getCount() = list.size

    fun addPage(page: Fragment){
        list.add(page)
    }

    fun deletePage(position: Int){
        list.removeAt(position)
    }
}