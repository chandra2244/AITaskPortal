import {
  deleteTask,
  updateTaskStatus
} from "../services/taskService"

function TaskCard({ task }) {

  const handleDelete = async () => {

    try {

      await deleteTask(task.id)

      window.location.reload()

    } catch (error) {

      alert("Delete Failed")
    }
  }

  const handleStatusChange = async (e) => {

    try {

      await updateTaskStatus(task.id, {
        status: e.target.value
      })

      window.location.reload()

    } catch (error) {

      alert("Update Failed")
    }
  }

  return (
    <div className="bg-white p-5 rounded-2xl shadow-lg border">

      <div className="flex justify-between items-start">

        <div>

          <h2 className="text-2xl font-bold">
            {task.title}
          </h2>

          <p className="text-gray-600 mt-2">
            {task.description}
          </p>

          <p className="text-blue-600 mt-3 font-semibold">
  {task.aiSummary}
</p>

        </div>

        <select
          value={task.status}
          onChange={handleStatusChange}
          className="border px-3 py-2 rounded-lg"
        >

          <option>TODO</option>
          <option>IN_PROGRESS</option>
          <option>DONE</option>

        </select>

      </div>

      <div className="flex justify-between items-center mt-5">

        <span className="bg-red-200 px-3 py-1 rounded-full text-sm">
          {task.priority}
        </span>

        <button
          onClick={handleDelete}
          className="bg-red-500 text-white px-4 py-2 rounded-lg"
        >
          Delete
        </button>

      </div>

    </div>
  )
}

export default TaskCard