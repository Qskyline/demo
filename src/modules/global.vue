<script>
const config = {
  prefix: '/demo'
}

const registerStatus = {
  REGISTER_SUCCESS: 0,
  REGISTER_FAILED_U_E: 1,   //Username is already exist.
  REGISTER_FAILED_M_E: 2,   //MobliePhomeNumber is already exist.
  REGISTER_FAILED_UNKNOWN_ERROR: 3,   //Unknown error.
  REGISTER_FAILED_SECURITYCHECK: 4
}

const loginStatus = {
  LOGIN_SUCCESS: 100,
  LOGIN_USER_ALREADY_LOGGED: 101,
  LOGIN_AUTH_FAILED: 102,
  LOGIN_USER_LOCKED: 103,
  LOGIN_USER_NICKOUT_ERROR: 104,
  LOGIN_SECURITY_SQLINJECTION: 105
}

const runStatus = {
  STATUS_LOGGED: 200,
  STATUS_NOLOGGED: 201,
  STATUS_SESSION_TIMEOUT: 202,
  STATUS_SESSION_SINGLE_USER_RESTRICTION: 203,
  STATUS_ACCESS_DENY: 204
}

const logoutStatus = {
  LOGOUT_SUCCESS: 300
}

export default
{
  config,
  registerStatus,
  loginStatus,
  runStatus,
  logoutStatus
}
</script>