package com.aceinteract.topcoder.covidstats.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.aceinteract.topcoder.covidstats.di.ViewModelFactory
import com.aceinteract.topcoder.covidstats.util.showSnackBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

/**
 * Base Fragment class that all Fragments use.
 *
 * Contains utility functions for use in the fragments.
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : DaggerFragment() {

    protected var viewModel: VM? = null

    protected var viewBinding: VB? = null

    abstract var viewModelFactory: ViewModelFactory<VM>

    protected abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected abstract fun setupViewModel(): VM

    protected abstract fun setupUI()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = setupViewModel()
        viewBinding = setupViewBinding(inflater, container)
        setupUI()
        return viewBinding!!.root
    }

    inline fun <reified T : ViewModel> obtainViewModel(viewModelFactory: ViewModelFactory<T>) =
        ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

    protected fun showSnackBar(
        rootView: View,
        text: String,
        isError: Boolean = false,
        duration: Int = Snackbar.LENGTH_SHORT
    ) {
        context?.showSnackBar(rootView, text, isError, duration)
    }

    /**
     * Interface for implementing back button press listeners on fragments.
     */
    interface OnBackPressed {

        /**
         * Is called when the back button is pressed.
         */
        fun onBackPressed(): Boolean

    }

}