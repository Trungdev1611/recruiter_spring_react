Tóm tắt các bước
Thiết lập các biến và hàm quản lý token:

Khai báo accessToken, refreshToken, và tokenExpiresIn.
Tạo các hàm setAccessToken, setRefreshToken, setTokenExpiresIn, và scheduleTokenRefresh.
Lên lịch tự động làm mới token:

Khi token được cập nhật hoặc lấy mới, sử dụng scheduleTokenRefresh để lên lịch làm mới token trước khi hết hạn.
Kiểm tra và làm mới token trước mỗi yêu cầu API:

Sử dụng hàm apiRequest để kiểm tra và làm mới token trước mỗi yêu cầu API nếu cần.


nếu mà phải đợi đến lúc request mới check token gần hết hạn thì giả sử 2 request cách nhau xa hơn thời gian token hết hạn thì người dùng bị lỗi rồi>
ChatGPT
Đúng vậy, nếu bạn chỉ kiểm tra token khi thực hiện yêu cầu mới, thì có thể xảy ra tình huống token hết hạn giữa các yêu cầu và người dùng sẽ gặp lỗi. Để giải quyết vấn đề này, bạn nên sử dụng cả hai chiến lược:

Tự động làm mới token trước khi nó hết hạn: Sử dụng setTimeout để làm mới token một khoảng thời gian trước khi nó hết hạn.
Kiểm tra token trước mỗi yêu cầu: Đảm bảo rằng mọi yêu cầu API đều kiểm tra và làm mới token nếu cần.



Tổng quan
Lưu Token vào localStorage sau khi đăng nhập. (client nhận cả access và refresh token)
        public class AuthenticationResponse {
        private String authenticationToken;
        private String refreshToken;
        private Instant expiresAt;
        private String username;    
}
Thiết lập setTimeout để tự động làm mới token trước khi (5 phút) hết hạn -> gọi lên api truyền refresh token để lấy token mới => validate refreshtoken với 1 table trong database => (không trả về RFresh token mới, chỉ trả về token mới)
Sử dụng Axios Interceptor để xử lý các yêu cầu API và làm mới token khi gặp lỗi 401.
Cập nhật lại token trong localStorage và thực hiện lại các yêu cầu bị hủy.
Logout thì delete refreshtoken trong DB
src/
|-- api/
|   |-- auth.js
|   |-- apiClient.js
|
|-- components/
|   |-- App.js
|
|-- hooks/
|   |-- useAuth.js
|
|-- utils/
|   |-- tokenUtils.js
|
|-- App.js
|-- index.js


// utils/tokenUtils.js

export const setAccessToken = (token) => {
  localStorage.setItem('accessToken', token);
};

export const setRefreshToken = (token) => {
  localStorage.setItem('refreshToken', token);
};

export const setTokenExpiration = (expiresIn) => {
  localStorage.setItem('tokenExpiresIn', Date.now() + expiresIn * 1000);
};

export const getAccessToken = () => localStorage.getItem('accessToken');
export const getRefreshToken = () => localStorage.getItem('refreshToken');
export const getTokenExpiration = () => localStorage.getItem('tokenExpiresIn');


// api/apiClient.js

import axios from 'axios';
import {
  getAccessToken,
  getRefreshToken,
  setAccessToken,
  setRefreshToken,
  setTokenExpiration,
} from '../utils/tokenUtils';
import { refreshAccessToken } from './auth';

const apiClient = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.request.use(
  async (config) => {
    const token = getAccessToken();
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      try {
        const response = await refreshAccessToken();
        const { accessToken, refreshToken, expiresIn } = response.data;
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        setTokenExpiration(expiresIn);

        // Update token in axios headers
        apiClient.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
        originalRequest.headers['Authorization'] = `Bearer ${accessToken}`;

        return apiClient(originalRequest);
      } catch (e) {
        console.error('Error refreshing token:', e);
      }
    }
    return Promise.reject(error);
  }
);

export default apiClient;


// api/auth.js

import axios from 'axios';
import { getRefreshToken } from '../utils/tokenUtils';

export const refreshAccessToken = () => {
  return axios.post('/api/refresh-token', {
    refreshToken: getRefreshToken(),
  });
};
