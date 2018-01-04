<template><b-container fluid class="home" v-show="isShow">
  <b-row no-gutters><navigator></navigator></b-row>
  <b-row no-gutters><contentShow></contentShow></b-row>
  <b-row no-gutters>{{fetch_data}}</b-row>
</b-container></template>

<script>
import navigator from '../modules/nav'
import contentShow from '../modules/contentShow'
export default {
  data() {
    return {
      fetch_data: '',
      isShow: false
    }
  },
  created() {
    var prefix = this.GLOBAL.config.prefix;
    this.$http.post(prefix + '/security/getHomeData.do').then(
      (response) => {
        var router = this.$router
        var _runStatus = this.GLOBAL.runStatus;

        switch(response.body.statusCode) {
          case _runStatus.STATUS_LOGGED:
            this.fetch_data = response.body.data;
            this.isShow = true;
            break;
          case _runStatus.STATUS_NOLOGGED:
          case _runStatus.STATUS_SESSION_TIMEOUT:
            this.$router.push({name: 'login'});
            break;
          case _runStatus.STATUS_SESSION_SINGLE_USER_RESTRICTION:
            this.fetch_data = '用户已被踢出！';
            this.isShow = true;
            setTimeout(function() {
                router.push({name: 'login'})
              }, 2000); 
            break;
          case _runStatus.STATUS_ACCESS_DENY:
            //do something
            break;
          default :
            //之后再说
        }
      },
      (response) => {
      	//error
      }
    )
  },
  components: {
    navigator,
    contentShow
  }
}
</script>

<style>
.home {
  padding: 0;
}
</style>