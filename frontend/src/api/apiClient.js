import axios from "axios";

const BASE_URL = "http://localhost:8080";
// const BASE_URL = "https://api-generator.retool.com";

const instance = axios.create({
  baseURL: BASE_URL,
  timeout: 10000,
});

instance.defaults.withCredentials = true;
instance.defaults.headers.post["Content-Type"] = "application/json";
instance.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    const token = localStorage.getItem("token");
    console.log(`tokenn`, token);
    if (token) {
      config.headers.Authorization = "Bearer " + token; //cấu hình token cho all request
    }
    return config;
  },
  function (error) {
    console.log("request Error", error);
    alert("error")
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  function (response) {
    return response.data;
  },
  function (error) {
    if (error.response?.status === 401) {
      console.log("error401", error);
    } else {
      console.log("error", error);
    }
    return Promise.reject(error.response?.data || error);
  }
);

export const typeFormData = {
  headers: { "Content-Type": "multipart/form-data" },
};

//nếu truyền formData thì ta pass thêm 1 tham số thứ 3 vào method là typeFormData thôi, nó sẽ tự ghi đè application/json
//VD: post: (url, payload?: unknown) => instance.post(url, payload, typeFormData),
export const Apiclient = {
  get: (url, params) => instance.get(url, { params }),
  post: (url, payload) => instance.post(url, payload),
  patch: (url, payload) => instance.patch(url, payload),
  put: (url, payload) => instance.put(url, payload),
  delete: (url) => instance.delete(url),
  postFormData: (url, payload) =>
    instance.post(url, payload, {
      headers: { "Content-Type": "multipart/form-data" },
    }),
};
