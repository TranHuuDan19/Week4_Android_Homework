package com.example.week2

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.week2.databinding.ActivitySignupBinding

class SignUpViewModel:ViewModel() {
    var account : Account=Account("default","default","default")

}