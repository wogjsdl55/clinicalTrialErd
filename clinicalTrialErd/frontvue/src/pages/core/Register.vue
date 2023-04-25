<template>
    <v-app id="register" class="secondary">
      <v-content>
        <v-container fluid fill-height>
          <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4 lg4>
              <v-card class="elevation-1 pa-3">
                <v-card-text>
                  <div class="layout column align-center">
                    <img src="static/logo.png" alt="Vue Material Admin" width="180" height="180">
                    <h1 class="flex my-4 primary--text">회원가입</h1>
                  </div>
                  <v-form>
                    <v-text-field
                      append-icon="person"
                      name="Name"
                      label="이름"
                      type="text"
                      v-model="userName"
                      :error="error"
                      :rules="[rules.required]"/>
                    <v-text-field
                      append-icon="mail"
                      name="Email"
                      label="이메일"
                      type="text"
                      v-model="userEmail"
                      :error="error"
                      :rules="[rules.reg_email]"/>
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
                  <v-btn block color="primary" @click="register" :loading="loading">회원가입</v-btn>
                </v-card-actions>
                <v-card-text>
                  <h6 class="text-h6 text-muted font-weight-medium d-flex justify-center align-center mt-3">
                        이미 계정이 있으십니까?
                      <RouterLink to="/login" class="text-primary text-decoration-none text-body-1 opacity-1 font-weight-medium pl-2">로그인</RouterLink>
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
        userName: '',
        userEmail: '',
        userPassword: '',
        hidePassword: true,
        error: false,
        showResult: false,
        result: '',
        rules: {
          required: value => !!value || 'Required.'
          , reg_email: v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 형식으로 부탁드립니다.'
        }
      }
    },
  
    methods: {
        register() {
        const vm = this;
            
        if (!vm.userName) {  
          vm.result = "이름이 입력되지 않았습니다.";
          vm.showResult = true;
  
          return;
        }

        if (!vm.userEmail) {  
          vm.result = "이메일이 입력되지 않았습니다.";
          vm.showResult = true;
  
          return;
        }

        if (!vm.userPassword) {  
          vm.result = "패스워드가 입력되지 않았습니다.";
          vm.showResult = true;
  
          return;
        }
        //axios 통신으로 spring에 데이터 전달
        vm.$axios.post('http://localhost:8080/member/insMemberInfo', {
          headers : {"Content-Type" : "application/json"},
		      user_name: vm.userName
          , user_pwd: vm.userPassword
          , user_email: vm.userEmail
        })
        .then(function(response) {
          if(response.data != 0){
            alert(response.data);
          }else{
            alert('오류가 발생하였습니다.' + response.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
      }
    }
  }
  </script>
  
  <style scoped lang="css">
    #register {
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
  