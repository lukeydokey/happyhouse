import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";
import { checkId } from "@/api/member.js";
import { register } from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isRegisterError: false,
    isUsable: false,
    ckIdMsg: "아이디는 5~12자리",
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_CKID_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_CKID_MSG: (state, result) => {
      if (result) {
        state.ckIdMsg = "사용 가능";
      } else {
        state.ckIdMsg = "사용 불가";
      }
    },
    SET_CKID_MSG_DEFAULT: (state) => {
      state.ckIdMsg = "아이디는 5~12자리";
    },
    SET_IS_USABLE: (state, isUsable) => {
      state.isUsable = isUsable;
    },
    SET_IS_REGISTER_ERROR: (state, isRegisterError) => {
      state.isRegisterError = isRegisterError;
    },
    CLEAR_USER_INFO: (state) => {
      state.userInfo = null;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    async userRegister({ commit }, user) {
      await register(
        user,
        (response) => {
          if (response.data === "success") {
            commit("SET_IS_REGISTER_ERROR", false);
          } else {
            commit("SET_IS_REGISTER_ERROR", true);
          }
        },
        () => {},
      );
    },
    setRegisterErrorFalse: ({ commit }) => {
      commit("SET_IS_REGISTER_ERROR", false);
    },
    setRegisterErrorTrue: ({ commit }) => {
      commit("SET_IS_REGISTER_ERROR", true);
    },
    setIsLoginErrorFalse: ({ commit }) => {
      commit("SET_IS_LOGIN_ERROR", false);
    },
    setIsLoginErrorTrue: ({ commit }) => {
      commit("SET_IS_LOGIN_ERROR", true);
    },
    setCkidMsgDefault: ({ commit }) => {
      commit("SET_CKID_MSG_DEFAULT");
    },
    getUserInfo: ({ commit }, token) => {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    delLoginInfo: ({ commit }) => {
      sessionStorage.removeItem("access-token");
      commit("CLEAR_USER_INFO");
      commit("SET_IS_LOGIN", false);
    },
    idCheck: ({ commit }, userid) => {
      checkId(
        userid,
        (response) => {
          if (response.data === "success") {
            commit("SET_CKID_MSG", true);
            commit("SET_IS_USABLE", true);
          } else {
            commit("SET_CKID_MSG", false);
            commit("SET_IS_USABLE", false);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default memberStore;
