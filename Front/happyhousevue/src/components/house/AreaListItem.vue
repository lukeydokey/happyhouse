<template>
  <div v-if="area.array.length != 0">
    <b-row
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      @click="select(area.class)"
      :class="{
        'mouse-over-bgcolor': isColor,
      }"
      style="width: 100%; margin: 0; cursor: pointer"
    >
      <b-col>
        <div style="float: left; font-size: 1.1em">
          <b>{{ area.class }}</b>
        </div>
        <div style="float: right; font-size: 1.1em">
          <b style="color: #2196f3">{{ area.array.length }}</b> 건
        </div>
      </b-col>
    </b-row>
    <div v-if="area.class == getSelectedArea">
      <area-list-item-detail
        v-for="(object, index) in this.area.array"
        :key="index"
        :object="object"
      />
    </div>

    <hr style="margin: 0; width: 100%" />
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import AreaListItemDetail from "@/components/house/AreaListItemDetail.vue";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  components: {
    AreaListItemDetail,
  },
  data() {
    return {
      isColor: false,
    };
  },
  computed: {
    ...mapGetters(houseStore, ["getSelected", "getSelectedArea"]),
  },
  props: {
    area: Object,
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
      if (this.getSelectedArea == selected) {
        this.setSelectedArea(null);
      } else {
        this.setSelectedArea(selected);
      }
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
