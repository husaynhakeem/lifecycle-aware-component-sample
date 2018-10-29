package com.husaynhakeem.lifecycleawarecomponentsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun checkUserStatus() = MutableLiveData<Boolean>().apply {
        value = true
    }
}