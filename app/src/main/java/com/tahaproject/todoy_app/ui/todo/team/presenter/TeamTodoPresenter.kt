package com.tahaproject.todoy_app.ui.todo.team.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.todo.personal.presenter.IPersonalTodoContract
import java.io.IOException

class TeamTodoPresenter(private val view: IPersonalTodoContract.IView, token: String):
    IPersonalTodoContract.IPresenter{
    private lateinit var context: Context
    private val teamTodoRequestImpl: ITeamTodoApi = TeamTodoApi(token)


    override fun fetchData(singleTodoTask: SingleTodoTask) {
        teamTodoRequestImpl.getTeamTodos(::showData,::showError)
    }



    private fun showData(toDosResponse: ToDosResponse) {
        view.showTodos(toDosResponse)
    }



    private fun showError(ioException: IOException){
        view.showError(ioException)
    }

}