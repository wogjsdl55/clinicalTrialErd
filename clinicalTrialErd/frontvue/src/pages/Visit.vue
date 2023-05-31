<template>
  <v-container grid-list-xl fluid>
    <!--삭제버튼 컴포넌트-->
    <component is="deleteButton" @deleteButton="deleteButton"/>

    <v-card color="#f2f4f7"  height="55" class="font-weight-bold"><v-card-text>방문일</v-card-text></v-card>
      <v-card v-for="(data, index) in dataList" v-bind:key="data.group_type">
          <v-card color="#f2f4f7" height="40" width="300" style="display: flex;"> 
            <v-card-text >{{ data.group_etc1 }}</v-card-text>

            <v-card width="0" ><component  v-bind:is="data.group_type" v-bind:results="data"  /></v-card>
          </v-card>  
      </v-card>

      <v-btn color="primary" style="margin: 3rem; margin-left: 30rem;" @click="saveButton">저장</v-btn>
      <v-snackbar v-model="showResult" :timeout="2000"  top>{{ result }}</v-snackbar>
  </v-container>
</template>


<script>
export default {
  data () {
    return {
      result: '',
      pickDate: '',
      showResult: false,
      items: [],
      dataList: {}
    }
  },

  created() {
    const vm = this;
    vm.$EventBus.$on('pickDate', function(pickDate){ vm.pickDate = pickDate; });

    //토큰 확인
    if(vm.$store.getters.isLogin == false){ vm.$router.push({ name: 'Login' });  }
    
    //방문일 데이터 가져오기
    vm.$axios.post('/scn/visit/select', {
      headers : {"Content-Type" : "application/json", "Authrozation":  vm.$store.getters.token},
      //페이지명 설정
      token: vm.$store.getters.token,
      user_email: vm.$store.getters.userEmail,
      visit_date: '',
    })
    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){
        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        vm.items = eval( '['+ response.data.data + ']');
        //기존 방문일 데이터 넣기
        vm.visit_date = vm.items[0].visit_date;
      }
    })

    .catch(function(error) { console.log(error); });

    //페이지 셋팅 값 데이터 가져오기
    vm.$axios.post('/pageSetting/getInfo', {
      headers : {"Content-Type" : "application/json", "Authrozation": vm.$store.getters.token},
      //페이지명 설정
      group_name: 'visit'
    })

    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){
        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        const data= '['+ response.data.data + ']';
        vm.items = eval(data);
        vm.dataList = eval(vm.items[0].dataList)
      }else{ alert('오류가 발생하였습니다.' + response.data.resultMsg); }
    })

    .catch(function(error) { console.log(error); });
  },
  //렌더링 된 후 호출
  updated() { this.sendResultData(); },

  methods: {
    saveButton(){
      const vm = this;
      if (!vm.pickDate) {  vm.result = "방문일이 입력되지 않았습니다."; vm.showResult = true;  return;  }

      //방문일 저장
      vm.$axios.post('/scn/visit/insert', {
        headers : {"Content-Type" : "application/json"},
        //페이지명 설정
        user_email: vm.$store.getters.userEmail,
        visit_date: vm.pickDate[1]
      })

      .then(function(response) {
        //0:실패, 1: 성공, 99:에러
        if(response.data.resultCode == 1){ alert(response.data.resultMsg); vm.$router.go(); }
      })

      .catch(function(error) { console.log(error); });
    },
    //datePick 데이터 전달
    sendResultData(){ this.$EventBus.$emit('datePick', this.visit_date); },

    //삭제버튼
    deleteButton(){
      const vm = this;
      vm.dialog = false;
      //체크
      if (!this.visit_date) { vm.result = "방문일이 입력되지 않았습니다."; vm.showResult = true; return; }

      //방문일 삭제
      vm.$axios.post('/scn/visit/delete', {
        headers : {"Content-Type" : "application/json"},
        //페이지명 설정
        user_email: vm.$store.getters.userEmail
      })

      .then(function(response) {
        //0:실패, 1: 성공, 99:에러
        if(response.data.resultCode == 1){ alert(response.data.resultMsg);  vm.$router.go(); }
        else{ alert('오류가 발생하였습니다.' + response.data.resultMsg); }
      })

      .catch(function(error) { console.log(error); })
    },
  },
}
</script>