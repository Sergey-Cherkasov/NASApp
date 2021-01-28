package br.svcdev.nasapp.mvvm.view.ui.fragment.solarspace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.svcdev.nasapp.R
import kotlinx.android.synthetic.main.solar_space_fragment.*

class SolarSpaceFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.solar_space_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = ViewPagerAdapter(childFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float,
                positionOffsetPixels: Int) {
                when(position) {
                    0 -> {
                        swipe_indicator_1.setImageResource(R.drawable.swipe_indicator_active)
                        swipe_indicator_2.setImageResource(R.drawable.swipe_indicator_passive)
                        swipe_indicator_3.setImageResource(R.drawable.swipe_indicator_passive)
                    }
                    1 -> {
                        swipe_indicator_1.setImageResource(R.drawable.swipe_indicator_passive)
                        swipe_indicator_2.setImageResource(R.drawable.swipe_indicator_active)
                        swipe_indicator_3.setImageResource(R.drawable.swipe_indicator_passive)
                    }
                    2 -> {
                        swipe_indicator_1.setImageResource(R.drawable.swipe_indicator_passive)
                        swipe_indicator_2.setImageResource(R.drawable.swipe_indicator_passive)
                        swipe_indicator_3.setImageResource(R.drawable.swipe_indicator_active)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

}