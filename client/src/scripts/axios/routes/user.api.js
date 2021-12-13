import { getAuth, postAuth } from '../configuration/instance';

const EDIT_USER = '/api/user/edit';
const LIST_USERS = (page) => `/api/user/list/${page}`;
const SHOW_USER = (userId) => `/api/user/show/${userId}`;
const CHANGE_ROLE_TO_AUTHOR = '/api/user/change-role/author';
const IS_USERNAME_UNIQUE = (username) => `/api/user/is-unique/username/${username}`;
const IS_EMAIL_UNIQUE = (email) => `/api/user/is-unique/email/${email}`;
const SUBSCRIPTIONS = (userId, page) => `/api/user/subscriptions/${userId}/${page}`;
const SUBSCRIBERS = (userId, page) => `/api/user/subscribers/${userId}/${page}`;
const SUBSCRIPTION_INFO = (userId) => `/api/user/subscription/info/${userId}`;
const SUBSCRIBE_TO_AUTHOR = '/api/user/subscription/subscribe';
const UNSUBSCRIBE_FROM_AUTHOR = '/api/user/subscription/unsubscribe';

export default class UserApi {
  edit(data) {
    return postAuth(EDIT_USER, data);
  }

  list(page) {
    return getAuth(LIST_USERS(page));
  }

  show(userId) {
    return getAuth(SHOW_USER(userId));
  }

  changeRoleToAuthor() {
    return postAuth(CHANGE_ROLE_TO_AUTHOR, {});
  }

  isUsernameUnique(username) {
    return getAuth(IS_USERNAME_UNIQUE(username));
  }

  isEmailUnique(email) {
    return getAuth(IS_EMAIL_UNIQUE(email));
  }

  /* Subscription */
  subscriptionInfo(userId) {
    return getAuth(SUBSCRIPTION_INFO(userId));
  }

  subscriptions(userId, page) {
    return getAuth(SUBSCRIPTIONS(userId, page));
  }

  subscribers(userId, page) {
    return getAuth(SUBSCRIBERS(userId, page));
  }

  subscribeToAuthor(authorId) {
    return postAuth(SUBSCRIBE_TO_AUTHOR, { authorId });
  }

  unsubscribeFromAuthor(authorId) {
    return postAuth(UNSUBSCRIBE_FROM_AUTHOR, { authorId });
  }
}
