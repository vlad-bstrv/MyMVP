package com.vladbstrv.mymvp.ui.restorePassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladbstrv.mymvp.R
import com.vladbstrv.mymvp.databinding.ActivityMainBinding
import com.vladbstrv.mymvp.databinding.ActivityRestorePasswordBinding

class RestorePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestorePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestorePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}