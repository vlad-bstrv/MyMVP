package com.vladbstrv.mymvp.ui.login

import com.vladbstrv.mymvp.domain.usecase.LoginUsecase

class LoginPresenter(private val loginUsecase: LoginUsecase) :  LoginContract.Presenter{

    private lateinit var view: LoginContract.View

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view.showProgress()

        val result = loginUsecase.login(login, password)
        if(result) {
            view.hideProgress()
            view.setSuccess()
        } else {
            view.hideProgress()
            view.setError()
        }
    }


}