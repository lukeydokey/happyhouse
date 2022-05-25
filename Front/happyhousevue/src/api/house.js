import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}
function coordList(params, success, fail) {
  api.get(`/map/coord`, { params: params }).then(success).catch(fail);
}

async function getHouseRecentInfo(aptCode, success, fail) {
  await api.get(`/map/aptRecentInfo/${aptCode}`).then(success).catch(fail);
}

function houseDealList(params, success, fail) {
  api.get(`/map/aptDeal`, { params: params }).then(success).catch(fail);
}
function SchoolList(params, success, fail) {
  api.get(`/map/school`, { params: params }).then(success).catch(fail);
}
function ParkList(params, success, fail) {
  api.get(`/map/park`, { params: params }).then(success).catch(fail);
}
function AreaList(params, success, fail) {
  api.get(`/map/area`, { params: params }).then(success).catch(fail);
}
async function setLikeApt(params, success, fail) {
  await api
    .post(`/map/likeapt`, JSON.stringify(params))
    .then(success)
    .catch(fail);
}
function deleteLikeApt(params, success, fail) {
  api.delete(`/map/likeapt`, { params: params }).then(success).catch(fail);
}
function getLikeApt(params, success, fail) {
  api.get(`/map/likeapt`, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  getHouseRecentInfo,
  houseDealList,
  SchoolList,
  ParkList,
  AreaList,
  coordList,
  setLikeApt,
  deleteLikeApt,
  getLikeApt,
};
