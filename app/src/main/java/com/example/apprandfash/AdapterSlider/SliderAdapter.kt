package com.example.apprandfash.AdapterSlider

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.apprandfash.R
import java.util.Objects

class SliderAdapter (var dataSlider : ArrayList<Int>, var context: Activity?) : PagerAdapter() {

    lateinit var layoutInflater : LayoutInflater

    override fun getCount(): Int {
        return dataSlider.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_slider, container, false)

        val imgViewSlider : ImageView
        imgViewSlider = view.findViewById(R.id.img_slider)

        imgViewSlider.setImageResource(dataSlider[position])
        container.addView(view,0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        destroyItem(container, position, `object`)

        container.removeView(container as View)
    }
}