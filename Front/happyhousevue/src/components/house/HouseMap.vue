<template>
  <div class="fluid-container" style="width: 140px; z-index: 3" id="map">
    <b-card
      v-if="this.house"
      style="width: 15vw; z-index: 4"
      class="mb-2 float-left fontsans"
    >
      <house-area />
      <b-button variant="secondary" @click="setnull" class="float-right"
        >창 닫기</b-button
      >
    </b-card>
    <b-card
      title="오늘의 정보"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem; z-index: 4"
      class="mb-2 float-right fontsans"
    >
      <search-ranking />
      <search-ranking-by-gender />
      <div>추천 정보/뉴스/웹 크롤링 내용이 들어갈 자리</div>
      <strong>
        맵에 핀이 안 뜨면 상단바의 홈 버튼을 눌렀다가 아파트 버튼을 누르면
        됩니다.
      </strong>
      <b-button variant="primary" v-b-toggle.sidebar-right @click="clearHouses"
        >매물 검색</b-button
      >
    </b-card>
  </div>
</template>

<script>
/*global kakao*/
import { eventBus } from "@/main.js";
import { mapState, mapActions } from "vuex";
import HouseArea from "@/components/house/HouseArea.vue";
import SearchRanking from "@/components/house/ranking/SearchRanking.vue";
import SearchRankingByGender from "@/components/house/ranking/SearchRankingByGender.vue";

const houseStore = "houseStore";

export default {
  components: { HouseArea, SearchRanking, SearchRankingByGender },
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
      markerPositions: [],
      schoolPositions: [],
      parkPositions: [],
      markers: [],
      schoolmarkers: [],
      // 화면에 표시되어있는 marker들
    };
  },
  methods: {
    ...mapActions(houseStore, [
      "pushMarker",
      "setHouseNull",
      "getSchoolList",
      "getParkList",
      "getAreaList",
      "clearHouseList",
    ]),
    setnull() {
      this.setHouseNull();
    },
    clearHouses() {
      this.clearHouseList();
    },
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
      for (var step = 0; step < this.houses.data.length; step++) {
        this.markerPositions.push({
          title: this.houses.data[step].aptName,
          latlng: new kakao.maps.LatLng(
            this.houses.data[step].lat,
            this.houses.data[step].lng,
          ),
          content:
            this.houses.data[step].sidoName +
            " " +
            this.houses.data[step].gugunName +
            " " +
            this.houses.data[step].dongName +
            " " +
            this.houses.data[step].jibun,
        });
      }
      var imgSrc = require("@/assets/map/apart.png");
      var imgSize = new kakao.maps.Size(42, 63);
      this.displayMarkers(this.markerPositions, imgSrc, imgSize);
    },
    updateArea(areas) {
      console.log("업데이트진입");
      this.schoolPositions = [];
      for (var step = 0; step < areas.length; step++) {
        this.schoolPositions.push({
          title: areas[step].name,
          latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
          content: areas[step].address,
        });
      }
      var imgSrc = require("@/assets/map/school.png");
      var imgSize = new kakao.maps.Size(36, 54);
      this.displayAreas(this.schoolPositions, imgSrc, imgSize);
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
    displayMarkers(positions, imgSrc, imgSize) {
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
          title: position.title,
          image: markerImage,
        });

        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div id="content">' +
            '<div id="siteNotice">' +
            "</div>" +
            '<h5 id="firstHeading" class="firstHeading"> ' +
            position.title +
            " </h5>" +
            '<div id="bodyContent"><p>' +
            position.content +
            "</div>", // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow),
          // overlay.setMap(this.map);
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(this.map, marker, infowindow),
        );

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
    makeOverListener(map, marker, infowindow) {
      return function () {
        const imgSrc = require("@/assets/map/apart.png");
        const imgSize = new kakao.maps.Size(48, 70);
        const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
        marker.setImage(markerImage);
        infowindow.open(map, marker);
      };
    },
    makeOutListener(map, marker, infowindow) {
      return function () {
        const imgSrc = require("@/assets/map/apart.png");
        const imgSize = new kakao.maps.Size(42, 63);
        const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
        marker.setImage(markerImage);
        infowindow.close();
      };
    },
    displayAreas(positions, imgSrc, imgSize) {
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
      if (this.schoolmarkers.length > 0) {
        this.schoolmarkers.forEach((item) => {
          item.setMap(null);
        });
      }

      positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
          title: position.title,
          image: markerImage,
        });

        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div id="content">' +
            '<div id="siteNotice">' +
            "</div>" +
            '<h5 id="firstHeading" class="firstHeading"> ' +
            position.title +
            " </h5>" +
            '<div id="bodyContent"><p>' +
            position.content +
            "</div>", // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow),
          // overlay.setMap(this.map);
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(this.map, marker, infowindow),
        );

        this.schoolmarkers.push(marker);
      });
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
    ...mapState(houseStore, [
      "houses",
      "house",
      "schools",
      "parks",
      "checkMarkersLenght",
      "range",
    ]),
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
    eventBus.$on("rangeChanged", (data) => {
      console.log(data);
      if (this.house) {
        this.getAreaList({
          lat: this.house.lat,
          lng: this.house.lng,
          range: this.range,
        });
      }
    });
    eventBus.$on("areaUpdated", (data) => {
      console.log("이벤트버스");
      this.updateArea(data);
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
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
