export default class AuthDataRepository {
  pushState(state) {
    localStorage.setItem("role", state.role);
    localStorage.setItem("token", state.token);
  }

  removeState() {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
  }

  changeRoleToAuthor(state) {
    localStorage.setItem("role", state.role);
  }

  getState() {
    if (!localStorage.getItem("token")) {
      return null;
    }

    return {
      role: localStorage.getItem("role") ?? "",
      token: localStorage.getItem("token") ?? ""
    };
  }
}
