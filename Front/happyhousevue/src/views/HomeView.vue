<template>
  <b-container class="bv-example-row mt-3 text-center fontsans">
    <b-container class="mainContents">
      <b-row></b-row>
      <b-row class="mt-5">
        <b-col></b-col>
        <b-col cols="10">
          <b-jumbotron
            bg-variant="transparent"
            text-variant="light"
            class="mt-5"
          >
            <template #header><b>Happy House</b></template>

            <template #lead> <b>행복한 우리집</b> </template>
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
      <b-button
        type="button"
        variant="outline-light"
        v-if="isLogin"
        size="lg"
        class="mt-4"
        @click="moveApt"
      >
        Start
      </b-button>
      <b-button
        type="button"
        variant="outline-light"
        v-if="!isLogin"
        size="lg"
        class="mt-4"
        @click="moveLogin"
      >
        Join
      </b-button>
    </b-container>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  computed: {
    ...mapState(memberStore, ["isLogin"]),
  },
  methods: {
    moveLogin() {
      this.$router.push({ name: "signIn" });
    },
    moveApt() {
      this.$router.push({ name: "house" });
    },
  },
};
</script>

<style scoped>
.mt-4 {
  width: 200px;
  height: 60px;
}
</style>
