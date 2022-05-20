<template>
  <div class="seek-bar">
    <div id="circle" class="circle">
      <div>
        <em></em>
        <em></em>
      </div>
      <svg xmlns="http://www.w3.org/2000/svg" version="1.1">
        <defs>
          <filter id="svg_goo" filterUnits="objectBoundingBox">
            <feGaussianBlur
              in="SourceGraphic"
              stdDeviation="10"
              result="blur"
            />
            <feColorMatrix
              in="blur"
              mode="matrix"
              values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 30 -11"
              result="svg_goo"
            />
            <feBlend in="SourceGraphic" in2="svg_goo" />
          </filter>
        </defs>
      </svg>
      <span>0</span>
    </div>
  </div>
</template>

<script>
import { eventBus } from "@/main";
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  data() {
    return {
      currentValue: this.value,
    };
  },
  computed: {
    ...mapState(houseStore, ["range"]),
  },
  methods: {
    ...mapActions(houseStore, ["setRange"]),
    dragElement(elmnt, initVal) {
      let clientX_gab = 0,
        clientX = 0;
      let parentElmnt = elmnt.parentNode;
      elmnt.querySelector("span").innerText = Math.round(initVal);

      elmnt.style.left = (initVal / 100) * parentElmnt.clientWidth + "px";
      elmnt.onmousedown = dragMouseDown;
      elmnt.addEventListener("touchstart", dragMouseDown);
      function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();
        elmnt.classList.add("s_on");
        if (e.changedTouches) {
          e.clientX = e.changedTouches[0].clientX;
        }
        clientX = e.clientX;
        document.onmouseup = closeDragElement;
        document.addEventListener("touchend", closeDragElement);
        document.onmousemove = elementDrag;
        document.addEventListener("touchmove", elementDrag);
      }

      function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        if (e.changedTouches) {
          e.clientX = e.changedTouches[0].clientX;
        }
        clientX_gab = e.clientX - clientX;
        clientX = e.clientX;
        let leftVal = 0;
        let parentElmnt = elmnt.parentNode;
        if (elmnt.offsetLeft + clientX_gab < 0) {
          leftVal = 0;
        } else if (elmnt.offsetLeft + clientX_gab > parentElmnt.clientWidth) {
          leftVal = parentElmnt.clientWidth;
        } else {
          leftVal = elmnt.offsetLeft + clientX_gab;
        }
        elmnt.querySelector("span").innerText =
          Math.round((leftVal / parentElmnt.clientWidth) * 1000) + "M";
        // this.set(Math.round((leftVal / parentElmnt.clientWidth) * 1000));
        eventBus.$emit(
          "change",
          Math.round((leftVal / parentElmnt.clientWidth) * 1000),
        );
        elmnt.style.left = leftVal + "px";
      }

      function closeDragElement() {
        elmnt.classList.remove("s_on");
        document.onmouseup = null;
        document.removeEventListener("touchend", closeDragElement);
        document.onmousemove = null;
        document.removeEventListener("touchmove", elementDrag);
      }
    },
    set(input) {
      this.setRange(input);
    },
  },
  mounted() {
    this.dragElement(document.getElementById("circle"), 0);
  },
  created() {
    // this.displayMarkers(this.markerPositions);

    eventBus.$on("change", (data) => {
      this.set(data);
    });
  },
};
</script>

<style scoped>
HTML CSS JSResult Skip Results Iframe EDIT ON .container {
  overflow: hidden;
}
.seek-bar {
  margin: 50px auto 20px;
  position: relative;
  width: calc(87%);
  height: 10px;
  background: rgb(210, 210, 210);
  border-radius: 5px;
}
.seek-bar > .circle {
  cursor: grab;
  position: absolute;
  left: 0;
  top: 50%;
  width: 30px;
  height: 30px;
  transform: translate(-50%, -50%);
  touch-action: none;
}
.seek-bar > .circle > svg {
  pointer-events: none;
  width: 0;
  height: 0;
}
.seek-bar > .circle > div {
  height: 100%;
  -webkit-filter: url("#svg_goo");
  filter: url("#svg_goo");
}
.seek-bar > .circle > div > em {
  pointer-events: none;
  position: absolute;
  display: block;
  width: 40px;
  height: 40px;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background: #2196f3;
  border-radius: 100%;
  transition: all 0.5s;
}
.seek-bar > .circle > span {
  pointer-events: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  filter: none;
  color: #fff;
  font-size: 13px;
  transition: all 0.5s;
  white-space: nowrap;
  text-align: center;
  font-family: "JUA";
}

.seek-bar > .circle.s_on > span {
  margin-top: -50px;
  font-size: 20px;
}
.seek-bar > .circle.s_on > div > em:nth-child(2) {
  margin-top: -50px;
  background: #82bfee;
  width: 50px;
  height: 50px;
}
</style>
