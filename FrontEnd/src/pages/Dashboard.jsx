import { useEffect, useState } from "react"

import Navbar from "../components/Navbar"
import TaskCard from "../components/TaskCard"
import TaskForm from "../components/TaskForm"

import { getTasks } from "../services/taskService"

function Dashboard() {

  const [tasks, setTasks] = useState([])

  useEffect(() => {

    fetchTasks()

  }, [])

  const fetchTasks = async () => {

    try {

      const response = await getTasks()

      setTasks(response.data)

    } catch (error) {

      console.log(error)
    }
  }

  return (
    <div className="min-h-screen bg-gray-100">

      <Navbar />

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 p-8">

        <div className="md:col-span-1">
          <TaskForm />
        </div>

        <div className="md:col-span-2 space-y-5">

          {
            tasks.map((task) => (
              <TaskCard key={task.id} task={task} />
            ))
          }

        </div>

      </div>

    </div>
  )
}

export default Dashboard