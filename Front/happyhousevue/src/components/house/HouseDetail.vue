<template>
  <b-container v-if="house" class="bv-example-row">
    <deal-chart :chart-data="chartData" class="mt-3" />
    <deal-table class="mt-3" :deals="this.deals" />
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import DealTable from "@/components/house/DealTable.vue";
import DealChart from "@/components/house/DealChart.vue";

const houseStore = "houseStore";

export default {
  components: { DealTable, DealChart },
  name: "HouseDetail",
  data() {
    return {
      min: [0, 0, 0, 0],
      max: [0, 0, 0, 0],
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "houseRecentInfo", "deals"]),
    // house() {
    //   return this.$store.state.house;
    // },
    chartData() {
      return {
        labels: ["2019", "2020", "2021", "2022"],
        datasets: [
          {
            label: "min",
            backgroundColor: "#99CC00",
            data: this.min,
          },
          {
            label: "max",
            backgroundColor: "#99EEFF",
            data: this.max,
          },
        ],
      };
    },
  },
  async created() {
    await this.clearHouseRecentInfo();
    await this.getHouseRecentInfo(this.house.aptCode);
    this.min = this.houseRecentInfo.min;
    this.max = this.houseRecentInfo.max;
  },
  methods: {
    ...mapActions(houseStore, ["getHouseRecentInfo", "clearHouseRecentInfo"]),
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
