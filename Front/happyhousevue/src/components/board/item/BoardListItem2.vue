<template>
  <b-tr>
    <b-td class="col-3">{{ userid }}</b-td>
    <b-td class="text-left">{{ content }}</b-td>
    <b-td class="col-2">{{ regtime | dateFormat }}</b-td>
    <b-td class="col-1"
      ><b-button
        type="reset"
        variant="danger"
        v-if="isMyComment"
        @click="delComment"
      >
        X
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
import { mapActions, mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardListItem2",
  props: {
    articleno: Number,
    commentno: Number,
    userid: String,
    content: String,
    regtime: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    isMyComment() {
      return this.userInfo.id === this.userid ? true : false;
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    ...mapActions(boardStore, ["deleteComment", "getComments"]),
    delComment() {
      this.deleteComment(this.commentno);
      alert("댓글 삭제 완료");
      this.getComments(this.articleno);
    },
  },
};
</script>

<style></style>
