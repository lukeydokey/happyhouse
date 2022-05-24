<template>
  <b-container
    v-if="houses && houses.length != 0 && this.areas.length != 0"
    class="bv-example-row"
    style="padding: 0"
  >
    <!-- <li v-for="(area, index) in this.getArea" :key="index">
      {{ area }}
      2
    </li> -->
    <area-list-item
      v-for="(area, index) in this.getArea"
      :key="index"
      :area="area"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col
        ><b-alert variant="secondary" show
          >검색된 결과가 없습니다.</b-alert
        ></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
import AreaListItem from "@/components/house/AreaListItem.vue";
import { mapState, mapGetters } from "vuex";

const houseStore = "houseStore";

export default {
  name: "AreaList",
  components: {
    AreaListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses", "areas"]),
    ...mapGetters(houseStore, ["getArea"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
