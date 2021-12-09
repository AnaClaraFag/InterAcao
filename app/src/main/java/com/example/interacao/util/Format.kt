package com.example.interacao.util

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

import java.util.*


    
    private val locale= Locale("pt-br","BR")

    fun Date.format():String{
        return SimpleDateFormat("yyyy-MM-dd ",locale).format(this)
    }
