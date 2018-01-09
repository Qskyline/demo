<template><b-container fluid class="home_template" v-show="show">
  <b-row no-gutters><b-col>
    <navigator/>
  </b-col></b-row>

  <b-row no-gutters align-h="center"><b-col cols="11" sm="11" md="11" lg="11" xl="11">
    <b-row><b-col cols="12" sm="12" md="6" lg="4" xl="2" v-for="n in count" class="card_content">
      <router-view/>
    </b-col></b-row>
  </b-col></b-row>
</b-container></template>

<script>
import navigator from '../modules/nav'
export default {
  data() {
    return {
      count: 9
    }
  },
  components: {
    navigator
  },
  props: ['show']
}
</script>

<style>
.home_template {
  padding: 0;
}
.card_content {
  margin-bottom: 1rem !important;
}
</style>