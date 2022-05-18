<template>
  <b-tr>
    <b-td class="col-3">{{ userid }}</b-td>
    <b-td class="text-left">{{ content }}</b-td>
    <b-td class="col-2">{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem2",
  props: {
    userid: String,
    content: String,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
