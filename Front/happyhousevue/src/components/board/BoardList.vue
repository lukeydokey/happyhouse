<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          :current-page="curPage"
          :per-page="perPage"
          @row-clicked="viewArticle"
        >
        </b-table>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-pagination
          align="center"
          v-model="curPage"
          :per-page="perPage"
          :total-rows="articleslength"
        ></b-pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { moment } from "moment";
import { mapActions, mapState } from "vuex";
const boardStore = "boardStore";

export default {
  name: "BoardList",
  data() {
    return {
      curPage: 1,
      perPage: 5,
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        {
          key: "regtime",
          label: "작성일",
          tdClass: "tdClass",
          formatter: "dateFormat",
        },
      ],
    };
  },
  computed: {
    ...mapState(boardStore, ["articles", "articleslength"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    this.listArticle(param);
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    ...mapActions(boardStore, [
      "listArticle",
      "getArticle",
      "getComments",
      "clearComments",
    ]),
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    viewArticle(article) {
      this.getArticle(article.articleno);
      this.clearComments();
      this.getComments(article.articleno);
      setTimeout(() => {
        this.$router.push({
          name: "boardDetail",
        });
      }, 200);
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
