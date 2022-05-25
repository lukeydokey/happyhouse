<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="primary" show><h3>회원정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isRegisterError"
              >모든 정보를 기입 하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input id="userid" ref="id" disabled :value="user.id">
              </b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                ref="password"
                id="userpwd"
                v-model="user.password"
                placeholder="비밀번호 입력"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                ref="name"
                v-model="user.name"
                required
                placeholder="이름 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="useremail">
              <b-form-input
                id="useremail"
                ref="email"
                v-model="user.email"
                required
                placeholder="이메일 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="usertel">
              <b-form-input
                id="usertel"
                ref="phonenumber"
                @input="(value) => (user.phonenumber = value)"
                required
                placeholder="전화번호 입력"
                :value="user.phonenumber | phoneNum"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="성별:" label-for="usergender">
              <b-form-select
                id="usergender"
                ref="gender"
                disabled
                :options="options"
                v-model="user.gender"
                size="sm"
              ></b-form-select>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="checkValue"
              >수정완료</b-button
            >
            <b-button
              type="button"
              variant="danger"
              class="m-1"
              @click="movePage"
              >취소</b-button
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
  name: "MemberModify",
  data() {
    return {
      user: {
        id: "",
        password: "",
        name: "",
        email: "",
        phonenumber: "",
        gender: "",
      },
      options: [
        { value: "남성", text: "남성" },
        { value: "여성", text: "여성" },
      ],
    };
  },
  created() {
    this.user = { ...this.userInfo };
    this.user.phonenumber = this.userInfo.phonenumber.replace("-", "");
  },
  computed: {
    ...mapState(memberStore, ["isRegisterError", "userInfo"]),
  },
  filters: {
    phoneNum: function (value) {
      return value
        .toString()
        .replace(/[^0-9]/, "")
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    },
  },
  methods: {
    ...mapActions(memberStore, [
      "setRegisterErrorFalse",
      "setRegisterErrorTrue",
      "userUpdate",
    ]),
    checkValue() {
      // 사용자 입력값 체크하기
      this.setRegisterErrorFalse();
      !this.$refs.password &&
        (this.setRegisterErrorTrue(), this.$refs.password.focus());
      !this.isRegisterError &&
        !this.$refs.name &&
        (this.setRegisterErrorTrue(), this.$refs.name.focus());
      !this.isRegisterError &&
        !this.$refs.email &&
        (this.setRegisterErrorTrue(), this.$refs.email.focus());
      !this.isRegisterError &&
        !this.$refs.phonenumber &&
        (this.setRegisterErrorTrue(), this.$refs.phonenumber.focus());
      // 만약, 내용이 다 입력되어 있다면 modify
      if (!this.isRegisterError) this.confirm();
    },
    async confirm() {
      this.userUpdate(this.userInfo);
      this.$router.push({ name: "myPage" });
    },
    movePage() {
      this.setRegisterErrorFalse();
      this.$router.push({ name: "myPage" });
    },
  },
};
</script>

<style scoped>
.m-1 {
  width: 100px;
}
</style>
