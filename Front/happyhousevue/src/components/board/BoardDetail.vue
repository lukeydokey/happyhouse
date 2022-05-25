<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="isMyArticle">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="delArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} </h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table-simple hover responsive>
          <b-thead>
            <b-tr>
              <b-th colspan="3">답변</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item2
              v-for="comment in comments"
              :key="comment.commentno"
              v-bind="comment"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <board-input-item2 />
  </b-container>
</template>

<script>
// import moment from "moment";
import BoardInputItem2 from "@/components/board/item/BoardInputItem2.vue";
import BoardListItem2 from "@/components/board/item/BoardListItem2.vue";
import { mapActions, mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";
export default {
  name: "BoardDetail",
  components: {
    BoardInputItem2,
    BoardListItem2,
  },
  computed: {
    ...mapState(boardStore, ["article", "message", "comments"]),
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    isMyArticle() {
      return this.article.userid === this.userInfo.id ? true : false;
    },
  },
  methods: {
    ...mapActions(boardStore, [
      "clearComments",
      "updateComments",
      "deleteArticle",
    ]),
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    delArticle() {
      if (confirm("정말로 삭제?")) {
        this.deleteArticle(this.article.articleno);
        alert("삭제 성공!!");
        this.$router.push({ name: "boardList" });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
