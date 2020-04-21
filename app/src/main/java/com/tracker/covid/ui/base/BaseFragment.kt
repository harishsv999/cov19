package com.tracker.covid.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tracker.covid.CoVidApplication
import com.tracker.covid.di.component.DaggerFragmentComponent
import com.tracker.covid.di.component.FragmentComponent
import com.tracker.covid.di.modules.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(createDaggerComponent())
        super.onCreate(savedInstanceState)
        attachObserver()
        viewModel.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(provideLayout() ,container ,false )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpvView(view)
    }

    protected open fun attachObserver() {

        viewModel.message.observe(this , Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messagedId.observe(this , Observer {
            it.data?.run { getStringFromResource( it.data) }

        })
    }

    private fun showMessage(message: String) = Toast.makeText(activity , message , Toast.LENGTH_SHORT).show()

    private fun getStringFromResource(@StringRes resId  : Int)  = showMessage(getString(resId))

    @LayoutRes
    abstract protected  fun provideLayout() : Int

    protected abstract fun  setUpvView(view: View)

    protected abstract fun  injectDependencies (fragmentComponent: FragmentComponent)

    private fun createDaggerComponent() : FragmentComponent = DaggerFragmentComponent.builder()
            .appComponent((activity!!.application as CoVidApplication).appComponent)
            .fragmentModule(FragmentModule(this))
            .build()
}
