package com.vladbstrv.mymvp.ui.login

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep

class LoginPresenter :  LoginContract.Presenter{

    private lateinit var view: LoginContract.View
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view.showProgress()

        Thread {
            sleep(1000)
            uiHandler.post {
                view.hideProgress()
                if (login == password) {
                    view.setSuccess()
                } else {
                    view.setError()
                }
            }
        }.start()
    }


}