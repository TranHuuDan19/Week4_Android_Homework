package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.week2.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel
    private var account: Account = Account("Default fullName", "Default email", "Default password")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataStore.account = this.account

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        validateUsername()
        validateEmail()
        validatePassword()
        binding.btnSignUp.setOnClickListener {
            if (validateEmail() and validateUsername() and validatePassword()) {
                getDataToDataStore()
                val intent = Intent(this@SignUp, SignIn::class.java)
                Toast.makeText(this@SignUp, "SIGNUP SUCCESS!!!" +"\n"+ DataStore.account.fullName + DataStore.account.email + DataStore.account.password,
                    Toast.LENGTH_LONG
                ).show()
                startActivity(intent)
            }
            Toast.makeText(this@SignUp, "SIGNUP FAILED!!!" +"\n"+ DataStore.account.fullName + DataStore.account.email + DataStore.account.password,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun validateEmail(): Boolean {
        var tvEmail = binding.tvEmailSignUp.text.toString().trim()

        if (tvEmail.isEmpty()) {
            binding.tvEmailSignUp.error = "Field can't be empty"
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(tvEmail).matches()) {
            binding.tvEmailSignUp.error = "Please enter a valid email address"
            return false;
        } else {
            binding.tvEmailSignUp.error = null
            return true;
        }
    }

    private fun validateUsername(): Boolean {
        var fullName = binding.tvFullNameSignUp.text.toString().trim()
        if (fullName.isEmpty()) {
            binding.tvFullNameSignUp.error = "Field can't be empty"
            return false
        } else {
            binding.tvFullNameSignUp.error = null
            return true;
        }
    }

    private fun validatePassword(): Boolean {
        var tvPassword = binding.tvPasswordSignUp.text.toString()
        var passwordVal =
            "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[!@#$%^&*()])" + ".{8,}"
        return if (tvPassword.isEmpty()) {
            binding.tvPasswordSignUp.error = "Field Cannot Be Empty"
            false
        } else if (!tvPassword.matches(Regex(passwordVal))) {
            binding.tvPasswordSignUp.error = "Password Is Too Weak"
            false
        } else {
            binding.tvPasswordSignUp.error = null
            true
        }
    }
    private fun getDataToDataStore()
    {
        binding.apply {
            viewModel.account.fullName = tvFullNameSignUp.text.toString().trim()
            viewModel.account.email    = tvEmailSignUp.text.toString().trim()
            viewModel.account.password = tvPasswordSignUp.text.toString().trim()
        }
        DataStore.account.fullName = viewModel.account.fullName
        DataStore.account.email    = viewModel.account.email
        DataStore.account.password = viewModel.account.password
    }
}
