package com.vladbstrv.mymvp.ui.login

import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.data.MockLoginApiImpl
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.LoginUsecase
import java.lang.Thread.sleep

class LoginPresenter(private val loginUsecase: LoginUsecase) :  LoginContract.Presenter{

    private lateinit var view: LoginContract.View

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view.showProgress()

        loginUsecase.login(login, password) { result ->
            view.hideProgress()
            if (result) {
                view.setSuccess()
            } else {
                view.setError()
            }
        }


    }


}