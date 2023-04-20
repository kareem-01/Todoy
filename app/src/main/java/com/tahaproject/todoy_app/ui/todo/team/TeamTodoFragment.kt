package com.tahaproject.todoy_app.ui.todo.team

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import android.widget.Toast
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.presenter.IHomeContract
import com.tahaproject.todoy_app.ui.addtask.AddNewTaskFragment
import com.tahaproject.todoy_app.ui.home.HomeActivity
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapter
import com.tahaproject.todoy_app.ui.todo.team.presenter.ITeamTodoContract
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoPresenter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding, TeamTodoPresenter>(),
    ITeamTodoContract.IView {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private lateinit var teamtodoAdapter: TeamAdapter
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    override fun setup() {

        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())
    }



class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding, TeamTodoPresenter>(),
    ITeamTodoContract.IView {

    override val presenter: TeamTodoPresenter
        get() = TeamTodoPresenter(this)

    override fun showTodos(toDosResponse: ToDosResponse) {
        initView(toDosResponse)

    }


    override fun showError(ioException: IOException) {
        requireActivity().runOnUiThread {
            showToast(ioException.localizedMessage.toString())
        }
    }


    private fun initView(toDosResponse: ToDosResponse) {

        teamtodoAdapter = TeamAdapter(toDosResponse.value)
        binding.recyclerTeamTodo.adapter = teamtodoAdapter
        get() = TeamTodoPresenter(this, SharedPreferenceUtil(activity as HomeActivity).getToken())

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    private var selectedTaskChip: TaskChip = TaskChip.TODO

    private lateinit var toDosResponse: ToDosResponse
    private lateinit var adapter: TeamAdapter


    override fun setup() {
        chooseGroup()
        setChipClickListeners()
    }

    override fun addCallBack() {
   }

    private fun chooseGroup() {
        binding.chipGroupTeamTodo.setOnCheckedStateChangeListener { _, checkedId ->
            selectedTaskChip = when (checkedId[0]) {
                R.id.chip_todo -> TaskChip.TODO
                R.id.chip_inProgress -> TaskChip.IN_PROGRESS
                R.id.chip_done -> TaskChip.DONE
                else -> TaskChip.TODO
            }
        }
    }

    private fun setChipClickListeners() {
        binding.chipTodo.setOnClickListener { onChipTodoClicked() }
        binding.chipInProgress.setOnClickListener { onChipInProgressClicked() }
        binding.chipDone.setOnClickListener { onChipDoneClicked() }
    }

    private fun onChipTodoClicked() {
        processClickedChipData(toDosResponse, TaskChip.TODO)
    }

    private fun onChipInProgressClicked() {
        processClickedChipData(toDosResponse, TaskChip.IN_PROGRESS)
    }

    private fun onChipDoneClicked() {
        processClickedChipData(toDosResponse, TaskChip.DONE)
    }

    private fun processClickedChipData(toDosResponse: ToDosResponse, status: TaskChip) {
        selectedTaskChip = status

        val filteredList: List<Todo> = when (status) {
            TaskChip.TODO -> toDosResponse.value.filter { it.status == Constants.TODO_STATUS }
            TaskChip.IN_PROGRESS -> toDosResponse.value.filter  { it.status == Constants.IN_PROGRESS_STATUS}
            TaskChip.DONE -> toDosResponse.value.filter { it.status == Constants.DONE_STATUS }
        }

        adapter = TeamAdapter(filteredList)
        binding.recyclerviewTeamTodo.adapter = adapter
    }

    override fun showTodos(toDosResponse: ToDosResponse) {
        this.toDosResponse = toDosResponse
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "${error.message}", Toast.LENGTH_SHORT).show()
        }
    }
}



    }
enum class TaskChip {
    TODO, IN_PROGRESS, DONE
}
