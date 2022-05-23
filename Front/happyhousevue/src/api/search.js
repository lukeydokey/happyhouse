import { apiInstance } from "./index.js";

const api = apiInstance();

function getHotPlaces(success, fail) {
  api.get(`/search/hotplace`).then(success).catch(fail);
}

export { getHotPlaces };
