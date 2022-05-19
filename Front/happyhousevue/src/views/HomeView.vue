<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-container class="mainContents">
      <b-row></b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-jumbotron bg-variant="transparent" text-variant="light">
            <template #header><b>Happy House</b></template>

            <template #lead> <b>행복한 우리집</b> </template>

            <hr class="my-4" />
            <p><b>아파트 정보 검색</b></p>
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
      <b-button type="button" variant="primary" v-if="isLogin" @click="moveApt">
        Start
      </b-button>
      <b-button
        type="button"
        variant="primary"
        v-if="!isLogin"
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
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
