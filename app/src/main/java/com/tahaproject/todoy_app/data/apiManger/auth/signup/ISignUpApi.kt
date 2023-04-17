package com.tahaproject.todoy_app.data.apiManger.auth.signup

import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import com.tahaproject.todoy_app.ui.signup.presenter.SignUpContract
import java.io.IOException

interface ISignUpApi {
    fun signUp(
        signUpRequest: SignUpRequest,
        onSuccess: (SignUpResponse) -> Unit,
        onFailed: (IOException) -> Unit,
        presenter: SignUpContract.Presenter
    )
}

