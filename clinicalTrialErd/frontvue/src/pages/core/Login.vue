<template>
  <v-app id="login" class="secondary">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4 lg4>
            <v-card class="elevation-1 pa-3">
              <v-card-text>
                <div class="layout column align-center">
                  <img src="static/logo.png" alt="Vue Material Admin" width="180" height="180">
                  <h1 class="flex my-4 primary--text">로그인</h1>
                </div>
                <v-form>
                  <v-text-field
                    append-icon="mail"
                    name="login"
                    label="이메일"
                    type="text"
                    v-model="userEmail"
                    :error="error"
                    :rules="[rules.required]"/>
                  <v-text-field
                    :type="hidePassword ? 'password' : 'text'"
                    :append-icon="hidePassword ? 'visibility_off' : 'visibility'"
                    name="password"
                    label="패스워드"
                    id="password"
                    :rules="[rules.required]"
                    v-model="userPassword"
                    :error="error"
                    @click:append="hidePassword = !hidePassword"/>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-btn block color="primary" @click="login" :loading="loading">로그인</v-btn>
              </v-card-actions>
              <v-card-text>
                <h6 class="text-h6 text-muted font-weight-medium d-flex justify-center align-center mt-3">
                    처음이십니까?
                    <RouterLink to="/Register" class="text-primary text-decoration-none text-body-1 opacity-1 font-weight-medium pl-2">회원가입</RouterLink>
                </h6>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
      <v-snackbar
        v-model="showResult"
        :timeout="2000"
        top>
        {{ result }}
      </v-snackbar>
    </v-content>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      userEmail: '',
      userPassword: '',
      hidePassword: true,
      error: false,
      showResult: false,
      result: '',
      rules: { required: value => !!value || 'Required.' }
    }
  },

  methods: {
    login() {
      const vm = this;

      if (!vm.userEmail) {  vm.result = "이메일이 입력되지 않았습니다."; vm.showResult = true; return; }
      if (!vm.userPassword) {  vm.result = "패스워드가 입력되지 않았습니다."; vm.showResult = true; return; }

      //axios 통신으로 spring에 API
      vm.$axios.post('/member/getMemberInfo', {
          headers : {"Content-Type" : "application/json"},
          user_email: vm.userEmail
          , user_pwd: vm.userPassword
        })

        .then(function(response) {
          //0:실패, 1: 성공, 99:에러
          if(response.data.resultCode == 99){ alert("오류가 발생하였습니다."+ response.data.resultMsg);
          //정상 로그인
          }else if(response.data.resultCode == 1){
            //토큰 store에 저장
            vm.$store.dispatch('setToken', response.data.accessToken)
            vm.$store.dispatch('user_email', vm.userEmail)
            vm.$router.push({ name: 'Dashboard' });
          //기타 메세지
          }else{ alert(response.data.resultMsg); }
        })
        .catch(function(error) { console.log(error); });
    }
  }
}
</script>

<style scoped lang="css">
  #login {
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    content: "";
    z-index: 0;
    background: radial-gradient(rgb(210, 241, 223), rgb(211, 215, 250), rgb(186, 216, 244)) 0% 0% / 400% 400%;
  }
</style>
