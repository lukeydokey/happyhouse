import {
  getHotPlaces,
  search,
  getHotPlacesByGender,
  getDongsByDongName,
} from "@/api/search.js";

const searchStore = {
  namespaced: true,
  state: {
    hotplaces: [],
    hotplacesbygender: [],
    searchLog: [],
    searcheddongs: [],
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
    SET_HOT_PLACES_BY_GENDER: (state, hotplacesbygender) => {
      hotplacesbygender.forEach((hotplace) => {
        state.hotplacesbygender.push(hotplace);
      });
    },
    CLEAR_HOT_PLACES_BY_GENDER: (state) => {
      state.hotplacesbygender = [];
    },
    SET_SEARCH_LOG: (state, searchInfo) => {
      state.searchLog.push(searchInfo);
    },
    SET_SEARCHED_DONGS: (state, searcheddongs) => {
      searcheddongs.forEach((dong) => {
        state.searcheddongs.push(dong);
      });
    },
    CLEAR_SEARCHED_DONGS: (state) => {
      state.searcheddongs = [];
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
    getHotPlacesByGender({ commit }, gender) {
      getHotPlacesByGender(
        gender,
        (response) => {
          commit("SET_HOT_PLACES_BY_GENDER", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    clearHotPlacesByGender({ commit }) {
      commit("CLEAR_HOT_PLACES_BY_GENDER");
    },
    search({ commit }, searchInfo) {
      search(
        searchInfo,
        (response) => {
          if (response.data === "success") commit("SET_SEARCH_LOG", searchInfo);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDongsByDongName({ commit }, dongName) {
      commit("CLEAR_SEARCHED_DONGS");
      getDongsByDongName(
        dongName,
        (response) => {
          commit("SET_SEARCHED_DONGS", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    clearSearchedDongs({ commit }) {
      commit("CLEAR_SEARCHED_DONGS");
    },
  },
};

export default searchStore;
