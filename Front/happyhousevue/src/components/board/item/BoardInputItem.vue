<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            v-model="articleInfo.userid"
            type="text"
            required
            disabled
            ref="userid"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="articleInfo.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="articleInfo.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
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
      articleInfo: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(boardStore, ["article", "message"]),
  },
  mounted() {
    this.articleInfo.userid = this.userInfo.id;
    if (this.type != "register") {
      this.articleInfo.articleno = this.article.articleno;
      this.articleInfo.subject = this.article.subject;
      this.articleInfo.content = this.article.content;
    }
  },
  methods: {
    ...mapActions(boardStore, ["writeArticle", "modifyArticle"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.articleInfo.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.articleInfo.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modiArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.articleInfo.articleno = 0;
      this.articleInfo.subject = "";
      this.articleInfo.content = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      let info = {
        userid: this.articleInfo.userid,
        subject: this.articleInfo.subject,
        content: this.articleInfo.content,
      };
      this.writeArticle(info);
      alert("글작성 성공!!");
      this.moveList();
    },
    modiArticle() {
      this.modifyArticle(JSON.stringify(this.articleInfo));
      alert("수정 완료!!!");
      this.moveList();
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style></style>
