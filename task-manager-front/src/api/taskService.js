export const getAllTasks = async () => {
    fetch("/task/all")
        .then(response => {
            return response.json()
        })
}
export const addNewTask = async (addRequest) => {
    fetch("/task/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(addRequest)
    })
}
export const deleteTask = async (taskId) => {
    fetch("/task/delete/" + taskId, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        },

    })
}
export const setFinishTask = async (taskId) => {
    fetch("/task/finish/" + taskId, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },

    })
}
export const setUnfinishTask = async (taskId) => {
    fetch("/task/un-finish/" + taskId, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },

    })
}
export const updateTask = async (updateRequest) => {
    fetch("task/update", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updateRequest)
    })
}
