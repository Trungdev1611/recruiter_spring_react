import { Apiclient } from "./apiClient";
class JobApi {
    getListJob() {
        return Apiclient.get(`/jobs`)
    }
    getJobDetail(idJob) {
        return Apiclient.get(`/jobs/${idJob}`)
    }
    createJob(dataJob) {
        return Apiclient.post(`/create-job`, dataJob)
    }
}

const jobApi = new JobApi();
export default jobApi