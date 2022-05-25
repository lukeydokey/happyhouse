<template>
  <div class="fluid-container">
    <house-side-bar />
    <house-map style="width: 100%; height: 100%"> </house-map>
  </div>
</template>

<script>
import HouseMap from "@/components/house/HouseMap.vue";
import { mapActions, mapState } from "vuex";
import HouseSideBar from "@/components/house/sideBar/HouseSideBar.vue";
const houseStore = "houseStore";
const searchStore = "searchStore";
const memberStore = "memberStore";
export default {
  name: "HouseView",
  components: {
    HouseMap,
    HouseSideBar,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.setCoordOff();
    this.likeAptGet(this.userInfo.id);
    this.clearHotPlaces();
    this.clearHotPlacesByGender();
    this.clearSearchedDongs();
    this.clearHouseList();
    this.setIsSearching(true);
    this.getHotPlaces();
    this.getHotPlacesByGender(this.userInfo.gender);
  },
  destroyed() {
    this.setIsSearching(false);
  },
  methods: {
    ...mapActions(houseStore, [
      "setIsSearching",
      "clearHouseList",
      "setCoordOff",
      "likeAptGet",
    ]),
    ...mapActions(searchStore, [
      "getHotPlaces",
      "clearHotPlaces",
      "getHotPlacesByGender",
      "clearHotPlacesByGender",
      "clearSearchedDongs",
    ]),
  },
};
</script>
<style scoped>
.sidebar {
  background-color: rgb(30, 67, 126);
}
.fluid-container {
  width: 100vw;
  height: 90vh;
}
body {
  margin: 0;
}
</style>
<style>
.small-title {
  display: inline-block;
}
</style>
