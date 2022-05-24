<template>
  <b-table-simple hover responsive class="mt-3">
    <b-thead>
      <b-tr>
        <b-th colspan="3"><b>검색 순위 TOP 3</b></b-th>
      </b-tr>
    </b-thead>
    <b-tbody v-if="hotplaces.length != 0">
      <search-ranking-item
        v-for="(hotplace, index) in hotplaces"
        :key="index"
        :index="index"
        v-bind="hotplace"
      />
    </b-tbody>
    <b-tbody v-else>
      <b-tr><b-td> 검색 기록이 없습니다. </b-td></b-tr>
    </b-tbody>
  </b-table-simple>
</template>

<script>
import { mapState } from "vuex";
import SearchRankingItem from "@/components/house/ranking/SearchRankingItem.vue";

const searchStore = "searchStore";
export default {
  name: "SearchRanking",
  components: {
    SearchRankingItem,
  },
  computed: {
    ...mapState(searchStore, ["hotplaces"]),
  },
};
</script>

<style></style>
