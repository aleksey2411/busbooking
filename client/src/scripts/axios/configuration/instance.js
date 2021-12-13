import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://127.0.0.1:8080',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

const postAuth = function post(url, data) {
  return instance.post(url, data, { headers: { Authorization: 'Bearer ' + localStorage.getItem('token')} });
};

const getAuth = function get(url){
  return instance.get(url, { headers: { Authorization: 'Bearer ' + localStorage.getItem('token')} });
};
export { instance, postAuth, getAuth };
