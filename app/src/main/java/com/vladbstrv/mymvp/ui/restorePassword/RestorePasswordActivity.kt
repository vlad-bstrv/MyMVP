package com.vladbstrv.mymvp.ui.restorePassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.vladbstrv.mymvp.app
import com.vladbstrv.mymvp.databinding.ActivityRestorePasswordBinding
import com.vladbstrv.mymvp.ui.register.RegisterContract

class RestorePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestorePasswordBinding
    private var viewModel: RestorePasswordContract.ViewModel? = null
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestorePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restorePresenter()

        binding.restorePasswordButton.setOnClickListener {
            viewModel?.onRestore(
                binding.loginTextInputLayout.editText?.text.toString()
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
                viewModel?.password?.let { it1 -> setSuccess(it1) }
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

    private fun restorePresenter(): RestorePasswordContract.ViewModel {
        val presenter = lastCustomNonConfigurationInstance as? RestorePasswordContract.ViewModel
        return presenter ?: RestorePasswordViewModel(app.restoreUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess(password: String) {
        binding.restoredPasswordTextView.text = password
    }

    private fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.restorePasswordButton.isEnabled = false
    }

    private fun hideProgress() {
        binding.restorePasswordButton.isEnabled = true
    }
}