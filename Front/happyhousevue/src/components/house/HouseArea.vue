<template>
  <div>
    <h4 class="small-title">주변 정보</h4>
    <b-button
      variant="secondary"
      @click="this.changeCompare"
      class="float-right"
      >관심 매물 비교</b-button
    >
    <seek-bar></seek-bar>
    <h5 class="text-center inline fontsans">
      <b>{{ this.house.aptName }}<br /></b><b> 근방 </b>
      <b class="meter">{{ range }}M </b>
      <b>내의 주변시설</b>
    </h5>
    <area-list />
  </div>
</template>

<script>
import SeekBar from "@/components/house/SeekBar.vue";
import AreaList from "@/components/house/AreaList.vue";
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseArea",
  components: {
    SeekBar,
    AreaList,
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(houseStore, [
      "getSchoolList",
      "getParkList",
      "changeCompare",
    ]),
    compareMode() {
      console.log(this.compare);
      this.compare = !this.compare;
    },
  },
  computed: {
    ...mapState(houseStore, ["range", "house", "compare"]),
    // ...mapState(houseStore, ["houses"]),
    // // houses() {
    // //   return this.$store.state.houses;
    // // },
  },
};
</script>

<style>
.meter {
  color: #2196f3;
}
</style>
