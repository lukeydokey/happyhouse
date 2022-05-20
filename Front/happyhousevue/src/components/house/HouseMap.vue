<template>
  <div class="fluid-container" style="width: 140px; z-index: 3" id="map">
    <b-card
      title="오늘의 정보"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem; z-index: 4"
      class="mb-2 float-right"
    >
      <b-text>추천 정보/뉴스가 들어갈 자리</b-text>
      <b-button variant="primary" v-b-toggle.sidebar-right>매물 검색</b-button>
    </b-card>
  </div>
</template>

<script>
import { eventBus } from "@/main.js";
import { mapState } from "vuex";

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
      // 화면에 표시되어있는 marker들
    };
  },
  methods: {
    initMap() {
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
        console.log(this.houses.data[step]);
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
    moveMap() {
      console.log("맵이동");
      var moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);

      // 지도 중심을 이동 시킵니다
      this.map.panTo(moveLatLon);
    },
    displayMarkers(positions) {
      // 1. 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      // 2. 마커 이미지 커스터마이징 하기
      // javascript 영역에서 assets의 정보 가져오기
      const imgSrc = require("@/assets/map/marker.png");
      const imgSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 3. 마커 표시하기
      positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng, // 마커의 위치
          title: position.title, // 마우스 오버 시 표시할 제목
          image: markerImage, // 마커의 이미지
        });
        this.markers.push(marker);
      });

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds(),
      );

      this.map.setBounds(bounds);
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
  computed: {
    ...mapState(houseStore, ["houses", "markers", "house"]),
  },
  created() {
    // this.displayMarkers(this.markerPositions);

    eventBus.$on("apartUpdated", (data) => {
      console.log(data);
      this.update();
    });
    eventBus.$on("detailApart", (data) => {
      console.log(data);
      this.moveMap();
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
