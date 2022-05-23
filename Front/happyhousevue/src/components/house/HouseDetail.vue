<template>
  <b-container v-if="house" class="bv-example-row">
    <deal-chart :chart-data="chartData" class="mt-3" />
    <deal-table class="mt-3" />
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
  computed: {
    ...mapState(houseStore, ["house", "houseRecentInfo"]),
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
            data: this.houseRecentInfo.min,
          },
          {
            label: "max",
            backgroundColor: "#99EEFF",
            data: this.houseRecentInfo.max,
          },
        ],
      };
    },
  },
  async created() {
    await this.getHouseRecentInfo(this.house.aptCode);
    console.log(this.houseRecentInfo.min + "created");
  },
  methods: {
    ...mapActions(houseStore, ["getHouseRecentInfo"]),
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
