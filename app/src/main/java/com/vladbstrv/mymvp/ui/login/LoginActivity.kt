package com.vladbstrv.mymvp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.vladbstrv.mymvp.app
import com.vladbstrv.mymvp.databinding.ActivityLoginBinding
import com.vladbstrv.mymvp.ui.register.RegisterActivity
import com.vladbstrv.mymvp.ui.restorePassword.RestorePasswordActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var viewModel: LoginContract.ViewModel? = null
    private val handler:Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restoreViewModel()
        binding.signInButton.setOnClickListener {
            viewModel?.onLogin(
                binding.loginTextInputLayout.editText?.text.toString(),
                binding.passwordTextInputLayout.editText?.text.toString(),
            )
        }

        viewModel?.shouldShowProgress?.subscribe(handler) { shouldShow ->
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

        binding.forgetPasswordButton.setOnClickListener {
            val intent = Intent(this, RestorePasswordActivity::class.java)
            startActivity(intent)
        }

        binding.createAccountButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun restoreViewModel(): LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel ?: LoginViewModel(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    private fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.signInButton.isEnabled = false
        binding.createAccountButton.isEnabled = false
        binding.forgetPasswordButton.isEnabled = false
        hideKeyboard(this)
    }

    private fun hideProgress() {
        binding.signInButton.isEnabled = true
        binding.createAccountButton.isEnabled = true
        binding.forgetPasswordButton.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.isSuccess?.unsubscribe()
        viewModel?.errorText?.unsubscribe()
        viewModel?.shouldShowProgress?.unsubscribe()
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}