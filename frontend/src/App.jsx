
import { useEffect } from 'react'
import './App.css'
import SignUp from './pages/SignUp'
import { Apiclient } from './api/apiClient'


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
      <SignUp />

    </>
  )
}

export default App
