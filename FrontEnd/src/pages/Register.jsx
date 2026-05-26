import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"

import { registerUser } from "../services/authService"

function Register() {

  const navigate = useNavigate()

  const [formData, setFormData] = useState({
    name: "",
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

      const response = await registerUser(formData)

      alert(response.data)

      navigate("/")

    } catch (error) {

      alert("Registration Failed")

    }
  }

  return (
    <div className="h-screen flex items-center justify-center bg-gray-100">

      <div className="bg-white p-10 rounded-2xl shadow-xl w-[400px]">

        <h1 className="text-3xl font-bold text-center text-green-600 mb-6">
          Register
        </h1>

        <form className="space-y-4" onSubmit={handleSubmit}>

          <input
            type="text"
            name="name"
            placeholder="Enter Name"
            className="w-full border p-3 rounded-lg"
            onChange={handleChange}
          />

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

          <button className="w-full bg-green-600 text-white p-3 rounded-lg">
            Register
          </button>

        </form>

        <p className="text-center mt-4">

          Already have an account?{" "}

          <Link to="/" className="text-blue-600 font-semibold">
            Login
          </Link>

        </p>

      </div>

    </div>
  )
}

export default Register