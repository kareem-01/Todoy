package com.tahaproject.todoy_app.data.models.responses.todosListResponse

import android.os.Parcelable
import com.tahaproject.todoy_app.data.models.responses.BaseResponse
import com.tahaproject.todoy_app.data.models.responses.IBaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateResponse(
    override val value: Todos,
    override val message: String?,
    override val isSuccess: Boolean,
) : IBaseResponse<Todos> by BaseResponse(value, message, isSuccess), Parcelable


