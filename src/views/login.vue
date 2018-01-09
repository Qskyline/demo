<template>
<b-container fluid class="container_login" v-show="isShow">
  <b-row no-gutters>
    <b-col>
      <b-alert :variant="state" dismissible :show="showDismissibleAlert" @dismissed="showDismissibleAlert=false" >
        {{loginInfo}}{{help}}{{count}}{{unit}}
      </b-alert>
    </b-col>
  </b-row>

  <b-row align-v="center" align-h="center" class="row_login_frame">
    <b-col class="col_login_frame" cols="10" md="4" lg="3" xl="2">
      <b-row no-gutters class="text-center row_logo"><b-col>Skyline</b-col></b-row>
      <b-row no-gutters align-h="center"><b-col cols="10"><hr class="line"></b-col></b-row>
      <vue-form :state="formstate" v-model="formstate" @submit.prevent="submit">
        <validate auto-label class="form-group required-field">
          <input 
              type="text"
              name="name"
              :style="backgroundDiv"
              class="form-control login_input_username login_input"
              placeholder="Enter your name"
              required pattern="^[a-zA-Z][a-zA-Z|_|\-]{3,19}$"
              v-model.lazy="user.name">
        </validate>
        <validate auto-label class="form-group required-field">
          <input 
            type="password" 
            name="password" 
            :style="backgroundDiv"
            class="form-control login_input_password login_input" 
            placeholder="Enter your password"
            required maxlength="16" minlength="8" 
            v-model.lazy="user.password">
        </validate>
        <b-row no-gutters class="row_choice">
          <b-col cols="7" class="col_rem">
            <b-form-checkbox v-model="user.isRememberMe">&nbsp;记住我</b-form-checkbox>
          </b-col>
          <b-col cols="3"><a href="#">忘记密码</a></b-col>
          <b-col cols="2"><a href="#/register">注册</a></b-col>
        </b-row>
        <b-row no-gutters class="row_submit">
          <b-col>
            <b-button variant="secondary" type="submit" class="login_button">提交</b-button>
          </b-col>
        </b-row>
      </vue-form>
    </b-col>
  </b-row>
</b-container>
</template>

<script>
import md5 from 'js-md5'
export default {
  created() {
    var _runStatus = this.GLOBAL.runStatus;
    var prefix = this.GLOBAL.config.prefix;
    this.$http.post(prefix + '/security/check_loginStatus.do').then(
      (response) => {
        switch(response.body.statusCode) {
          case _runStatus.STATUS_LOGGED:
            this.$router.push({path: '/home'});
            break;
          case _runStatus.STATUS_NOLOGGED:
          case _runStatus.STATUS_SESSION_TIMEOUT:
          case _runStatus.STATUS_SESSION_SINGLE_USER_RESTRICTION:
          case _runStatus.STATUS_ACCESS_DENY:
            this.isShow = true;
            break;
          default :
            //之后再说
        }
      },
      (response) => {
      }
    )
  },
  data() {
    return {
      formstate: {},
      user: {
        name: '',
        password: '',
        isRememberMe: ''
      },
      isShow: false,
      showDismissibleAlert: false,
      loginInfo: '',
      state: 'danger',
      count: '',
      unit: '',
      help: ''
    }
  },
  computed: {
    backgroundDiv: function() {
      return {
        backgroundImage: 'url(' + require('../assets/input_icons_24_2764534.png') + ')'
      }
    }
  },
  methods: {
    submit: function() {
      if (this.formstate.$valid) {
        var _loginStatus = this.GLOBAL.loginStatus;
        var prefix = this.GLOBAL.config.prefix;
        var router = this.$router;
        var userEncrypt = {};
        userEncrypt.name = this.user.name;
        userEncrypt.password = md5(this.user.password);
        userEncrypt.isRememberMe = this.user.isRememberMe;

        this.$http.post(prefix + '/security/login.do', userEncrypt).then(
          (response) => {
            switch(response.body.statusCode) {
              case _loginStatus.LOGIN_SUCCESS:
                router.push({path: '/home'});
                break;
              case _loginStatus.LOGIN_USER_ALREADY_LOGGED:
                this.state = 'info';
                this.help = ' in ';
                this.count = 5;
                this.unit = ' second!';
                var tt = setInterval(() => {
                    this.count --;
                    if(this.count == 0) {
                      clearInterval(tt);
                      router.push({path: '/home'});
                    }
                  }, 1000);
              case _loginStatus.LOGIN_AUTH_FAILED:
              case _loginStatus.LOGIN_USER_LOCKED:
              case _loginStatus.LOGIN_USER_NICKOUT_ERROR:
                this.loginInfo = response.body.errMsg;
                this.showDismissibleAlert = true;
                break;
              case _loginStatus.LOGIN_SECURITY_SQLINJECTION:
                this.loginInfo = "To be honest, this Message should never come to you."
                this.showDismissibleAlert = true;
                break;
              default :
                this.loginInfo = 'Unknown error!';
                this.showDismissibleAlert = true;
            }
          },
          (response) => {
            this.loginInfo = 'Request Timeout!';
            this.showDismissibleAlert = true;
          }
        );
      } else {
        this.loginInfo = 'Format Error!';
        this.showDismissibleAlert = true;
      }
    }
  }
}
</script>

<style>
.container_login {
  padding: 0;
}
.row_login_frame {
  margin-top: 10%;
}
.col_login_frame {
  border: 1px solid #555555;
}
.row_logo {
  margin-top: 1rem;
  margin-bottom: -1rem;
}
.line {
  border: none;
  border-top: 1px solid #555555;
}
.login_input {
  padding-left: 3rem !important;
  background-repeat: no-repeat;
  margin-bottom: 3%;
}
.login_input_username {
  background-position: 1% 31%;
}
.login_input_username:focus {
  background-position: 1% 50.6%;
} 
.login_input_password {
  background-position: 1% 69%;
}
.login_input_password:focus {
  background-position: 1% 88.6%;
}
.row_choice {
  font-size: small;
  text-align: right;
}
.col_rem {
  text-align: left;
}
.row_submit {
  margin-bottom: 1rem;
}
.login_button {
  width: 100%;
}
</style>