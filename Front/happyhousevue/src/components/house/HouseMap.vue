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
      centerPosition: null,
      drawingLine: new kakao.maps.Polyline({
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: "#00a0e9", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      }),

      drawingCircle: new kakao.maps.Circle({
        strokeWeight: 1, // 선의 두께입니다
        strokeColor: "#00a0e9", // 선의 색깔입니다
        strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
        fillColor: "#00a0e9", // 채우기 색깔입니다
        fillOpacity: 0.2, // 채우기 불투명도입니다
      }),
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
      kakao.maps.event.addListener(this.map, "click", function (mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;
        eventBus.$emit("click", latlng);
        var message = "클릭한 위치의 위도는 " + latlng.getLat() + " 이고, ";
        message += "경도는 " + latlng.getLng() + " 입니다";

        console.log(message);
      });
      // kakao.maps.event.addListener(this.map, "dragend", function () {
      //   // 지도 중심좌표를 얻어옵니다
      //   eventBus.$emit("dragMove", "dragMove");
      // });
      // kakao.maps.event.addListener(this.map, "zoom_changed", function () {
      //   // 지도의 현재 레벨을 얻어옵니다
      //   eventBus.$emit("zoomChange", "zoomChange");
      // });
      // kakao.maps.event.addListener(this.map, "center_changed", function () {
      //   // 지도의  레벨을 얻어옵니다
      //   eventBus.$emit("centerChange", "centerChange");
      // });
      kakao.maps.event.addListener(this.map, "bounds_changed", function () {
        eventBus.$emit("bounds_changed", "bounds_changed");
      });
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
    clickMove(moveLatLon) {
      this.map.panTo(moveLatLon);
    },
    // dragMove() {
    //   if (this.map) var latlng = this.map.getCenter();

    //   var message = "변경된 지도 중심좌표는 " + latlng.getLat() + " 이고, ";
    //   message += "경도는 " + latlng.getLng() + " 입니다";

    //   console.log(message);
    // },
    // zoomChange() {
    //   var level = this.map.getLevel();

    //   var message = "현재 지도 레벨은 " + level + " 입니다";
    //   console.log(message);
    // },
    // centerChange() {
    //   var level = this.map.getLevel();

    //   // 지도의 중심좌표를 얻어옵니다
    //   var latlng = this.map.getCenter();

    //   var message = "지도 레벨은 " + level + " 이고";
    //   message +=
    //     "중심 좌표는 위도 " +
    //     latlng.getLat() +
    //     ", 경도 " +
    //     latlng.getLng() +
    //     "입니다";

    //   console.log(message);
    // },
    boundsChanged() {
      // 지도 영역정보를 얻어옵니다
      var bounds = this.map.getBounds();

      // 영역정보의 남서쪽 정보를 얻어옵니다
      var swLatlng = bounds.getSouthWest();

      // 영역정보의 북동쪽 정보를 얻어옵니다
      var neLatlng = bounds.getNorthEast();

      var message =
        "<p>영역좌표는 남서쪽 위도, 경도는  " +
        swLatlng.toString() +
        "이고 <br>";
      message += "북동쪽 위도, 경도는  " + neLatlng.toString() + "입니다 </p>";

      console.log(message);
    },
    drawCircle(data) {
      this.centerPosition = this.map.getCenter();
      var length;
      if (this.house) {
        length = this.range;
        this.centerPosition = new kakao.maps.LatLng(
          this.house.lat,
          this.house.lng,
        );
      } else {
        length = data;
      }
      if (!(this.drawingCircle && this.drawingLine)) return;
      if (length > 0) {
        // 그려지고 있는 원의 중심좌표와 반지름입니다
        var circleOptions = {
          center: this.centerPosition,
          radius: length,
        };

        // 그려지고 있는 원의 옵션을 설정합니다
        this.drawingCircle.setOptions(circleOptions);

        // 그려지고 있는 원을 지도에 표시합니다
        this.drawingCircle.setMap(this.map);

        // 그려지고 있는 선을 지도에 표시합니다
        this.drawingLine.setMap(this.map);
      } else {
        this.drawingCircle.setMap(null);
        this.drawingLine.setMap(null);
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
    ...mapState(houseStore, ["houses", "house", "checkMarkersLenght", "range"]),
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
      this.drawCircle(data);
    });
    eventBus.$on("click", (data) => {
      this.clickMove(data);
    });
    eventBus.$on("rangeChange", (data) => {
      this.drawCircle(data);
    });
    // eventBus.$on("dragMove", (data) => {
    //   console.log(data);
    //   this.dragMove();
    // });
    // eventBus.$on("zoomChange", (data) => {
    //   console.log(data);
    //   this.zoomChange();
    // });
    // eventBus.$on("centerChange", (data) => {
    //   console.log(data);
    //   this.centerChange();
    // });
    eventBus.$on("bounds_changed", (data) => {
      console.log(data);
      this.boundsChanged();
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
