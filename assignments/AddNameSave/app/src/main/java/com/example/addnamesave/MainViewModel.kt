package com.example.addnamesave

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private var name = ""
    private var output = ""
    fun addName(value: String)
    {
        name = value
        output = output + name + "\n"

    }

    fun displayName() : String
    {
        return output
    }
}