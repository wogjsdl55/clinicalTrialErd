<template>
  <v-container grid-list-xl fluid>
    <v-card color="#f2f4f7"  height="55" class="font-weight-bold">
        <v-card-text>방문일</v-card-text>
    </v-card>
          
      <v-card v-for="(data, index) in dataList" v-bind:key="data.group_type">
          <v-card color="#f2f4f7" height="40" width="300" style="display: flex;"> 
            <v-card-text >{{ data.group_etc1 }}</v-card-text>

            <v-card width="0" >
              <component  v-bind:is="data.group_type"/>
            </v-card>
          </v-card>  
      </v-card>

      <v-btn
        color="primary"
        style="margin: 3rem; margin-left: 30rem;"
        @click="saveButton"
      >
        저장
      </v-btn>
  </v-container>
</template>


<script>
export default {
  data () {
    return {
      pickDate: '',
      visit_seq: '',
      items: [],
      dataList: {},
    }
  },

  created() {
    const vm = this;
    vm.$EventBus.$on('pickDate', function(pickDate){ vm.pickDate = pickDate; });

    //토큰 확인
    if(vm.$store.getters.isLogin == false){
      //vm.$router.push({ name: 'Login' });
    }
    
    //방문일 데이터 가져오기
    vm.$axios.post('http://localhost:8080/scn/visit/select', {
    headers : {"Content-Type" : "application/json"},
      //페이지명 설정
      visit_seq: '',
      user_email: vm.$store.getters.userEmail,
      visit_date: '',
    })
    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){
        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        const data= '['+ response.data.data + ']';
        vm.items = eval(data);
        //기존 방문일 데이터 넣기
        vm.visit_seq = vm.items[0].visit_seq;
        vm.visit_date = vm.items[0].visit_date;

      }else{ alert('오류가 발생하였습니다.' + response.data.resultMsg); }
    })
    .catch(function(error) {
      console.log(error);
    });

    //페이지 셋팅 값 데이터 가져오기
    vm.$axios.post('http://localhost:8080/pageSetting/getInfo', {
    headers : {"Content-Type" : "application/json"},
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

      }else{
        alert('오류가 발생하였습니다.' + response.data.resultMsg);
      }
    })
    .catch(function(error) {
      console.log(error);
    });
  },
  //렌더링 된 후 호출
  updated() {
    this.sendResultData();
  },
  methods: {
    saveButton(){
      const vm = this;
      
      //방문일 저장
      vm.$axios.post('http://localhost:8080/scn/visit/insert', {
        headers : {"Content-Type" : "application/json"},
        //페이지명 설정
        visit_seq: vm.visit_seq,
        user_email: vm.$store.getters.userEmail,
        visit_date: vm.pickDate
      })
      .then(function(response) {
        //0:실패, 1: 성공, 99:에러
        if(response.data.resultCode == 1){
          alert('저장되었습니다.');
        }else{
          alert('오류가 발생하였습니다.' + response.data.resultMsg);
        }
      })
      .catch(function(error) {
        console.log(error);
      });

    },
    sendResultData(){
      this.$EventBus.$emit('datePick', this.visit_date);      
    }
  },
}
</script>

<style>

</style>
