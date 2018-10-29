package com.husaynhakeem.lifecycleawarecomponentsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun checkUserStatus(): LiveData<Boolean> {
        return MutableLiveData<Boolean>().apply {
            value = true
        }
    }
}