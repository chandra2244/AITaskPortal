import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"

import { loginUser } from "../services/authService"

function Login() {

  const navigate = useNavigate()

  const [formData, setFormData] = useState({
    email: "",
    password: ""
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

      const response = await loginUser(formData)

      localStorage.setItem("token", response.data)

alert("Login Success")

navigate("/dashboard")

    } catch (error) {

      alert("Login Failed")

    }
  }

  return (
    <div className="h-screen flex items-center justify-center bg-gray-100">

      <div className="bg-white p-10 rounded-2xl shadow-xl w-[400px]">

        <h1 className="text-3xl font-bold text-center text-blue-600 mb-6">
          Login
        </h1>

        <form className="space-y-4" onSubmit={handleSubmit}>

          <input
            type="email"
            name="email"
            placeholder="Enter Email"
            className="w-full border p-3 rounded-lg"
            onChange={handleChange}
          />

          <input
            type="password"
            name="password"
            placeholder="Enter Password"
            className="w-full border p-3 rounded-lg"
            onChange={handleChange}
          />

          <button className="w-full bg-blue-600 text-white p-3 rounded-lg">
            Login
          </button>

        </form>

        <p className="text-center mt-4">

          Don't have an account?{" "}

          <Link to="/register" className="text-blue-600 font-semibold">
            Register
          </Link>

        </p>

      </div>

    </div>
  )
}

export default Login