package com.tahaproject.todoy_app.data.apiManger.personalTodo

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoPostRequest
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodoUpdateResponse
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.data.interceptors.TodoInterceptor
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class PersonalTodoApiRequest : ApiRequest(), IPersonalTodoApi {
    private val client =
        OkHttpClient.Builder().addInterceptor(TodoInterceptor()).addInterceptor(logInterceptor)
            .build()

    override fun createPersonalTodo(
        personalTodoPostRequest: PersonalTodoPostRequest, onSuccess: (PersonalTodoPostRequest) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.TITLE, personalTodoPostRequest.value.title)
            .add(Constants.Todo.DESCRIPTION, personalTodoPostRequest.value.description)
            .build()
        val request = postRequest(formBody, Constants.EndPoints.personalTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val personalTodo =
                        gson.fromJson(jsonString, PersonalTodoPostRequest::class.java)
                    onSuccess(personalTodo)
                }
            }

        })

    }

    override fun getPersonalTodos(
        onSuccess: (PersonalTodosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val request = getRequest(Constants.EndPoints.personalTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val personalTodosResponse =
                        gson.fromJson(jsonString, PersonalTodosResponse::class.java)
                    onSuccess(personalTodosResponse)
                }
            }

        })

    }

    override fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: PersonalTodoUpdateRequest,
        onSuccess: (PersonalTodoUpdateResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.ID, personalTodoUpdateRequest.id)
            .add(Constants.Todo.STATUS, personalTodoUpdateRequest.status.toString())
            .build()
        val request = putRequest(formBody, Constants.EndPoints.personalTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val updatePersonTodo =
                        gson.fromJson(jsonString, PersonalTodoUpdateResponse::class.java)
                    onSuccess(updatePersonTodo)
                }
            }

        })

    }
}