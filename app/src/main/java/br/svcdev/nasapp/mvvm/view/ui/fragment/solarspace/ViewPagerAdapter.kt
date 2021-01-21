package br.svcdev.nasapp.mvvm.view.ui.fragment.solarspace

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val EARTH_FRAGMENT = 0
        private const val MARS_FRAGMENT = 1
        private const val WEATHER_FRAGMENT = 2
    }

    private val fragments = listOf(EarthFragment(), MarsFragment(), AsteroidsFragment())

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        return when(position) {
            EARTH_FRAGMENT -> fragments[EARTH_FRAGMENT]
            MARS_FRAGMENT -> fragments[MARS_FRAGMENT]
            WEATHER_FRAGMENT -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            EARTH_FRAGMENT -> "Earth"
            MARS_FRAGMENT -> "Mars"
            WEATHER_FRAGMENT -> "Weather"
            else -> "Earth"
        }
    }
}