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
  src: url("@/assets/font/SpoqaHanSansNeo-Medium.ttf");
}
.fontjua {
  font-family: "JUA";
}
body {
  margin: 0;
}
a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #3aa4ca;
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
