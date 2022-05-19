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
                v-model="user.userid"
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
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="checkValue"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
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
import http from "@/api/http";
import { mapActions } from "vuex";
export default {
  name: "MemberLogin",
  data() {
    return {
      isLoginError: false,
      user: {
        userid: "",
        userpwd: "",
      },
    };
  },
  methods: {
    ...mapActions(["setLoginInfo"]),
    checkValue() {
      // 사용자 입력값 체크하기
      // isbn, 제목, 저자, 가격, 설명이 없을 경우 각 항목에 맞는 메세지를 출력
      this.isLoginError = false;
      !this.user.userid && ((this.isLoginError = true), this.$refs.id.focus());
      !this.isLoginError &&
        !this.user.userpwd &&
        ((this.isLoginError = true), this.$refs.password.focus());

      if (!this.isLoginError) this.confirm();
    },
    confirm() {
      http
        .post("/user", {
          id: this.user.userid,
          password: this.user.userpwd,
        })
        .then(({ data }) => {
          let msg = "아이디 혹은 비밀번호가 틀렸습니다.";
          if (data === "success") {
            msg = "로그인 성공";
            this.setLoginInfo(this.user.userid);
          }
          alert(msg);
          this.$router.push("/");
        });
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style></style>
