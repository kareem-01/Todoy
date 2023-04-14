package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.data.domain.responses.TeamToDosResponse

class FakeDataManager {
    val personalTodosList = listOf(
        PersonalTodosResponse.PersonalTodo(
            id = "1",
            title = "Buy groceries",
            description = "Apples, bananas, milk, and bread",
            status = 0,
            creationTime = "2023-04-10T10:00:00"
        ),
        PersonalTodosResponse.PersonalTodo(
            id = "2",
            title = "Finish report",
            description = "Complete the monthly sales report",
            status = 1,
            creationTime = "2023-04-12T15:00:00"
        )
    )
    val teamToDosList = listOf(
        TeamToDosResponse.TeamToDo(
            id = "1",
            title = "Plan team meeting",
            description = "Organize a team meeting to discuss project progress",
            assignee = "Alice",
            status = 0,
            creationTime = "2023-04-13T09:00:00"
        ),
        TeamToDosResponse.TeamToDo(
            id = "2",
            title = "Update project documentation",
            description = "Revise the project requirements document and update the team",
            assignee = "Bob",
            status = 1,
            creationTime = "2023-04-14T11:00:00"
        )
    )

}