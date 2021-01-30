package com.test.bottomnavigationcontrol

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.bottomnavigationcontrol.databinding.ActivityMainBinding
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val fm: FragmentManager = supportFragmentManager
    val fragment1: Fragment = ServiceProviderHomeFragment()
    val fragment2: Fragment = ServiceProviderOffersFragment()
    val fragment3: Fragment = ServiceProviderBalanceFragment()
    val fragment4: Fragment = ServiceProviderOrdersFragment()
    val fragment5: Fragment = ServiceProviderProfileFragment()
    var active: Fragment = fragment1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        navigationImagesMargin(binding.spBottomNavigation)
        binding.spBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fm.beginTransaction().add(R.id.main_container, fragment5, "5").hide(fragment5).commit()
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit()
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit()
    }

    private fun navigationImagesMargin(view: View) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                navigationImagesMargin(child)
            }
        } else if (view is ImageView) {
            val param = view.layoutParams as ViewGroup.MarginLayoutParams
            param.topMargin = convertDpToPx(14)
            view.layoutParams = param
        }
    }

    private fun convertDpToPx(dp: Int): Int {
        return (dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            binding.spBottomNavigation.post {
                navigationImagesMargin(binding.spBottomNavigation)
            }
            when (item.itemId) {
                R.id.service_provider_home_fragment -> {
                    fm.beginTransaction().hide(active).show(fragment1).commit()
                    active = fragment1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.service_provider_offers_fragment -> {
                    fm.beginTransaction().hide(active).show(fragment2).commit()
                    active = fragment2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.service_provider_balance_fragment -> {
                    fm.beginTransaction().hide(active).show(fragment3).commit()
                    active = fragment3
                    return@OnNavigationItemSelectedListener true
                }
                R.id.service_provider_orders_fragment -> {
                    fm.beginTransaction().hide(active).show(fragment4).commit()
                    active = fragment4
                    return@OnNavigationItemSelectedListener true
                }
                R.id.service_provider_profile_fragment -> {
                    fm.beginTransaction().hide(active).show(fragment5).commit()
                    active = fragment5
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }
}