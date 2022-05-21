<template>
  <div class="fluid-container">
    <b-sidebar
      id="sidebar-right"
      icon="house-fill"
      title="HappyHouse"
      right
      shadow
      bg-color="#F5F5F5"
    >
      <b-card bg-variant="first" class="fontsans">
        <h4 class="small-title">위치 검색</h4>
        <b-row>
          <b-col>
            <house-search-bar></house-search-bar>
          </b-col>
        </b-row>
        <h4 class="small-title">아파트 목록</h4>
        <b-row>
          <house-list />
        </b-row>
      </b-card>
    </b-sidebar>
    <house-map style="width: 100%; height: 100%"> </house-map>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseMap from "@/components/house/HouseMap.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseMap,
  },
};
</script>
<style scoped>
.sidebar {
  background-color: rgb(30, 67, 126);
}
.fluid-container {
  width: 100vw;
  height: 90vh;
}
body {
  margin: 0;
}
</style>
<style>
.small-title {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
