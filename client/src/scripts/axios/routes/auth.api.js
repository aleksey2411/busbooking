import { instance } from '../configuration/instance';

const LOGIN = '/api/auth/login';
const SIGNUP_REQUEST = '/api/auth/registration';

export default class AuthApi {
  login({ email, password }) {
    return instance.post(LOGIN, { email, password });
  }

  signup(credentials) {
    return instance.post(SIGNUP_REQUEST, credentials);
  }

}
