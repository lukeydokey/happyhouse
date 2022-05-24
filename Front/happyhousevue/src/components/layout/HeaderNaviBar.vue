<template>
  <div>
    <b-navbar toggleable="lg" :variant="navBarColor">
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
              ><b-icon icon="house" font-scale="2"></b-icon> 홈</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
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
          <b-nav-item-dropdown v-if="!isLogin" right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
          <b-nav-item-dropdown v-else right>
            <template #button-content>
              <b-icon icon="people" font-scale="2" variant="active"></b-icon>
            </template>
            <b-dropdown-item class="align-self-center"
              ><router-link
                :to="{ name: 'myPage' }"
                class="link align-self-center"
                ><b-avatar
                  variant="primary"
                  v-text="userInfo ? userInfo.id.charAt(0).toUpperCase() : ''"
                ></b-avatar
                >{{ userInfo.name }}({{ userInfo.id }})</router-link
              >
            </b-dropdown-item>
            <b-dropdown-item href="#" @click="logout"
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
const memberStore = "memberStore";
const houseStore = "houseStore";
export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(houseStore, ["isSearching"]),
    navBarColor() {
      return this.isSearching ? "light" : "transparent";
    },
  },
  methods: {
    ...mapActions(memberStore, ["delLoginInfo"]),
    logout() {
      this.delLoginInfo();
      if (this.$router.currentRoute.name != "home")
        this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
