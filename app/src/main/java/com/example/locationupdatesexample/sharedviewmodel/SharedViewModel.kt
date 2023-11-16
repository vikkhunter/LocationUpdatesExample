package com.example.locationupdatesexample.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    private val _mutableLiveData = MutableLiveData<String>()
    val liveData : LiveData<String> = _mutableLiveData

    fun postValue(newValue: String) {
        _mutableLiveData.postValue(newValue)
    }
}