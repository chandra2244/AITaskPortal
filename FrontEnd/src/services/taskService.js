import API from "./api"

export const createTask = async (taskData) => {
    return await API.post("/tasks", taskData)
}

export const getTasks = async () => {
    return await API.get("/tasks")
}

export const deleteTask = async (id) => {
    return await API.delete(`/tasks/${id}`)
}


export const updateTaskStatus = async (id, statusData) => {
    return await API.put(`/tasks/${id}`, statusData)
}