import {
  // listArticle,
  // writeArticle,
  // getArticle,
  // modifyArticle,
  // deleteArticle,
  addComment,
} from "@/api/board.js";

const boardStore = {
  namespaced: true,
  state: { comments: [] },
  getters: {
    commentList(state) {
      return state.comments;
    },
  },
  mutations: {
    SET_COMMENT_LIST(state, comments) {
      comments.forEach((comment) => {
        state.comments.push({
          userid: comment.userid,
          regtime: comment.regtime,
          content: comment.content,
        });
      });
    },
    CLEAR_COMMENTS(state) {
      state.comments = [];
    },
  },
  actions: {
    updateComments({ commit }, articleno) {
      addComment(
        articleno,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_COMMENT_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    clearComments({ commit }) {
      commit("CLEAR_COMMENTS");
    },
  },
};

export default boardStore;
