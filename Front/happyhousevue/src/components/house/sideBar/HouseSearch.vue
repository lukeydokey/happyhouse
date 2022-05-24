<template>
  <b-row class="ml-1 mr-1 mt-2 text-center">
    <b-form-group v-if="type === 'dong'">
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
    </b-form-group>
    <b-form-group v-else>
      <b-row>
        <b-col cols="9" class="mr-n3">
          <b-form-input
            v-model.trim="input"
            :placeholder="holder"
            @keypress.enter="sendKeyword"
          ></b-form-input>
        </b-col>
        <b-col>
          <b-button variant="outline-primary" @click="sendKeyword"
            >검색</b-button
          >
        </b-col>
      </b-row>
      <b-row class="mt-3" v-if="type === 'dongname'">
        <b-col v-if="searcheddongs.length > 0">
          <dong-search-item
            v-for="dong in searcheddongs"
            :key="dong.dongCode"
            v-bind="dong"
          />
        </b-col>
        <b-col v-else>
          <b-alert variant="secondary" show>검색된 법정동이 없습니다.</b-alert>
        </b-col>
      </b-row>
    </b-form-group>
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
import DongSearchItem from "./DongSearchItem.vue";

const houseStore = "houseStore";
const searchStore = "searchStore";
const memberStore = "memberStore";

export default {
  components: { DongSearchItem },
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      input: null,
    };
  },
  props: {
    type: String,
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    ...mapState(searchStore, ["hotplaces", "searcheddongs"]),
    ...mapState(memberStore, ["userInfo"]),
    holder() {
      return this.type === "dongname"
        ? "동이름 검색 (예 : 사직동)"
        : "좌표 검색 (예 : 31,132)";
    },
  },
  created() {
    if (this.type === "dong") {
      this.clearSidoList();
      this.getSido();
    }
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
    ...mapActions(searchStore, ["search", "getDongsByDongName"]),

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
    async sendKeyword() {
      if (this.type === "dongname") {
        this.getDongsByDongName(this.input);
      }
    },
  },
};
</script>

<style></style>
