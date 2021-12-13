export const ROLE_USER = 'ROLE_USER';
export const ROLE_OWNER = 'ROLE_OWNER';

export function isRoleUser(roles) {
  return roles.indexOf(ROLE_USER) !== -1;
}

export function isRoleOwner(roles) {
  return roles.indexOf(ROLE_OWNER) !== -1;
}

export default {
  ROLE_USER,
  ROLE_AUTHOR: ROLE_OWNER,
  isRoleUser,
  isRoleOwner,
};
