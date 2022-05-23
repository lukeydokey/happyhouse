import { getHotPlaces } from "@/api/search.js";

const searchStore = {
  namespaced: true,
  state: {
    hotplaces: [],
  },
  getters: {},
  mutations: {
    SET_HOT_PLACES: (state, hotplaces) => {
      hotplaces.forEach((hotplace) => {
        state.hotplaces.push(hotplace);
      });
    },
    CLEAR_HOT_PLACES: (state) => {
      state.hotplaces = [];
    },
  },
  actions: {
    getHotPlaces({ commit }) {
      getHotPlaces(
        (response) => {
          commit("SET_HOT_PLACES", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    clearHotPlaces({ commit }) {
      commit("CLEAR_HOT_PLACES");
    },
  },
};

export default searchStore;
