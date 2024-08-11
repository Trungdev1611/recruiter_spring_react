import  { useEffect, useState } from 'react'

function useFetchData(fetchFuntion) {
    const [loading, setLoading] = useState(true)
    const [data, setData] = useState(null)
    const [error, setError] = useState(null)
    console.log('useFetchData')
    useEffect(() => {
      async  function fetchData() {
            try {
                let result = await fetchFuntion()
                setData(result.data)
            } catch (err) {
                setError(err)
            }
            finally {
                setLoading(false)
            }
        }
        fetchData()
    }, [fetchFuntion])


  return {data,  loading, error }
}


export default useFetchData
