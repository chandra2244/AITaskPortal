import { useState } from "react"

import { createTask } from "../services/taskService"

function TaskForm() {

  const [formData, setFormData] = useState({
    title: "",
    description: "",
    priority: "LOW",
    status: "TODO"
  })

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = async (e) => {

    e.preventDefault()

    try {

      const response = await createTask(formData)

      alert(response.data)

      window.location.reload()

    } catch (error) {

      alert("Task Creation Failed")
    }
  }

  return (
    <div className="bg-white p-6 rounded-2xl shadow-lg">

      <h2 className="text-2xl font-bold mb-5 text-blue-600">
        Create Task
      </h2>

      <form className="space-y-4" onSubmit={handleSubmit}>

        <input
          type="text"
          name="title"
          placeholder="Task Title"
          className="w-full border p-3 rounded-lg"
          onChange={handleChange}
        />

        <textarea
          name="description"
          placeholder="Task Description"
          className="w-full border p-3 rounded-lg h-32"
          onChange={handleChange}
        ></textarea>

        <select
          name="priority"
          className="w-full border p-3 rounded-lg"
          onChange={handleChange}
        >
          <option>LOW</option>
          <option>MEDIUM</option>
          <option>HIGH</option>
        </select>

        <button className="w-full bg-blue-600 text-white p-3 rounded-lg">
          Create Task
        </button>

      </form>

    </div>
  )
}

export default TaskForm