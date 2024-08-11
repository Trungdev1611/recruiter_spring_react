import LoadingSimple from '@src/components/loading/LoadingSimple'
import useFetchData from '@src/hooks/useFetchData';

const withLoadingError = (Component, fetchFunction) => {
  return function WithLoadingError(props) {
    const { data, loading, error } = useFetchData(fetchFunction);
    if(loading) {
        return <LoadingSimple />
    }
    if(error) {
        return <div className='text-2xl text-slate-600 font-semibold'>{error.message || "Có lỗi xảy ra"}</div>
    }
    return <Component {...props} data={data}/>
  }
}

export default withLoadingError