package com.vladbstrv.mymvp.ui.login

import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.data.MockLoginApiImpl
import com.vladbstrv.mymvp.domain.LoginApi
import java.lang.Thread.sleep

class LoginPresenter(private val api: LoginApi) :  LoginContract.Presenter{

    private lateinit var view: LoginContract.View
    private val uiHandler = Handler(Looper.getMainLooper())


    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view.showProgress()

        Thread {
            val success = api.login(login, password)
            uiHandler.post {
                view.hideProgress()
                if (success) {
                    view.setSuccess()
                } else {
                    view.setError()
                }
            }
        }.start()
    }


}