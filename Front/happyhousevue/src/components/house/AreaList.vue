<template>
  <b-container
    v-if="houses && houses.length != 0"
    class="bv-example-row"
    style="padding: 0"
  >
    <area-list-item
      v-for="(house, index) in areas"
      :key="index"
      :house="house"
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
import { mapState } from "vuex";

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
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
