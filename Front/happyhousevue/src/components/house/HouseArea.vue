<template>
  <div>
    <seek-bar></seek-bar>
    <h5 class="text-center inline fontsans">
      <b>근방 </b>
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
    ...mapState(houseStore, ["range"]),
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
