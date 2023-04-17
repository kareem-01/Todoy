package com.tahaproject.todoy_app.ui.todo.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.responses.TeamToDosResponse
import com.tahaproject.todoy_app.databinding.ItemCardTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter

class TeamAdapter(view: List<TeamToDosResponse.TeamToDo>) :
    BaseRecyclerAdapter<TeamToDosResponse.TeamToDo, ItemCardTeamTodoBinding>(view) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardTeamTodoBinding =
        ItemCardTeamTodoBinding::inflate

    override fun bindViews(binding: ItemCardTeamTodoBinding) {
        binding.apply {
            //textViewTitleTeamTodo.text = list[0].title
            //textViewAssignName.text = list[0].assignee
            //textViewBodyTeamTodo.text = list[0].description
            //textViewStatus.text = list[0].status.toString()
            //textViewCreationTime.text = list[0].creationTime
        }
    }
}