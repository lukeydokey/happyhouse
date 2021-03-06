import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

function checkId(userid, success, fail) {
  api.get(`/user/idCheck/${userid}`).then(success).catch(fail);
}

async function register(user, success, fail) {
  await api
    .post(`/user/register`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function updateInfo(user, success, fail) {
  await api.put(`/user/update`, JSON.stringify(user)).then(success).catch(fail);
}

function delUser(userid, success, fail) {
  console.log(userid);
  api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}
// function logout(success, fail)

export { login, findById, checkId, register, updateInfo, delUser };
