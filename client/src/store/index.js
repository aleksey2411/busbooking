import { createStore } from 'vuex';
import auth from './auth/auth';
import user from './user/user';

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    user,
  },
  strict: true,
});
