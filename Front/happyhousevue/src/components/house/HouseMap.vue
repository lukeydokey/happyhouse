<template>
  <div class="fluid-container" style="width: 140px; z-index: 3" id="map">
    <b-card
      v-if="this.house"
      style="width: 15vw; z-index: 4"
      class="mb-2 float-left fontsans scroll"
    >
      <house-area />
      <b-button variant="secondary" @click="setnull" class="float-right"
        >창 닫기</b-button
      >
    </b-card>
    <b-card
      v-if="this.house && compare"
      style="width: 15vw; z-index: 4"
      class="mb-2 float-left fontsans scroll"
    >
      <compare-like />
    </b-card>

    <b-card
      style="width: 12vw; z-index: 4"
      class="mb-2 float-right fontsans scroll"
    >
      <div style="align-items: center">
        <h4 class="small-title float-left">추천 정보</h4>
        <b-button variant="primary" class="float-right" v-b-toggle.sidebar-right
          >매물 검색</b-button
        >
      </div>

      <search-ranking />
      <search-ranking-by-gender />

      <h4 class="small-title float-left">
        <b class="blue fontsans">{{ userInfo.name }}</b> 님의 관심 매물
      </h4>
      <br />
      <like-list />
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
import LikeList from "@/components/house/LikeList.vue";
import CompareLike from "@/components/house/CompareLike.vue";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: {
    HouseArea,
    SearchRanking,
    SearchRankingByGender,
    LikeList,
    CompareLike,
  },
  data() {
    return {
      centerPosition: null,
      drawingLine: null,
      drawingCircle: null,
      map: null,
      geocoder: null,
      markerPositions: [],
      schoolPositions: [],
      parkPositions: [],
      subwayPositions: [],
      conveniencePositions: [],
      marketPositions: [],
      cafePositions: [],
      cctvPositions: [],
      markers: [],
      schoolmarkers: [],
      parkmarkers: [],
      subwaymarkers: [],
      conveniencemarkers: [],
      marketmarkers: [],
      cafemarkers: [],
      cctvmarkers: [],
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
      "setCurAddress",
      "getCoordList",
      "clearHouseList",
      "likeAptGet",
      "detailHouse",
      "getHouseDealList",
      "setSelectedArea",
      "getComAreaList",
    ]),
    setnull() {
      this.setHouseNull();
    },
    initMap() {
      this.drawingLine = new kakao.maps.Polyline({
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: "#00a0e9", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });

      this.drawingCircle = new kakao.maps.Circle({
        strokeWeight: 1, // 선의 두께입니다
        strokeColor: "#00a0e9", // 선의 색깔입니다
        strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
        fillColor: "#00a0e9", // 채우기 색깔입니다
        fillOpacity: 0.2, // 채우기 불투명도입니다
      });
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.575869, 126.976859, 16),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();
      kakao.maps.event.addListener(this.map, "click", function (mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;
        eventBus.$emit("click", latlng);
        var message = "클릭한 위치의 위도는 " + latlng.getLat() + " 이고, ";
        message += "경도는 " + latlng.getLng() + " 입니다";

        console.log(message);
      });
      kakao.maps.event.addListener(this.map, "idle", function () {
        eventBus.$emit("bounds_changed", "bounds_changed");
      });
    },
    update() {
      this.markerPositions = [];
      if (this.houses)
        for (var step = 0; step < this.houses.data.length; step++) {
          this.markerPositions.push({
            title: this.houses.data[step].aptName,
            latlng: new kakao.maps.LatLng(
              this.houses.data[step].lat,
              this.houses.data[step].lng,
            ),
            house: this.houses.data[step],
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
      var imgSize = [42, 63];
      this.displayMarkers(this.markerPositions, imgSrc, imgSize);
    },
    updateArea(areas) {
      this.schoolPositions = [];
      this.parkPositions = [];
      this.subwayPositions = [];
      this.conveniencePositions = [];
      this.marketPositions = [];
      this.cafePositions = [];
      this.cctvPositions = [];
      for (var step = 0; step < areas.length; step++) {
        if (areas[step].type == "학교") {
          this.schoolPositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        } else if (areas[step].type == "공원") {
          this.parkPositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        } else if (areas[step].type == "지하철") {
          this.subwayPositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        } else if (areas[step].type == "편의점") {
          this.conveniencePositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        } else if (areas[step].type == "CCTV") {
          this.cctvPositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        } else if (areas[step].type == "대형마트") {
          this.marketPositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        } else if (areas[step].type == "카페") {
          this.cafePositions.push({
            title: areas[step].name,
            latlng: new kakao.maps.LatLng(areas[step].lat, areas[step].lng),
            content: areas[step].address,
          });
        }
      }
      var schoolIcon = require("@/assets/map/school.png");
      var parkIcon = require("@/assets/map/park.png");
      var subwayIcon = require("@/assets/map/subway.png");
      var convenienceIcon = require("@/assets/map/convenience.png");
      var cafeIcon = require("@/assets/map/cafe.png");
      var marketIcon = require("@/assets/map/market.png");
      var cctvIcon = require("@/assets/map/cctv.png");

      var imgSize = [40, 40];
      this.displayAreas(
        this.schoolPositions,
        this.schoolmarkers,
        schoolIcon,
        imgSize,
      );
      this.displayAreas(
        this.parkPositions,
        this.parkmarkers,
        parkIcon,
        imgSize,
      );
      this.displayAreas(
        this.subwayPositions,
        this.subwaymarkers,
        subwayIcon,
        imgSize,
      );
      this.displayAreas(
        this.conveniencePositions,
        this.conveniencemarkers,
        convenienceIcon,
        imgSize,
      );
      this.displayAreas(
        this.cafePositions,
        this.cafemarkers,
        cafeIcon,
        imgSize,
      );
      this.displayAreas(
        this.cctvPositions,
        this.cctvmarkers,
        cctvIcon,
        imgSize,
      );
      this.displayAreas(
        this.marketPositions,
        this.marketmarkers,
        marketIcon,
        imgSize,
      );
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
    boundsChanged() {
      var bounds = this.map.getBounds();
      var swLatlng = bounds.getSouthWest();
      var neLatlng = bounds.getNorthEast();

      if (this.coordSearch) {
        if (this.map.getLevel() < 5) {
          this.getCoordList({
            lat1: swLatlng.getLat(),
            lng1: swLatlng.getLng(),
            lat2: neLatlng.getLat(),
            lng2: neLatlng.getLng(),
          });
          this.searchAddrFromCoords(
            this.map.getCenter(),
            this.displayCenterInfo,
          );
        } else {
          this.setCurAddress(null);
          this.clearHouseList();
        }
      }
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
        var circleOptions = {
          center: this.centerPosition,
          radius: length,
        };
        this.drawingCircle.setOptions(circleOptions);
        this.drawingCircle.setMap(this.map);
        this.drawingLine.setMap(this.map);
      } else {
        this.drawingCircle.setMap(null);
        this.drawingLine.setMap(null);
      }
    },
    displayMarkers(positions, imgSrc, OriginSize) {
      const imgSize = new kakao.maps.Size(OriginSize[0], OriginSize[1]);
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
            "</div>",
        });

        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(
            this.map,
            marker,
            infowindow,
            imgSrc,
            OriginSize,
          ),
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(this.map, marker, infowindow, imgSrc, imgSize),
        );
        kakao.maps.event.addListener(
          marker,
          "click",
          this.makeClickListener(position.house),
        );

        this.markers.push(marker);
      });
      if (positions.length != 0 && !this.coordSearch) {
        const bounds = positions.reduce(
          (bounds, position) => bounds.extend(position.latlng),
          new kakao.maps.LatLngBounds(),
        );
        this.map.setBounds(bounds);
      }
      // console.log(this.map.getLevel());
      if (this.map.getLevel() > 10) {
        this.moveMap(this.houses.data[0]);
      }
    },
    makeClickListener(house) {
      return function () {
        eventBus.$emit("iconclick", house);
      };
    },
    makeOverListener(map, marker, infowindow, imgSrc, originSize) {
      return function () {
        const imgSize = new kakao.maps.Size(
          originSize[0] * 1.4,
          originSize[1] * 1.4,
        );
        const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
        marker.setImage(markerImage);
        infowindow.open(map, marker);
      };
    },
    makeOutListener(map, marker, infowindow, imgSrc, imgSize) {
      return function () {
        const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
        marker.setImage(markerImage);
        infowindow.close();
      };
    },
    displayAreas(positions, markers, imgSrc, OriginSize) {
      const imgSize = new kakao.maps.Size(OriginSize[0], OriginSize[1]);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
      if (markers.length > 0) {
        markers.forEach((item) => {
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
            "</div>",
        });
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(
            this.map,
            marker,
            infowindow,
            imgSrc,
            OriginSize,
          ),
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(this.map, marker, infowindow, imgSrc, imgSize),
        );

        markers.push(marker);
      });
    },
    searchAddrFromCoords(coords, callback) {
      this.geocoder.coord2RegionCode(
        coords.getLng(),
        coords.getLat(),
        callback,
      );
    },
    displayCenterInfo(result, status) {
      if (status === kakao.maps.services.Status.OK) {
        this.setCurAddress(result[1].address_name);
      }
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8c0ee03dd52a78f74e077c0615724e49&libraries=services`;

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
      "coordSearch",
      "like",
      "compare",
      "selectedCompare",
    ]),
    ...mapState(memberStore, ["userInfo"]),
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
      if (this.house) {
        console.log(this.house);
        this.getAreaList({
          lat: this.house.lat,
          lng: this.house.lng,
          range: this.range,
        });
      }
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
        console.log(this.house);
        this.getAreaList({
          lat: this.house.lat,
          lng: this.house.lng,
          range: this.range,
        });
      }
      if (this.selectedCompare) {
        console.log("비교");
        this.getComAreaList({
          lat: this.selectedCompare.lat,
          lng: this.selectedCompare.lng,
          range: this.range,
        });
      }
    });
    eventBus.$on("areaUpdated", (data) => {
      this.updateArea(data);
    });
    eventBus.$on("likeChange", (data) => {
      console.log(data);
      this.likeAptGet(data.id);
    });
    eventBus.$on("bounds_changed", (data) => {
      console.log(data);
      this.boundsChanged();
    });
    eventBus.$on("iconclick", (house) => {
      if (this.getSelected && this.getSelected.aptCode === house.aptCode) {
        this.clearHouse();
      } else {
        this.detailHouse(house);
        this.getHouseDealList(this.house.aptCode);
      }

      this.setSelectedArea(null);
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
.scroll {
  max-height: 100vh;
  overflow-y: auto;
}
.blue {
  color: #2196f3;
}
</style>
