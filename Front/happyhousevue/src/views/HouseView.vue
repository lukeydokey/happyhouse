<template>
  <b-container
    class="justify-content-md-center mt-10 category"
    style="background-color: red; width: 100%"
  >
    <b-button v-b-toggle.sidebar-right>Toggle Sidebar</b-button>
    <b-sidebar id="sidebar-right" title="Sidebar" right shadow>
      <b-card bg-variant="first">
        <h3 class="underline-orange">
          <b-icon icon="house-fill"></b-icon> House Service
        </h3>
        <b-row>
          <b-col>
            <house-search-bar></house-search-bar>
          </b-col>
        </b-row>
        <b-row>
          <house-list />
          <house-detail />
        </b-row>
      </b-card>
    </b-sidebar>
    <house-map style="width: 100%; height: 500px"></house-map>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseMap from "@/components/house/HouseMap.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    HouseMap,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}
body {
  overflow: hidden;
}
</style>
