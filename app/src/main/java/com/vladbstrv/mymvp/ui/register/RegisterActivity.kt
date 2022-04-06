package com.vladbstrv.mymvp.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladbstrv.mymvp.R
import com.vladbstrv.mymvp.databinding.ActivityRegisterBinding
import com.vladbstrv.mymvp.databinding.ActivityRestorePasswordBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}