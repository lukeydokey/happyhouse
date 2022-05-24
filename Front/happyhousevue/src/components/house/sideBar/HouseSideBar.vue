<template>
  <b-sidebar
    id="sidebar-right"
    icon="house-fill"
    title="HappyHouse"
    right
    shadow
    bg-color="#F5F5F5"
  >
    <b-card class="fontsans">
      <h4 class="small-title">위치 검색</h4>
      <b-tabs content-class="mt-3" justified class="mt-2">
        <b-tab title="법정동" active>
          <b-row>
            <b-col>
              <house-search :type="`dong`" />
            </b-col> </b-row
        ></b-tab>
        <b-tab title="동이름"
          ><b-row>
            <b-col><house-search :type="`dongname`" /></b-col></b-row
        ></b-tab>
        <b-tab title="좌표"
          ><b-row>
            <b-col><house-search :type="`latlng`" /></b-col></b-row
        ></b-tab>
      </b-tabs>

      <h4 class="small-title">아파트 목록</h4>
      <b-row>
        <house-list />
      </b-row>
    </b-card>
  </b-sidebar>
</template>

<script>
import HouseSearch from "@/components/house/sideBar/HouseSearch.vue";
import HouseList from "@/components/house/sideBar/HouseList.vue";
export default {
  name: "HouseSideBar",
  components: {
    HouseSearch,
    HouseList,
  },
};
</script>

<style></style>
