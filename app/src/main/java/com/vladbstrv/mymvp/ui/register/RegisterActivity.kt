package com.vladbstrv.mymvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vladbstrv.mymvp.R
import com.vladbstrv.mymvp.app
import com.vladbstrv.mymvp.databinding.ActivityRegisterBinding
import com.vladbstrv.mymvp.databinding.ActivityRestorePasswordBinding
import com.vladbstrv.mymvp.ui.login.LoginActivity
import com.vladbstrv.mymvp.ui.login.LoginPresenter

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter.onAttach(this)

        binding.registerButton.setOnClickListener {
            presenter.onRegister(
                binding.loginTextInputLayout.editText?.text.toString(),
                binding.passwordTextInputLayout.editText?.text.toString(),
                binding.passwordRepeatTextInputLayout.editText?.text.toString(),
                binding.emailTextInputLayout.editText?.text.toString()
            )
        }
    }

    private fun restorePresenter(): RegisterContract.Presenter {
        val presenter = lastCustomNonConfigurationInstance as? RegisterPresenter
        return presenter ?: RegisterPresenter(app.registerUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun setSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.registerButton.isEnabled = false
    }

    override fun hideProgress() {
        binding.registerButton.isEnabled = true
    }
}