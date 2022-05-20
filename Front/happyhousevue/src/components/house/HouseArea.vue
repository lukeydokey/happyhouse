<template>
  <seek-bar></seek-bar>
</template>

<script>
import SeekBar from "@/components/house/SeekBar.vue";

export default {
  name: "HouseArea",
  components: {
    SeekBar,
  },
  data() {
    return {};
  },
  computed: {
    // ...mapState(houseStore, ["houses"]),
    // // houses() {
    // //   return this.$store.state.houses;
    // // },
  },
};
</script>

<style></style>
