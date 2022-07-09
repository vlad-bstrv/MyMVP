package com.vladbstrv.mymvp.ui.restorePassword

import com.vladbstrv.mymvp.utils.Publisher

class RestorePasswordContract {

    interface ViewModel {

        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>
        val password: String

        fun onRestore(login: String)
    }
}