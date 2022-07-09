package com.vladbstrv.mymvp.ui.login

import androidx.annotation.MainThread
import com.vladbstrv.mymvp.utils.Publisher

class LoginContract {


    interface ViewModel {

        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)

    }
}