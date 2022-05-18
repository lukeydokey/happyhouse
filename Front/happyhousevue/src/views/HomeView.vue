<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-container class="mainContents">
      <b-row></b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-jumbotron bg-variant="transparent" text-variant="light">
            <template #header>Happy House</template>

            <template #lead> 행복한 우리집 </template>

            <hr class="my-4" />
            <p>아파트 정보 검색</p>
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
      <b-button type="button" variant="primary" @click="movePage"
        >Start</b-button
      >
    </b-container>
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  data() {
    return {
      mainProps: {
        center: true,
        fluidGrow: true,
        blank: true,
        blankColor: "#bbb",
      },
    };
  },
  methods: {
    movePage() {
      this.$router.push({ name: "signIn" });
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
