package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.requests.SignUpRequest
import com.tahaproject.todoy_app.data.responses.SignUpResponse
import java.io.IOException

interface SignUpContract {
    interface View {
        fun showData(signUpResponse: SignUpResponse)
        fun showError(error: IOException)
    }

    interface Presenter {
        fun fetchData(signUpRequest: SignUpRequest)
        fun attach(signUpView: View)
        fun deAttach()
    }
}