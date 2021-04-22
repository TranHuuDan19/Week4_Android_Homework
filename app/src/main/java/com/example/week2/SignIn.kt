package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.week2.databinding.ActivitySigninBinding

class SignIn: AppCompatActivity() {
        private lateinit var binding : ActivitySigninBinding
        private lateinit var viewModel: SignInViewModel
        private var account : Account = Account("Default fullName", "default email", "Default password")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.account = viewModel.account

        binding.apply {
            btnSignIn.setOnClickListener {
                viewModel.account.email    = tvEmailSignIn.text.toString().trim()
                viewModel.account.password = tvPassSignIn.text.toString().trim()

                if (viewModel.account?.email.compareTo(DataStore.account.email.trim()) == 0 && viewModel.account.password?.compareTo( DataStore.account.password.trim()) == 0 )
                {
                    val intent = Intent(this@SignIn,RestaurantListActivity::class.java)
                    Toast.makeText(this@SignIn, "LOGIN SUCCESS!!!" +"\n"+ viewModel.account.email + viewModel.account.password , Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }
                else
                    Toast.makeText(this@SignIn, "LOGIN FAILED" +"\n" +viewModel.account.email + viewModel.account.password , Toast.LENGTH_LONG).show()

                invalidateAll()
            }
        }
    }

}