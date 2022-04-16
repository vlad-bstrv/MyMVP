package com.vladbstrv.mymvp.ui.register

import com.vladbstrv.mymvp.domain.usecase.RegisterUsecase
import com.vladbstrv.mymvp.utils.Publisher

class RegisterViewModel(private val registerUsecase: RegisterUsecase) : RegisterContract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher()


    override fun onRegister(login: String, password: String, repeatPassword: String, email: String) {
        shouldShowProgress.post(true)

        registerUsecase.register(login, password, repeatPassword, email) {
            shouldShowProgress.post(false)
            if(it) {
                isSuccess.post(true)
                errorText.post("")
            } else {
                isSuccess.post(false)
                errorText.post("пароли не совпадают")
            }
        }

    }

}