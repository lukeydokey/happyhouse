<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-card bg-variant="first">
      <h3 class="underline-steelblue">
        <b-icon icon="person-lines-fill"></b-icon> Member Service
      </h3>
      <div id="map"></div>
      <!-- <router-view></router-view> -->
    </b-card>
  </b-container>
</template>

<script>
export default {
  name: "MemberView",
};
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };
// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);
</script>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=272767b10a84fed63f322fa7e795e0e0&libraries=services"
></script>
<style scoped>
#map {
  width: 400px;
  height: 400px;
}
</style>
