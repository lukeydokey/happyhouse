<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-row class="mb-1">
          <b-form-group
            class="col-3"
            id="userid-group"
            label="작성자:"
            label-for="userid"
          >
            <b-form-input
              id="userid"
              v-model="comment.userid"
              type="text"
              disabled
              placeholder="작성자 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            class="col-9"
            id="content-group"
            label="내용:"
            label-for="content"
          >
            <b-form-textarea
              id="content"
              v-model="comment.content"
              placeholder="내용 입력..."
              rows="3"
              max-rows="10"
            ></b-form-textarea>
          </b-form-group>
        </b-row>
        <b-row align-h="end">
          <b-button type="submit" variant="primary" class="m-1"
            >댓글작성</b-button
          >
          <b-button type="reset" variant="danger" class="m-1 mr-3"
            >초기화</b-button
          >
        </b-row>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";
export default {
  name: "BoardInputItem",
  data() {
    return {
      comment: {
        userid: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(boardStore, ["article"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.comment.userid = this.userInfo.id;
  },
  methods: {
    ...mapActions(boardStore, ["writeComment", "getComments", "clearComments"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.comment.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.registComment();
    },
    onReset(event) {
      event.preventDefault();
      this.comment.userid = "";
      this.comment.content = "";
    },
    registComment() {
      let info = {
        articleno: this.article.articleno,
        userid: this.comment.userid,
        content: this.comment.content,
      };
      // console.log(info);
      this.writeComment(info);
      alert("댓글 작성 완료!!");
      this.clearComments();
      this.getComments(this.article.articleno);
      // .then(this.$store.dispatch("updateComments", this.comment.articleno))
      this.comment.content = "";
    },

    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style></style>
