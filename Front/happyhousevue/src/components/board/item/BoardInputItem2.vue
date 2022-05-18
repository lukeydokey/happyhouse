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
            description="작성자를 입력하세요."
          >
            <b-form-input
              id="userid"
              :disabled="isUserid"
              v-model="comment.userid"
              type="text"
              required
              placeholder="작성자 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            class="col-8"
            id="content-group"
            label="내용:"
            label-for="content"
          >
            <b-form-textarea
              id="content"
              v-model="comment.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
        </b-row>
        <b-button type="submit" variant="primary" class="m-1"
          >댓글작성</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardInputItem",
  data() {
    return {
      comment: {
        articleno: this.$route.params.articleno,
        userid: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },

  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.comment.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
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
      http
        .post(`/comment`, {
          articleno: this.$route.params.articleno,
          userid: this.comment.userid,
          content: this.comment.content,
        })
        .then(this.$emit("updated", "등록 완료!"));

      this.comment.userid = "";
      this.comment.content = "";
    },

    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style></style>
