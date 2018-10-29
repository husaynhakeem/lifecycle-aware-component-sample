package com.husaynhakeem.lifecycleawarecomponentsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLocationListener()
        setupViewModel()
    }

    private fun setupLocationListener() {
        locationListener = LocationListener(lifecycle)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.checkUserStatus().observe(this, Observer { isUserSignedIn ->
            if (isUserSignedIn == true) {
                locationListener.enable()
            }
        })
    }
}
