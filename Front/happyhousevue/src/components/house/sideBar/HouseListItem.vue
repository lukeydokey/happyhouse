<template>
  <div>
    <b-row
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{
        'selected-bgcolor': getSelected && getSelected.jibun == house.jibun,
        'mouse-over-bgcolor': isColor,
      }"
      style="width: 100%; margin: 0; cursor: pointer"
    >
      <b-col class="align-self-center mt-1">
        <b @click="selectHouse">{{ house.aptName }}</b>
        <img
          v-if="this.getLikedApt.indexOf(this.house.aptCode.toString()) == -1"
          src="@/assets/like/emptyheart.png"
          style="float: right; width: 1.2em; height: 1.2em"
          @click="like(userInfo.id, house.aptCode)"
        />
        <img
          v-else
          src="@/assets/like/heart.png"
          style="float: right; width: 1.2em; height: 1.2em"
          @click="like(userInfo.id, house.aptCode)"
        />
        <br />
        <div @click="selectHouse">
          {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
          {{ house.jibun }}
        </div>
        <hr style="margin: 0; width: 100%" />
      </b-col>
    </b-row>
    <div v-if="getSelected && getSelected.jibun == house.jibun">
      <house-detail />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
import HouseDetail from "@/components/house/sideBar/HouseDetail.vue";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: { HouseDetail },
  name: "HouseListItem",
  // computed: {
  //   ...mapState(houseStore, ["house"]),
  // },
  data() {
    return {
      isColor: false,
    };
  },
  computed: {
    ...mapGetters(houseStore, ["getSelected", "getLikedApt"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, [
      "detailHouse",
      "getHouseDealList",
      "clearHouse",
      "setSelectedArea",
      "likeAptSet",
    ]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      if (this.getSelected && this.getSelected.aptCode === this.house.aptCode) {
        this.clearHouse();
      } else {
        this.detailHouse(this.house);
        this.getHouseDealList(this.house.aptCode);
      }

      this.setSelectedArea(null);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    like(id, aptCode) {
      console.log("test");
      console.log(this.getLikedApt.indexOf(this.house.aptCode.toString()));
      this.likeAptSet({ id, aptCode });
      console.log(this.getLikedApt.indexOf(this.house.aptCode.toString()));
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgb(236, 236, 236);
}
.selected-bgcolor {
  background-color: rgb(193, 230, 255);
}
</style>
