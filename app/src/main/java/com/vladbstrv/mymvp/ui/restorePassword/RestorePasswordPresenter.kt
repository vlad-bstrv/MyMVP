package com.vladbstrv.mymvp.ui.restorePassword

import android.os.Handler
import android.os.Looper

class RestorePasswordPresenter : RestorePasswordContract.Presenter {

    private lateinit var view: RestorePasswordContract.View
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: RestorePasswordContract.View) {
        this.view = view
    }

    override fun onRestore(login: String) {
        view.showProgress()
        Thread {
            Thread.sleep(1000)
            uiHandler.post() {
                view.hideProgress()
                view.setSuccess()
            }
        }.start()
    }

}