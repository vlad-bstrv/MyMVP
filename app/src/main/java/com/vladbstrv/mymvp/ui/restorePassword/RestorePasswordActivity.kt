package com.vladbstrv.mymvp.ui.restorePassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vladbstrv.mymvp.databinding.ActivityRestorePasswordBinding

class RestorePasswordActivity : AppCompatActivity(), RestorePasswordContract.View {

    private lateinit var binding: ActivityRestorePasswordBinding
    private lateinit var presenter: RestorePasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestorePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = RestorePasswordPresenter()
        presenter.onAttach(this)

        binding.restorePasswordButton.setOnClickListener {
            presenter.onRestore(
                binding.loginTextInputLayout.editText?.text.toString()
            )
        }
    }

    override fun setSuccess() {
        binding.restoredPasswordTextView.text = "password"
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