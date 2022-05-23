<template>
  <div>
    <h4 class="small-title">주변 정보</h4>
    <seek-bar></seek-bar>
    <h5 class="text-center inline fontsans">
      <b>{{ this.house.aptName }}<br /></b><b> 근방 </b>
      <b class="meter">{{ range }}M </b>
      <b>내의 편의시설</b>
    </h5>
    <h4></h4>
  </div>
</template>

<script>
import SeekBar from "@/components/house/SeekBar.vue";
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseArea",
  components: {
    SeekBar,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["range", "house"]),
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
