export default class UserDataRepository {
  pushState(state) {
    localStorage.setItem('user_id', state.id);
    this.updateData(state);
  }

  updateData(state) {
    localStorage.setItem('user_email', state.email);
    localStorage.setItem('user_first_name', state.firstName);
    localStorage.setItem('user_last_name', state.lastName);
  }

  removeState() {
    localStorage.removeItem('user_id');
    localStorage.removeItem('user_email');
    localStorage.removeItem('user_first_name');
    localStorage.removeItem('user_last_name');
  }

  getState() {
    if (!localStorage.getItem('token')) {
      return null;
    }

    return {
      id: localStorage.getItem('user_id') ?? '',
      email: localStorage.getItem('user_email') ?? '',
      firstName: localStorage.getItem('user_first_name') ?? '',
      lastName: localStorage.getItem('user_last_name') ?? '',
    };
  }
}
