<template>
  <div class="fluid-container" style="width: 140px; z-index: 3" id="map">
    <b-card
      title="오늘의 정보"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem; z-index: 4"
      class="mb-2 float-right fontsans"
    >
      <div>추천 정보/뉴스/웹 크롤링 내용이 들어갈 자리</div>
      <strong>
        맵에 핀이 안 뜨면 상단바의 홈 버튼을 눌렀다가 아파트 버튼을 누르면
        됩니다.
      </strong>
      <b-button variant="primary" v-b-toggle.sidebar-right>매물 검색</b-button>
    </b-card>
  </div>
</template>

<script>
/*global kakao*/
import { eventBus } from "@/main.js";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  data() {
    return {
      map: null,
      markerPositions: [
        {
          title: "카카오",
          latlng: new kakao.maps.LatLng(33.450705, 126.570677),
        },
        {
          title: "생태연못",
          latlng: new kakao.maps.LatLng(33.450936, 126.569477),
        },
        {
          title: "텃밭",
          latlng: new kakao.maps.LatLng(33.450705, 126.570177),
        },
        {
          title: "근린공원",
          latlng: new kakao.maps.LatLng(33.451393, 126.570738),
        },
      ],
      markers: [],
      // 화면에 표시되어있는 marker들
    };
  },
  methods: {
    ...mapActions(houseStore, ["pushMarker"]),
    initMap() {
      console.log();
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.575869, 126.976859, 16),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      // this.displayMarkers(this.markerPositions);
    },
    update() {
      this.markerPositions = [];
      var step;
      for (step = 0; step < this.houses.data.length; step++) {
        this.markerPositions.push({
          title: this.houses.data[step].aptName,
          latlng: new kakao.maps.LatLng(
            this.houses.data[step].lat,
            this.houses.data[step].lng,
          ),
        });
      }
      this.displayMarkers(this.markerPositions);
    },
    moveMap(selected) {
      var moveLatLon = new kakao.maps.LatLng(selected.lat, selected.lng);
      this.map.panTo(moveLatLon);
      if (this.map.getLevel() == 14) {
        //지도 동기화 오류 상태
        this.map.setLevel(4);
      }
    },
    displayMarkers(positions) {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      const imgSrc = require("@/assets/map/apart.png");
      const imgSize = new kakao.maps.Size(42, 63);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
          title: position.title,
          image: markerImage,
        });
        this.markers.push(marker);
      });

      const bounds = positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds(),
      );

      this.map.setBounds(bounds);
      // console.log(this.map.getLevel());
      if (this.map.getLevel() > 10) {
        this.moveMap(this.houses.data[0]);
      }
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8c0ee03dd52a78f74e077c0615724e49`;

      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      //console.log("이미 로딩됨: ", window.kakao);
      this.initMap();
    }
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "checkMarkersLenght"]),
  },
  created() {
    // this.displayMarkers(this.markerPositions);

    eventBus.$on("apartUpdated", (data) => {
      console.log(data);
      this.update();
    });
    eventBus.$on("detailApart", (data) => {
      console.log(data);
      this.moveMap(this.house);
    });
  },
};
</script>

<style scoped>
.fluid-container {
  padding: 0px;
  width: 100vw;
  height: 100px;
}
</style>
