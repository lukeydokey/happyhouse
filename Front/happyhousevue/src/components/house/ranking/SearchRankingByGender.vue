<template>
  <b-table-simple hover responsive class="mt-3">
    <b-thead>
      <b-tr>
        <b-th colspan="3"><b>같은 성별 검색 순위 TOP 3</b></b-th>
      </b-tr>
    </b-thead>
    <b-tbody v-if="hotplacesbygender.length != 0">
      <search-ranking-by-gender-item
        v-for="(hotplacebygender, index) in hotplacesbygender"
        :key="index"
        :index="index"
        v-bind="hotplacebygender"
      />
    </b-tbody>
    <b-tbody v-else>
      <b-tr><b-td> 검색 기록이 없습니다. </b-td></b-tr>
    </b-tbody>
  </b-table-simple>
</template>

<script>
import { mapState } from "vuex";
import SearchRankingByGenderItem from "@/components/house/ranking/SearchRankingByGenderItem.vue";

const searchStore = "searchStore";
export default {
  name: "SearchRanking",
  components: {
    SearchRankingByGenderItem,
  },
  computed: {
    ...mapState(searchStore, ["hotplacesbygender"]),
  },
};
</script>

<style></style>
