package com.vladbstrv.mymvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.vladbstrv.mymvp.app
import com.vladbstrv.mymvp.databinding.ActivityRegisterBinding
import com.vladbstrv.mymvp.ui.login.LoginActivity
import com.vladbstrv.mymvp.ui.login.LoginContract

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var viewModel: RegisterContract.ViewModel? = null
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restorePresenter()


        binding.registerButton.setOnClickListener {
            viewModel?.onRegister(
                binding.loginTextInputLayout.editText?.text.toString(),
                binding.passwordTextInputLayout.editText?.text.toString(),
                binding.passwordRepeatTextInputLayout.editText?.text.toString(),
                binding.emailTextInputLayout.editText?.text.toString()
            )
        }

        viewModel?.shouldShowProgress?.subscribe(handler) {shouldShow ->
            if (shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel?.isSuccess?.subscribe(handler) {
            if(it == true) {
                setSuccess()
            }
        }

        viewModel?.errorText?.subscribe(handler) {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if(success == false) {
                    setError(it)
                }
            }
        }
    }

    private fun restorePresenter(): RegisterContract.ViewModel {
        val presenter = lastCustomNonConfigurationInstance as? RegisterViewModel
        return presenter ?: RegisterViewModel(app.registerUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.registerButton.isEnabled = false
    }

    private fun hideProgress() {
        binding.registerButton.isEnabled = true
    }
}

