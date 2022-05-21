<template>
  <b-container v-if="house" class="bv-example-row">
    <deal-table class="mt-3" />
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import DealTable from "@/components/house/DealTable.vue";

const houseStore = "houseStore";

export default {
  components: { DealTable },
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
