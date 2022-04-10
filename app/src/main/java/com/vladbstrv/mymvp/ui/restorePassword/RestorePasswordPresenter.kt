package com.vladbstrv.mymvp.ui.restorePassword

import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.domain.usecase.RestoreUsecase

class RestorePasswordPresenter(private val restoreUsecase: RestoreUsecase) : RestorePasswordContract.Presenter {

    private lateinit var view: RestorePasswordContract.View
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: RestorePasswordContract.View) {
        this.view = view
    }

    override fun onRestore(login: String) {
        view.showProgress()
        restoreUsecase.restorePassword(login) {
                view.hideProgress()
            if(it != "null") {
                view.setSuccess(it)
            }
        }
    }

}