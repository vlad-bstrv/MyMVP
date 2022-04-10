package com.vladbstrv.mymvp.ui.register

import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.domain.RegisterUsecase

class RegisterPresenter(private val registerUsecase: RegisterUsecase) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View

    override fun onAttach(view: RegisterContract.View) {
        this.view = view
    }

    override fun onRegister(login: String, password: String, repeatPassword: String, email: String) {
        view.showProgress()

        registerUsecase.register(login, password, repeatPassword, email) {
            view.hideProgress()
            if(it) {
                view.setSuccess()
            } else {
                view.setError("")
            }
        }

    }

}