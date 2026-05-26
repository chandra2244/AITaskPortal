import { useNavigate } from "react-router-dom"

function Navbar() {

  const navigate = useNavigate()

  const handleLogout = () => {

    localStorage.removeItem("isLoggedIn")

    navigate("/")
  }

  return (
    <div className="bg-white shadow-md px-8 py-4 flex justify-between items-center">

      <h1 className="text-2xl font-bold text-blue-600">
        AI Task Portal
      </h1>

      <button
        onClick={handleLogout}
        className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600"
      >
        Logout
      </button>

    </div>
  )
}

export default Navbar