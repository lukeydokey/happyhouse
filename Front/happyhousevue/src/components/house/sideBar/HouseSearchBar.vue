<template>
  <b-row class="ml-1 mr-1 mt-2 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-form-select
      v-model="sidoCode"
      :options="sidos"
      @change="gugunList"
      style="cursor: pointer"
    ></b-form-select>
    <b-form-select
      v-model="gugunCode"
      :options="guguns"
      @change="dongList"
      style="cursor: pointer"
    ></b-form-select>
    <b-form-select
      v-model="dongCode"
      :options="dongs"
      @change="searchApt"
      style="cursor: pointer"
    ></b-form-select>
    <search-ranking />
    <search-ranking-by-gender />
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
import SearchRanking from "@/components/house/sideBar/ranking/SearchRanking.vue";
import SearchRankingByGender from "@/components/house/sideBar/ranking/SearchRankingByGender.vue";

const houseStore = "houseStore";
const searchStore = "searchStore";
const memberStore = "memberStore";

export default {
  components: { SearchRanking, SearchRankingByGender },
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    ...mapState(searchStore, ["hotplaces"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.clearSidoList();
    this.getSido();
  },
  updated() {},
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "clearSidoList",
      "clearGugunList",
      "clearDongList",
    ]),
    ...mapActions(searchStore, ["search"]),

    gugunList() {
      // console.log(this.sidoCode);
      this.clearGugunList();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      // console.log(this.gugunCode);
      this.clearDongList();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    async searchApt() {
      // console.log(this.dongCode);
      if (this.dongCode) {
        this.getHouseList(this.dongCode);
        let searchInfo = {
          id: this.userInfo.id,
          dongCode: this.dongCode,
        };
        this.search(searchInfo);
      }
    },
  },
};
</script>

<style></style>
