<template>
  <div>
    <h5><b>거래 매물</b></h5>
    <b-table striped hover :items="items" :fields="fields"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // Note 'isActive' is left out and will not appear in the rendered table
      fields: [
        {
          key: "평수",
          sortable: true,
        },
        {
          key: "층수",
          sortable: false,
        },
        {
          key: "가격",
          label: "가격",
          sortable: true,
        },
      ],
      items: [{}, {}, {}, {}, {}, {}, {}, {}],
    };
  },
};
</script>
