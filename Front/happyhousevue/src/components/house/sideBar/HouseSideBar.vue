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
        <b-tab @click="coordOff" title="법정동" active>
          <b-row>
            <b-col>
              <house-search :type="`dong`" />
            </b-col> </b-row
        ></b-tab>
        <b-tab @click="coordOff" title="동이름"
          ><b-row>
            <b-col><house-search :type="`dongname`" /></b-col></b-row
        ></b-tab>
        <b-tab @click="coordOn" title="좌표"
          ><b-row>
            <b-col
              ><h5>
                <div v-if="this.curAddress">
                  <b>{{ this.curAddress }} </b>
                  <div style="display: flex; align-items: center">
                    주변 검색 중&nbsp;&nbsp;
                    <b-spinner
                      variant="primary"
                      type="grow"
                      label="Spinning"
                    ></b-spinner>
                  </div>
                </div>
                <div v-else>
                  <b-alert variant="danger" show
                    >현재 줌 레벨에서는 검색이 불가합니다.</b-alert
                  >
                </div>
              </h5></b-col
            ></b-row
          ></b-tab
        >
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

import { mapGetters, mapState, mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseSideBar",
  components: {
    HouseSearch,
    HouseList,
  },
  methods: {
    ...mapGetters(houseStore, ["getCurAddress"]),
    ...mapActions(houseStore, ["setCoordOn", "setCoordOff"]),
    coordOn() {
      this.setCoordOn();
    },
    coordOff() {
      this.setCoordOff();
    },
  },
  computed: {
    ...mapState(houseStore, ["curAddress"]),
  },
};
</script>

<style></style>
