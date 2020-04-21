package com.tracker.covid.ui.home

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.covid.R
import com.tracker.covid.di.component.FragmentComponent
import com.tracker.covid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel>() {
    override fun provideLayout(): Int = R.layout.fragment_home

    override fun setUpvView(view: View) {
        rcv_home.apply {
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

}