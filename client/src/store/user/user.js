import UserDataRepository from './UserDataRepository.js';

const userDataRepository = new UserDataRepository();

const initialState = userDataRepository.getState() || {
  id: null,
  email: null,
  firstName: null,
  lastName: null,
};

export default {
  namespaced: true,
  state: initialState,
  mutations: {
    loginSuccess(state, response) {
      const { data } = response;

      state.id = data.user.id;
      state.email = data.user.email;
      state.firstName = data.user.firstName;
      state.lastName = data.user.lastName;

      userDataRepository.pushState(state);
    },
    logout(state) {
      state.id = null;
      state.username = null;
      state.firstName = null;
      state.lastName = null;

      userDataRepository.removeState();
    },
  },
};
