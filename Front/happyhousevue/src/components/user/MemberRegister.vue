<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
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
              <p v-if="typedId" v-bind:class="idChecked">{{ ckidMsg }}</p>
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
            <b-form-group label="이메일:" label-for="useraddr">
              <b-form-input
                id="useraddr"
                ref="address"
                v-model="user.address"
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
              variant="primary"
              class="m-1"
              @click="checkValue"
              >회원가입</b-button
            >
            <b-button
              type="button"
              variant="success"
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
import http from "@/api/http";
export default {
  name: "MemberRegister",
  data() {
    return {
      isRegisterError: false,
      ckidMsg: "아이디는 5~12자리",
      isUsable: false,
      user: {
        id: "",
        password: "",
        name: "",
        address: "",
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
  methods: {
    ckId() {
      if (this.user.id.length >= 5 && this.user.id.length <= 12) {
        http
          .post("/user/idCheck", {
            id: this.user.id,
          })
          .then(({ data }) => {
            this.isUsable = false;
            this.ckidMsg = "사용불가능";
            if (data === "success") {
              this.ckidMsg = "사용가능";
              this.isUsable = true;
            }
          });
      } else {
        this.ckidMsg = "아이디는 5~12자리";
      }
    },
    checkValue() {
      // 사용자 입력값 체크하기
      this.isRegisterError = false;
      !this.user.id && ((this.isRegisterError = true), this.$refs.id.focus());
      !this.isRegisterError &&
        !this.user.password &&
        ((this.isRegisterError = true), this.$refs.password.focus());
      !this.isRegisterError &&
        !this.user.name &&
        ((this.isRegisterError = true), this.$refs.name.focus());
      !this.isRegisterError &&
        !this.user.address &&
        ((this.isRegisterError = true), this.$refs.address.focus());
      !this.isRegisterError &&
        !this.user.phonenumber &&
        ((this.isRegisterError = true), this.$refs.phonenumber.focus());
      !this.isRegisterError &&
        !this.user.gender &&
        ((this.isRegisterError = true), this.$refs.gender.focus());
      // 만약, 내용이 다 입력되어 있다면 register
      if (!this.isRegisterError) this.confirm();
    },
    confirm() {
      this.user.phonenumber = this.$refs.phonenumber.value;
      http
        .post("/user/register", {
          id: this.user.id,
          password: this.user.password,
          name: this.user.name,
          address: this.user.address,
          phonenumber: this.user.phonenumber,
          gender: this.user.gender,
        })
        .then(({ data }) => {
          let msg = "계정 생성 실패";
          if (data === "success") {
            msg = "계정 생성 성공";
          }
          alert(msg);
          this.$router.push({ name: "home" });
        });
    },
    movePage() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped></style>
