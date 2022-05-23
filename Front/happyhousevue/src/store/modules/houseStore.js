import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  getHouseRecentInfo,
  houseDealList,
} from "@/api/house.js";
import { eventBus } from "@/main.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    houseRecentInfo: { aptCode: 0, min: [0, 0, 0, 0], max: [0, 0, 0, 0] },
    markers: [],
    range: 0,
    deals: [],
  },

  getters: {
    checkMarkersLenght: function (state) {
      return state.markers.length;
    },
    getSelected: function (state) {
      return state.house;
    },
  },

  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_MARKERS(state) {
      if (this.checkMarkersLenght > 0) {
        state.markers.forEach((item) => {
          item.setMap(null);
        });
      }
    },
    SET_HOUSE_LIST(state, houses) {
      // console.log(houses);
      state.houses = houses;
      eventBus.$emit("apartUpdated", "apartUpdated");
    },
    SET_HOUSEDEAL_LIST(state, deals) {
      // console.log(houses);
      state.deals = deals.data;
      eventBus.$emit("dealsUpdated", deals);
    },
    SET_DETAIL_HOUSE(state, house) {
      // console.log("Mutations", house);
      state.house = house;

      eventBus.$emit("detailApart", house);
    },
    SET_HOUSE_RECENT_INFO(state, houseRecentInfo) {
      state.houseRecentInfo.aptCode = houseRecentInfo[0].aptCode;
      console.log(houseRecentInfo);
      houseRecentInfo.forEach((info) => {
        if (info.dealYear === "2019") {
          state.houseRecentInfo.min[0] = info.min;
          state.houseRecentInfo.max[0] = info.max;
        } else if (info.dealYear === "2020") {
          state.houseRecentInfo.min[1] = info.min;
          state.houseRecentInfo.max[1] = info.max;
        } else if (info.dealYear === "2021") {
          state.houseRecentInfo.min[2] = info.min;
          state.houseRecentInfo.max[2] = info.max;
        } else if (info.dealYear === "2022") {
          state.houseRecentInfo.min[3] = info.min;
          state.houseRecentInfo.max[3] = info.max;
        }
      });
    },
    CLEAR_HOUSE_RECENT_INFO(state) {
      state.houseRecentInfo = {
        aptCode: 0,
        min: [0, 0, 0, 0],
        max: [0, 0, 0, 0],
      };
    },
    PUSH_MARKER(state, marker) {
      state.markers.push(marker);
    },
    SET_RANGE(state, range) {
      state.range = range;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    clearSidoList: ({ commit }) => {
      commit("CLEAR_SIDO_LIST");
    },
    clearGugunList: ({ commit }) => {
      commit("CLEAR_GUGUN_LIST");
    },
    clearDongList: ({ commit }) => {
      commit("CLEAR_DONG_LIST");
    },
    clearMarkers: ({ commit }) => {
      commit("CLEAR_MARKERS");
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = { dong: dongCode };
      houseList(
        params,
        (response) => {
          commit("SET_HOUSE_LIST", response);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getHouseRecentInfo({ commit }, aptCode) {
      await this.clearHouseRecentInfo();
      await getHouseRecentInfo(
        aptCode,
        (response) => {
          commit("SET_HOUSE_RECENT_INFO", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async clearHouseRecentInfo({ commit }) {
      await commit("CLEAR_HOUSE_RECENT_INFO");
    },

    getHouseDealList: ({ commit }, aptCode) => {
      const params = { aptCode: aptCode };
      houseDealList(
        params,
        (response) => {
          commit("SET_HOUSEDEAL_LIST", response);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    pushMarker: ({ commit }, marker) => {
      commit("PUSH_MARKER", marker);
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
    setRange: ({ commit }, range) => {
      commit("SET_RANGE", range);
    },
  },
};

export default houseStore;
