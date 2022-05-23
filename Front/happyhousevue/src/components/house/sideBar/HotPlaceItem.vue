<template>
  <b-tr>
    <b-td>{{ index + 1 }}</b-td>
    <b-td>
      {{ sidoName }}
      {{ gugunName }}
      {{ dongName }}
    </b-td>
  </b-tr>
</template>

<script>
export default {
  props: {
    index: Number,
    sidoName: String,
    gugunName: String,
    dongName: String,
  },
  created() {
    console.log(this.index);
  },
};
</script>

<style></style>
