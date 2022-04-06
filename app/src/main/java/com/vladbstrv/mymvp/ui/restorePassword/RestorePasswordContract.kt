package com.vladbstrv.mymvp.ui.restorePassword

class RestorePasswordContract {

    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onRestore(login: String)
    }
}