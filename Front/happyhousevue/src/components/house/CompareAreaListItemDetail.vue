<template>
  <b-row
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{
      'mouse-over-bgcolor': isColor,
    }"
    style="width: 100%; margin: 0; cursor: pointer"
  >
    <b-col class="ml-2 mr-2">
      <div style="float: left">{{ object.name }}</div>
      <div style="float: right">{{ distance(object) }} M</div>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  // computed: {
  //   ...mapState(houseStore, ["house"]),
  // },
  data() {
    return {
      isColor: false,
      isSelected: false,
    };
  },
  computed: {
    ...mapGetters(houseStore, ["getCompareSelected", "selectedArea"]),
  },
  props: {
    object: Object,
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
      if (this.getSelected) {
        this.clearHouse();
      } else {
        this.detailHouse(this.house);
        this.getHouseDealList(this.house.aptCode);
      }
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    select(selected) {
      if (this.isSelected) {
        this.setSelectedArea(null);
      } else {
        this.setSelectedArea(selected);
      }
    },
    radians(degrees) {
      return (degrees * Math.PI) / 180;
    },
    distance(object) {
      return (
        6371000 *
        Math.acos(
          Math.cos(this.radians(object.lat)) *
            Math.cos(this.radians(this.getCompareSelected.lat)) *
            Math.cos(
              this.radians(this.getCompareSelected.lng) -
                this.radians(object.lng),
            ) +
            Math.sin(this.radians(object.lat)) *
              Math.sin(this.radians(this.getCompareSelected.lat)),
        )
      ).toFixed(0);
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
