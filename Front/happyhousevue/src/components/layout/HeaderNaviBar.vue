<template>
  <div>
    <b-navbar toggleable="lg" variant="transparent">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="100px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="link"
              ><b-icon icon="house" font-scale="1.5"></b-icon> 홈</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#"
            ><router-link :to="{ name: 'map' }" class="link"
              ><b-icon icon="question-square-fill" font-scale="2"></b-icon>지도
            </router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link"
              ><b-icon icon="question-square-fill" font-scale="2"></b-icon> Q &
              A</router-link
            ></b-nav-item
          >
          <b-nav-item href="#" class="mr-3"
            ><router-link :to="{ name: 'house' }" class="link"
              ><b-icon icon="house-fill" font-scale="2"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#" v-if="!loginInfo"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-if="!loginInfo"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-if="loginInfo" @click="logout"
              ><router-link :to="{ name: 'home' }" class="link"
                ><b-icon icon="key"></b-icon> 로그아웃</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(["loginInfo"]),
  },
  methods: {
    ...mapActions(["delLoginInfo"]),
    logout() {
      this.delLoginInfo();
    },
  },
};
</script>

<style></style>
