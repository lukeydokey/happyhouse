<template>
  <b-container
    v-if="like && like.length != 0"
    class="bv-example-row"
    style="padding: 0"
  >
    <house-list-item
      v-for="(house, index) in like"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col
        ><b-alert variant="secondary" show
          >담긴 리스트가 없습니다.</b-alert
        ></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/sideBar/HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["like"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
