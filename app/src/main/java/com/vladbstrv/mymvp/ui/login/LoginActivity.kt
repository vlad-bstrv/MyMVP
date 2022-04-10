package com.vladbstrv.mymvp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.vladbstrv.mymvp.app
import com.vladbstrv.mymvp.databinding.ActivityLoginBinding
import com.vladbstrv.mymvp.ui.register.RegisterActivity
import com.vladbstrv.mymvp.ui.restorePassword.RestorePasswordActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter.onAttach(this)

        binding.signInButton.setOnClickListener {
            presenter.onLogin(
                binding.loginTextInputLayout.editText?.text.toString(),
                binding.passwordTextInputLayout.editText?.text.toString(),
            )
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

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun setError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.signInButton.isEnabled = false
        binding.createAccountButton.isEnabled = false
        binding.forgetPasswordButton.isEnabled = false
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.signInButton.isEnabled = true
        binding.createAccountButton.isEnabled = true
        binding.forgetPasswordButton.isEnabled = true
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