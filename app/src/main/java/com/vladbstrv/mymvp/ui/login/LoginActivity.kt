package com.vladbstrv.mymvp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladbstrv.mymvp.R
import com.vladbstrv.mymvp.databinding.ActivityLoginBinding
import com.vladbstrv.mymvp.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}