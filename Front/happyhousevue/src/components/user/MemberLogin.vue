<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="primary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.id"
                ref="id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                ref="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              squared
              type="button"
              variant="outline-secondary"
              class="m-1"
              style="float: right"
              @click="checkValue"
              >로그인</b-button
            >
            <b-button
              squared
              type="button"
              variant="outline-secondary"
              class="m-1"
              style="float: right"
              @click="movePage"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        id: "",
        password: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  destroyed() {
    this.setIsLoginErrorFalse();
  },
  methods: {
    ...mapActions(memberStore, [
      "userConfirm",
      "getUserInfo",
      "setIsLoginErrorFalse",
      "setIsLoginErrorTrue",
    ]),
    checkValue() {
      // 사용자 입력값 체크하기
      this.setIsLoginErrorFalse();
      !this.user.id && (this.setIsLoginErrorTrue(), this.$refs.id.focus());
      !this.isLoginError &&
        !this.user.password &&
        (this.setIsLoginErrorTrue(), this.$refs.password.focus());

      if (!this.isLoginError) this.confirm();
    },
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style scoped>
.m-1 {
  width: 100px;
}
</style>
