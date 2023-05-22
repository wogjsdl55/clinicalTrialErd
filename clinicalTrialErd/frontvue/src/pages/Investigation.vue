<template>
  <v-container grid-list-xl fluid>
    <v-card color="#f2f4f7"  height="55" class="font-weight-bold">
        <v-card-text>인구학적 조사</v-card-text>
    </v-card>
        
    <v-card  v-for="(data, index) in dataList" v-bind:key="data.group_type" :key="index">
        <v-card color="#f2f4f7" height="auto" width="300" style="display: flex;"> 
          <v-card-text >{{ data.group_etc1 }}</v-card-text>

          <v-card width="0" >
            <component  v-bind:is="data.group_type" v-bind:results="data"  @dataCheck="dataCheck" />
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

    <v-snackbar
      v-model="showResult"
      :timeout="2000"
      top>
      {{ result }}
    </v-snackbar>
  </v-container>
</template>


<script>
export default {
  data () {
    return {
      radiodate: '',
      pickDate: '',
      timeData: [],
      items: [],
      dataList: {},
      resultList: {},
      formData: [],
      showResult: false,
      result: '',
    }
  },

  created() {
    const vm = this;
    //생년월일
    vm.$EventBus.$on('pickDate', function(pickDate){ vm.pickDate = pickDate; });
    //axios 통신으로 spring에 데이터 가져오기
    vm.$axios.post('/pageSetting/getInfo', {
    headers : {"Content-Type" : "application/json"},
      //페이지명 설정
      group_name: 'Investigation'
    })
    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){
        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        vm.items = eval('['+ response.data.data + ']');
        vm.resultList = eval(vm.items[0].dataList);

        //인구학적조사 데이터 가져오기
        vm.$axios.post('/scn/investigation/select', {
        headers : {"Content-Type" : "application/json"},
          //페이지명 설정
          token: vm.$store.getters.token,
          user_email: vm.$store.getters.userEmail,
          visit_date: '',
        })
        .then(function(response) {
          //0:실패, 1: 성공, 99:에러
          if(response.data.resultCode == 1){
            // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
            vm.items = eval('['+ response.data.data + ']');
            //기존 인구학적 조사 데이터 넣기
            vm.resultList[0].group_data = vm.items[0].birthday;
            vm.resultList[1].group_data = vm.items[0].time_birth;
            vm.resultList[2].group_data = vm.items[0].age;
            vm.resultList[3].group_data = vm.items[0].gender;
            vm.resultList[4].group_data = vm.items[0].races;
            vm.resultList[5].group_data = vm.items[0].ethnic;

            vm.dataList = vm.resultList;
          }
        })
        .catch(function(error) { console.log(error); });

      }
      else{alert('오류가 발생하였습니다.' + response.data.resultMsg);}
    })
    .catch(function(error) {console.log(error);});


  },
  methods: { 
    saveButton(){ 
      const vm = this;

      if (!vm.pickDate[1]) { vm.result = "생년월일이 입력되지 않았습니다."; vm.showResult = true; return; }

      //인구학적조사 저장
      vm.$axios.post('/scn/investigation/insert', {
        headers : {"Content-Type" : "application/json"},
        //페이지명 설정
        user_email: vm.$store.getters.userEmail,
        birthday:   vm.pickDate[1],
        age:        vm.formData[0],
        ethnic:     vm.formData[1],
        gender:     vm.formData[2],
        races:      vm.formData[3],
        time_birth: vm.timeData[0],
        
      })
      .then(function(response) {
        //0:실패, 1: 성공, 99:에러
        if(response.data.resultCode == 1){ alert(response.data.resultMsg); }
      })
      .catch(function(error) { console.log(error); });
    },
    dataCheck(dataCheck){
      const vm = this;
      vm.dataCheck = dataCheck;
      //radio체크
      if(dataCheck[0]=="Age Unit"){ vm.formData[0] =  dataCheck[1] }
      if(dataCheck[0]=="인종"){  vm.formData[1] =  dataCheck[1] }
      if(dataCheck[0]=="성별"){  vm.formData[2] =  dataCheck[1] }
      if(dataCheck[0]=="민족"){  vm.formData[3] =  dataCheck[1] }

      //출생시간 체크
      if(dataCheck[0]=="출생시간"){ vm.timeData[0] =  dataCheck[1] }
    }
  }
}
</script>

<style>

</style>
