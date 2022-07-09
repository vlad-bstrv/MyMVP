package com.vladbstrv.mymvp.ui.register

import com.vladbstrv.mymvp.utils.Publisher

class RegisterContract {

    interface ViewModel {

        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        fun onRegister(login: String, password: String, repeatPassword: String, email: String)
    }
}