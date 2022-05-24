<template>
  <div>
    <b-row
      @click="selectHouse"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{
        'selected-bgcolor': getSelected && getSelected.jibun == house.jibun,
        'mouse-over-bgcolor': isColor,
      }"
      style="width: 100%; margin: 0; cursor: pointer"
    >
      <b-col class="align-self-center mt-1">
        <b>{{ house.aptName }}</b>
        <br />{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
        {{ house.jibun }}
        <hr style="margin: 0; width: 100%" />
      </b-col>
    </b-row>
    <div v-if="getSelected && getSelected.jibun == house.jibun">
      <house-detail />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import HouseDetail from "@/components/house/sideBar/HouseDetail.vue";

const houseStore = "houseStore";

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
    ...mapGetters(houseStore, ["getSelected"]),
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
