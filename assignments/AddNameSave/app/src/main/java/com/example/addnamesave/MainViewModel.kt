package com.example.addnamesave

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    var name: MutableLiveData<String> = MutableLiveData()
    var output = ""
    fun addName()
    {
        name.let {
            if (!it.value.equals("")) {
                output = output + it.value + "\n"
            } else {
                output = "No name entered"
            }
        }
    }



}