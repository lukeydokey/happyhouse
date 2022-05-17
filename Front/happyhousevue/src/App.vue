<template>
  <div id="app">
    <header-navi-bar />
    <router-view />
  </div>
</template>

<script>
import HeaderNaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    HeaderNaviBar,
  },
};
</script>

<style>
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
</style>
