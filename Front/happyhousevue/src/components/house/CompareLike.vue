<template>
  <div>
    <b-form-select
      class="mt-4"
      v-model="selectedPlace"
      :options="getLikeOption"
      @change="setCompareLike"
      style="cursor: pointer"
    ></b-form-select>
    <h5 v-if="selectedPlace" class="text-center inline fontsans mt-4">
      <b>{{ selectedPlace.aptName }}<br /></b><b> 근방 </b>
      <b class="meter">{{ range }}M </b>
      <b>내의 주변시설</b>
    </h5>
    <compare-area-list />
  </div>
</template>
<script>
import { mapState, mapActions, mapGetters } from "vuex";
import CompareAreaList from "@/components/house/CompareAreaList.vue";
const houseStore = "houseStore";

export default {
  name: "CompareLike",
  components: {
    CompareAreaList,
  },
  data() {
    return {
      selectedPlace: null,
    };
  },
  methods: {
    ...mapActions(houseStore, [
      "getSchoolList",
      "getParkList",
      "changeCompare",
      "compareLikeSet",
    ]),
    compareMode() {
      console.log(this.compare);
      this.compare = !this.compare;
    },
    setCompareLike() {
      this.compareLikeSet(this.selectedPlace);
    },
  },
  computed: {
    ...mapState(houseStore, ["range", "house", "compare"]),
    ...mapGetters(houseStore, ["getLikeOption"]),

    // ...mapState(houseStore, ["houses"]),
    // // houses() {
    // //   return this.$store.state.houses;
    // // },
  },
};
</script>
