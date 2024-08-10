
import { useEffect } from 'react'
import './App.css'
import SignUp from './pages/SignUp'
import { Apiclient } from './api/apiClient'
import Login from './components/auth/login/Login'


function App() {
  useEffect(() => {
    async function getData() {
      let res = await Apiclient.get(`/list-user`)
      console.log("res", res)
    }
    getData()
  },[])
  return (
    <>
      {/* <SignUp /> */}
      <Login />

    </>
  )
}

export default App
