import {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  getComments,
  writeComment,
  deleteComment,
} from "@/api/board.js";

const boardStore = {
  namespaced: true,
  state: { comments: [], articles: [], article: null },
  getters: {
    commentList(state) {
      return state.comments;
    },
  },
  mutations: {
    SET_ARTICLE_LIST(state, articles) {
      articles.forEach((article) => {
        state.articles.push({
          articleno: article.articleno,
          subject: article.subject,
          userid: article.userid,
          regtime: article.regtime,
          hit: article.hit,
        });
      });
    },
    CLEAR_ARTICLE_LIST(state) {
      state.articles = [];
    },
    SET_ARTICLE(state, article) {
      state.article = article;
    },
    CLEAR_ARTICLE(state) {
      state.article = null;
    },
    SET_COMMENT_LIST(state, comments) {
      comments.forEach((comment) => {
        state.comments.push({
          articleno: comment.articleno,
          commentno: comment.commentno,
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
    listArticle({ commit }, param) {
      commit("CLEAR_ARTICLE_LIST");
      listArticle(
        param,
        ({ data }) => {
          commit("SET_ARTICLE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getArticle({ commit }, articleno) {
      getArticle(
        articleno,
        ({ data }) => {
          commit("SET_ARTICLE", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    writeArticle({ commit }, article) {
      writeArticle(
        article,
        ({ data }) => {
          if (data === "success") {
            commit("CLEAR_ARTICLE");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteArticle({ commit }, articleno) {
      deleteArticle(
        articleno,
        ({ data }) => {
          if (data === "success") commit("CLEAR_ARTICLE");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyArticle({ commit }, article) {
      modifyArticle(
        article,
        ({ data }) => {
          if (data === "success") {
            commit("CLEAR_ARTICLE");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    writeComment({ commit }, comment) {
      writeComment(
        comment,
        ({ data }) => {
          console.log(data);
          commit("CLEAR_COMMENTS");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getComments({ commit }, articleno) {
      getComments(
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
    deleteComment({ commit }, commentno) {
      deleteComment(
        commentno,
        ({ data }) => {
          if (data === "success") commit("CLEAR_COMMENTS");
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default boardStore;
