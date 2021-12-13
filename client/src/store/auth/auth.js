import { api } from '../../scripts/axios/axios';
import { isRoleOwner, isRoleUser } from '../../scripts/role/roles-constants.js';
import AuthDataRepository from './AuthDataRepository.js';

const authDataRepository = new AuthDataRepository();

const initialState = authDataRepository.getState() || {
  role: null,
  token: null,
};

export default {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, credentials) {
      return new Promise((resolve, reject) => {
        api.auth
          .login(credentials)
          .then((response) => {
            if (response.data.token === undefined) {
              throw new Error('token not found');
            }
            commit('loginSuccess', response);
            commit('user/loginSuccess', response, { root: true });
            resolve(response);
          })
          .catch((error) => {
            commit('logout');
            reject(error);
          });
      });
    },
    logout({ commit }) {
      commit('logout');
      commit('user/logout', null, { root: true });
    },
  },

  getters: {
    loggedIn: (state) => state.token !== null,
    isRoleUser: (state) => isRoleUser(state.role),
    isRoleOwner: (state) => isRoleOwner(state.role),
  },

  mutations: {
    loginSuccess(state, response) {
      const { token } = response.data;

      state.role = response.data.user.role;
      state.token = token;
      authDataRepository.pushState(state);
    },
    logout(state) {
      state.role = null;
      state.token = null;

      authDataRepository.removeState();
    },
  },
};
