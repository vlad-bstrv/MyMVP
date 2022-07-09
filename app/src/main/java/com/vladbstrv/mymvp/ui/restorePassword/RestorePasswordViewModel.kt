package com.vladbstrv.mymvp.ui.restorePassword

import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.domain.usecase.RestoreUsecase
import com.vladbstrv.mymvp.utils.Publisher

class RestorePasswordViewModel(private val restoreUsecase: RestoreUsecase) :
    RestorePasswordContract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher()
    override var password: String = ""
        private set

    override fun onRestore(login: String) {
        shouldShowProgress.post(true)
        restoreUsecase.restorePassword(login) {
            shouldShowProgress.post(false)
            if (it != "null") {
                isSuccess.post(true)
                this.password = it
            } else {
                errorText.post("пользователь не найден")
            }
        }
    }


}