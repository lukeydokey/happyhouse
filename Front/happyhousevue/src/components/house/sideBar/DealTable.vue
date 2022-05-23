<template>
  <div>
    <h5><b>거래 매물</b></h5>
    <b-table
      striped
      hover
      :items="this.deals"
      :current-page="curPage"
      :per-page="perPage"
      :fields="fields"
    ></b-table>
    <b-pagination
      align="center"
      v-model="curPage"
      :per-page="perPage"
      :total-rows="this.deals.length"
    ></b-pagination>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  data() {
    return {
      curPage: 1,
      perPage: 10,
      // Note 'isActive' is left out and will not appear in the rendered table
      fields: [
        {
          key: "floor",
          label: "층수",
          sortable: false,
        },
        {
          key: "area",
          label: "평수",
          sortable: true,
        },

        {
          key: "dealAmount",
          label: "거래가",
          sortable: true,
        },
      ],
    };
  },

  props: {
    deals: Array,
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
};
</script>
