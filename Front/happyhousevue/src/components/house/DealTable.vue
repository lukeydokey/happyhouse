<template>
  <div>
    <h5><b>거래 매물</b></h5>
    <b-table striped hover :items="this.deals" :fields="fields"></b-table>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";
export default {
  data() {
    return {
      // Note 'isActive' is left out and will not appear in the rendered table
      fields: [
        {
          key: "area",
          label: "평수",
          sortable: true,
        },
        {
          key: "floor",
          label: "층수",
          sortable: false,
        },
        {
          key: "dealAmount",
          label: "거래가",
          sortable: true,
        },
      ],
    };
  },
  methods: {
    ...mapActions(houseStore, ["getHouseDealList"]),
  },

  props: {
    deals: Array,
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
};
</script>
