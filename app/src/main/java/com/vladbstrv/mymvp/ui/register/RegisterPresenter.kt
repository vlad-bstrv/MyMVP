package com.vladbstrv.mymvp.ui.register

import android.os.Handler
import android.os.Looper

class RegisterPresenter : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: RegisterContract.View) {
        this.view = view
    }

    override fun onRegister(login: String, password: String, repeatPassword: String) {
        view.showProgress()

        Thread {
            Thread.sleep(1000)
            uiHandler.post {
                view.hideProgress()
                if (password == repeatPassword && login == "login") {
                    view.setSuccess()
                } else if (password != repeatPassword) {
                    view.setError("Не совпадают пароли")
                } else {
                    view.setError("Неизвестная ошибка")
                }
            }
        }.start()
    }

}