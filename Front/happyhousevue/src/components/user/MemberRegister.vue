<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="primary" show><h3>회원가입</h3></b-alert>
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
              <b-form-input
                id="userid"
                ref="id"
                v-model="user.id"
                required
                placeholder="아이디 입력"
              >
              </b-form-input>
              <p v-if="typedId" v-bind:class="idChecked">{{ ckIdMsg }}</p>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                ref="password"
                id="userpwd"
                v-model="user.password"
                required
                placeholder="비밀번호 입력"
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
                v-model="user.gender"
                :options="options"
                size="sm"
              ></b-form-select>
            </b-form-group>
            <b-button
              type="button"
              variant="outline-primary"
              class="m-1"
              style="float: right"
              @click="checkValue"
              >회원가입</b-button
            >
            <b-button
              type="button"
              variant="outline-danger"
              class="m-1"
              style="float: right"
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
  name: "MemberRegister",
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
  computed: {
    ...mapState(memberStore, ["isUsable", "isRegisterError", "ckIdMsg"]),
    idChecked() {
      return {
        "text-danger":
          this.user.id.length < 5 ||
          this.user.id.length > 12 ||
          (this.user.id.length >= 5 &&
            this.user.id.length <= 12 &&
            !this.isUsable),
        "text-primary":
          this.user.id.length >= 5 &&
          this.user.id.length <= 12 &&
          this.isUsable,
      };
    },
    typedId() {
      let result = this.user.id.length > 0 ? true : false;
      this.ckId();
      return result;
    },
  },
  filters: {
    phoneNum: function (value) {
      return value
        .toString()
        .replace(/[^0-9]/, "")
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    },
  },
  destroyed() {
    this.setCkidMsgDefault();
    this.setRegisterErrorFalse();
  },
  methods: {
    ...mapActions(memberStore, [
      "userRegister",
      "idCheck",
      "setRegisterErrorFalse",
      "setRegisterErrorTrue",
      "setCkidMsgDefault",
    ]),
    ckId() {
      if (this.user.id.length >= 5 && this.user.id.length <= 12) {
        this.idCheck(this.user.id);
      } else {
        this.setCkidMsgDefault();
      }
    },
    checkValue() {
      // 사용자 입력값 체크하기
      this.setRegisterErrorFalse();
      !this.user.id && (this.setRegisterErrorTrue(), this.$refs.id.focus());
      !this.isRegisterError &&
        !this.isUsable &&
        (this.setRegisterErrorTrue(), this.$refs.id.focus());
      !this.isRegisterError &&
        !this.user.password &&
        (this.setRegisterErrorTrue(), this.$refs.password.focus());
      !this.isRegisterError &&
        !this.user.name &&
        (this.setRegisterErrorTrue(), this.$refs.name.focus());
      !this.isRegisterError &&
        !this.user.email &&
        (this.setRegisterErrorTrue(), this.$refs.email.focus());
      !this.isRegisterError &&
        !this.user.phonenumber &&
        (this.setRegisterErrorTrue(), this.$refs.phonenumber.focus());
      !this.isRegisterError &&
        !this.user.gender &&
        (this.setRegisterErrorTrue(), this.$refs.gender.focus());
      // 만약, 내용이 다 입력되어 있다면 register
      if (!this.isRegisterError) this.confirm();
    },
    async confirm() {
      this.user.phonenumber = this.$refs.phonenumber.value;
      await this.userRegister(this.user);
      this.setCkidMsgDefault();
      this.$router.push({ name: "signIn" });
    },
    movePage() {
      this.setCkidMsgDefault();
      this.setRegisterErrorFalse();
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped>
.m-1 {
  width: 100px;
}
</style>
