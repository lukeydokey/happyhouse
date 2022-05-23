<template>
  <b-tr @click="searchApt">
    <b-td>{{ index + 1 }}</b-td>
    <b-td>
      {{ sidoName }}
      {{ gugunName }}
      {{ dongName }}
    </b-td>
  </b-tr>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
const searchStore = "searchStore";
const memberStore = "memberStore";

export default {
  name: "SearchRankingByGenderItem",
  props: {
    index: Number,
    sidoName: String,
    gugunName: String,
    dongName: String,
    dongCode: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapActions(searchStore, ["search"]),
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
