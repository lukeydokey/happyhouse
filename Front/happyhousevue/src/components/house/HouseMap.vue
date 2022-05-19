<template>
  <b-container class="fluid">
    <b-container class="fluid" id="map"></b-container>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      map: null,
    };
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.575869, 126.976859, 16),
        level: 10,
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8c0ee03dd52a78f74e077c0615724e49`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      //console.log("이미 로딩됨: ", window.kakao);
      this.initMap();
    }
  },
};
</script>

<style scoped>
#map {
  width: 100vw;
  height: 100vh;
}
</style>
