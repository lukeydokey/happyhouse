<template>
  <div id="app">
    <navi-bar />
    <transition name="fade" mode="out-in">
      <router-view />
    </transition>
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
@font-face {
  font-family: "JUA";
  src: url("@/assets/font/BMJUA_ttf.ttf");
}
.fontjua {
  font-family: "JUA";
}
@font-face {
  font-family: "SANS";
  src: url("@/assets/font/SpoqaHanSansNeo-Medium.ttf");
}
.fontsans {
  font-family: "SANS";
}
body {
  margin: 0;
}

.fade-enter {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}

.fade-leave-to {
  opacity: 0;
}
</style>
