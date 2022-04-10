package com.vladbstrv.mymvp.ui.register

class RegisterContract {

    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onRegister(login: String, password: String, repeatPassword: String, email: String)
    }
}