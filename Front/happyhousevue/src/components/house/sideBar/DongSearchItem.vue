<template>
  <b-row
    @click="searchApt"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{
      'mouse-over-bgcolor': isColor,
    }"
    style="width: 100%; margin: 0; cursor: pointer"
  >
    <b-col class="align-self-center mt-2">
      {{ sidoName }} {{ gugunName }} {{ dongName }}
      <hr style="margin: 0; width: 100%" />
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
const searchStore = "searchStore";
const memberStore = "memberStore";

export default {
  name: "DongSearchItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
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
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.mouse-over-bgcolor {
  background-color: rgb(236, 236, 236);
}
</style>
