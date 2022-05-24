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
            <b-col
              ><h5>
                <b>{{ this.curAddress }} </b>
                <div style="display: flex; align-items: center">
                  주변 검색 중&nbsp;&nbsp;
                  <b-spinner
                    variant="primary"
                    type="grow"
                    label="Spinning"
                  ></b-spinner>
                </div></h5></b-col></b-row
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

import { mapGetters, mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseSideBar",
  components: {
    HouseSearch,
    HouseList,
  },
  methods: {
    ...mapGetters(houseStore, ["getCurAddress"]),
  },
  computed: {
    ...mapState(houseStore, ["curAddress"]),
  },
};
</script>

<style></style>
