package com.vladbstrv.mymvp.ui.restorePassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vladbstrv.mymvp.app
import com.vladbstrv.mymvp.databinding.ActivityRestorePasswordBinding
import com.vladbstrv.mymvp.ui.register.RegisterContract
import com.vladbstrv.mymvp.ui.register.RegisterPresenter

class RestorePasswordActivity : AppCompatActivity(), RestorePasswordContract.View {

    private lateinit var binding: ActivityRestorePasswordBinding
    private lateinit var presenter: RestorePasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestorePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter.onAttach(this)

        binding.restorePasswordButton.setOnClickListener {
            presenter.onRestore(
                binding.loginTextInputLayout.editText?.text.toString()
            )
        }
    }

    private fun restorePresenter(): RestorePasswordContract.Presenter {
        val presenter = lastCustomNonConfigurationInstance as? RestorePasswordContract.Presenter
        return presenter ?: RestorePasswordPresenter(app.restoreUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess(password: String) {
        binding.restoredPasswordTextView.text = password
    }

    override fun setError(error: String) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.restorePasswordButton.isEnabled = false
    }

    override fun hideProgress() {
        binding.restorePasswordButton.isEnabled = true
    }
}