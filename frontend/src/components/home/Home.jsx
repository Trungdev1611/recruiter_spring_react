import JobItem from "../job/JobItem"
import imageCompany from './../../assets/image_company.jpg'
import jobApi from "@src/api/jobApi"
import withLoadingError from "@src/HOC/withLoadingError"
const HomeContent = ({data}) => {
  console.log(`dataHome`, data)
  return (
    <div className="p-6 md:p-4 flex flex-col gap-y-4">
      <JobItem image={imageCompany}/>
      <JobItem image={imageCompany}/>
      <JobItem image={imageCompany}/>
      <JobItem image={imageCompany}/>
      <JobItem image={imageCompany}/>
    </div>
  )
}

const Home = withLoadingError(HomeContent, () => jobApi.getListJob())
export default Home


