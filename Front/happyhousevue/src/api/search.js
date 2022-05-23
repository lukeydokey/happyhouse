import { apiInstance } from "./index.js";

const api = apiInstance();

function getHotPlaces(success, fail) {
  api.get(`/search/hotplace`).then(success).catch(fail);
}

function getHotPlacesByGender(gender, success, fail) {
  api.get(`/search/hotplace/${gender}`).then(success).catch(fail);
}

function search(searchInfo, success, fail) {
  api.post(`/search`, searchInfo).then(success).catch(fail);
}

export { getHotPlaces, search, getHotPlacesByGender };
