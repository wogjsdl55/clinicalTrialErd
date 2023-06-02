<template>
  <v-container grid-list-xl fluid>
    <v-card color="#f2f4f7"  height="55" class="font-weight-bold">
        <v-card-text>본제 투여 현황</v-card-text>
    </v-card>
      <v-card>
        <v-data-table :headers="headers" :items="datas" :items-per-page="5" item-value="name" class="elevation-1" >
        <template v-slot:items="props">
          <td>
            <component is="deleteButton" v-bind:results="props" @deleteButton="deleteButton"/>
            <v-btn color="secondary" class="ma-2" @click="editButton(props)">수정</v-btn> 
          </td>
          <td>{{ props.item.seq }}</td>
          <td>{{ props.item.injection_yn }}</td>
          <td>{{ props.item.injection_start_date }}</td>
          <td>{{ props.item.injection_start_time }}</td>
          <td>{{ props.item.injection_end_date }}</td>
          <td>{{ props.item.injection_end_time }}</td>
          <td>{{ props.item.injection_date }}</td>
          <td>{{ props.item.unit }}</td>
          <td>{{ props.item.injection_cnt }}</td>
          <td>{{ props.item.route }}</td>
          <td>{{ props.item.rate_change }}</td>
          <td>{{ props.item.rate_reason }}</td>
        </template>
      </v-data-table>
      </v-card>

      <v-card  v-for="(data, index) in dataList" v-bind:key="data.group_type" :key="index">
          <v-card color="#f2f4f7" height="auto" width="300" style="display: flex;"> 
            <v-card-text >{{ data.group_etc1 }}</v-card-text>

            <v-card width="0" >
              <!--is 컴포넌트명 호출, resulst 부모, 자식 컴포넌트 간 데이터 전달(props, emit)-->
              <component  v-bind:is="data.group_type" v-bind:results="data"   @dataCheck="dataCheck" />
            </v-card>
        </v-card>  
      </v-card>

      <v-btn
        color="primary"
        style="margin: 3rem; margin-left: 30rem;"
        @click="saveButton()"
      >
        저장
      </v-btn>
  </v-container>
</template>


<script>
export default {
  data () {
    return {
      dataList: {},
      resultList: {},
      items: [],
      formData: [],
      dateData:[],
      pickDate: [],
      timeData: [],
      datas: [],

      headers: [
      // { text: "수정/삭제", align: "start", sortable: false, value: "id" },
      { text: "수정/삭제"  },
      { text: "No",   value: "seq" },
      { text: "본제 투여 여부", value: "injection_yn" },
      { text: "투여 시작일", value: "injection_start_date" },
      { text: "투여 시작 시간", value: "injection_start_time" },
      { text: "투여 종료일", value: "injection_end_date" },
      { text: "투여 종료 시간", value: "injection_end_time" },
      { text: "투여일", value: "injection_date" },
      { text: "단위", value: "unit" },
      { text: "투여횟수", value: "injection_cnt" },
      { text: "경로", value: "route" },
      { text: "투여량 변경 여부", value: "rate_change" },
      { text: "투여랑 변경 사유", value: "rate_reason" }
      ],
    }
  },

  created() {
    const vm = this;
    //eventBus로 데이터 전달
    vm.$EventBus.$on('pickDate', function(pickDate){ 
      vm.pickDate = pickDate; 

      if(pickDate[0]=="투여 시작일"){        vm.dateData[0] =  pickDate[1] }
      if(pickDate[0]=="투여 종료일"){        vm.dateData[1] =  pickDate[1] }
      if(pickDate[0]=="투여"){               vm.dateData[2] =  pickDate[1] }
    });

    //본제 투여 현황 데이터 가져오기
    vm.$axios.post('/scn/administration/select', {
    headers : {"Content-Type" : "application/json"},
      //페이지명 설정
      token: vm.$store.getters.token,
      userEmail: vm.$store.getters.userEmail,
    })
    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){
        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        vm.datas = eval('['+ response.data.data + ']');
        vm.datas = eval('['+ vm.datas[0] + ']');
      }
    })
    .catch(function(error) { console.log(error); });

    //axios 통신으로 spring에 페이지 데이터 가져오기
    vm.$axios.post('/pageSetting/getInfo', {
      headers : {"Content-Type" : "application/json"},
      //페이지명 설정
      group_name: 'administration'
    })
    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){

        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        vm.items = eval('['+ response.data.data + ']');
        vm.resultList = eval(vm.items[0].dataList)
        vm.dataList = vm.resultList;

      }else{ alert('오류가 발생하였습니다.' + response.data.resultMsg); }
    })
    .catch(function(error) { console.log(error); });    
  },
  methods: { 
    saveButton(){ 
      const vm = this;
      if (!vm.dateData[0]) { vm.result = "투여 시작일이 입력되지 않았습니다."; vm.showResult = true; return; }
      if (!vm.dateData[1]) { vm.result = "투여 종료일이 입력되지 않았습니다."; vm.showResult = true; return; }
      if (!vm.dateData[2]) { vm.result = "투여일이 입력되지 않았습니다."; vm.showResult = true; return; }

      //인구학적조사 저장
      vm.$axios.post('/scn/administration/insert', {
        headers : {"Content-Type" : "application/json"},
        seq: vm.dataList[0].group_seq,
        //페이지명 설정
        userEmail:           vm.$store.getters.userEmail,
        //본제 투여 여부
        injection_yn:         vm.formData[0],
        //투여 시작일
        injection_start_date: vm.dateData[0],
        //투여 시작 시간
        injection_start_time: vm.timeData[0],
        //투여 종료일
        injection_end_date:   vm.dateData[1],
        //투여 종료 시간 
        injection_end_time:   vm.timeData[1],
        //투여일
        injection_date:       vm.dateData[2],
        //단위
        unit:                 vm.formData[1],
        //투여횟수
        injection_cnt:        vm.formData[2],
        //경로
        route:                vm.formData[3],
        //투여량 변경 여부
        rate_change:          vm.formData[4],
        //투여량 변경 사유
        rate_reason:          vm.formData[5]

      })
      .then(function(response) {
        //0:실패, 1: 성공, 99:에러
        if(response.data.resultCode == 1){ alert(response.data.resultMsg); vm.$router.push({ name: 'Administration' }); }
        else {  alert(response.data.resultMsg); }})
      .catch(function(error) { console.log(error); });
    },
    dataCheck(dataCheck){
      const vm = this;
      vm.dataCheck = dataCheck;
      //date체크
      if(dataCheck[0]=="투여 시작일"){        vm.dateData[0] =  dataCheck[1] }
      if(dataCheck[0]=="투여 종료일"){        vm.dateData[1] =  dataCheck[1] }
      if(dataCheck[0]=="투여"){               vm.dateData[2] =  dataCheck[1] }

      //radio체크
      if(dataCheck[0]=="본제 투여 여부"){      vm.formData[0] =  dataCheck[1] }
      if(dataCheck[0]=="단위"){                vm.formData[1] =  dataCheck[1] }
      if(dataCheck[0]=="투여횟수"){            vm.formData[2] =  dataCheck[1] }
      if(dataCheck[0]=="경로"){                vm.formData[3] =  dataCheck[1] }
      if(dataCheck[0]=="투여량 변경 여부"){     vm.formData[4] =  dataCheck[1] }
      if(dataCheck[0]=="투여량 변경 사유"){     vm.formData[5] =  dataCheck[1] }

      //출생시간 체크
      if(dataCheck[0]=="투여 시작 시간"){       vm.timeData[0] =  dataCheck[1] }
      if(dataCheck[0]=="투여 종료 시간"){       vm.timeData[1] =  dataCheck[1] }
    },
    //삭제버튼
    deleteButton(deleteButton){
      const vm = this;
      vm.deleteButton = deleteButton;
      vm.dialog = false;
      
      //본제 투여 현황 삭제
      vm.$axios.post('/scn/administration/delete', {
        headers : {"Content-Type" : "application/json"},
        //페이지명 설정
        seq: vm.deleteButton
      })

      .then(function(response) {
        //0:실패, 1: 성공, 99:에러
        if(response.data.resultCode == 1){ alert(response.data.resultMsg);  vm.$router.go(); }
        else{ alert('오류가 발생하였습니다.' + response.data.resultMsg); }
      })

      .catch(function(error) { console.log(error); })
    },
    //수정버튼
    editButton(data){
      const vm = this;

      vm.resultList[0].group_data =  data.item.injection_yn;
      vm.resultList[1].group_data =  data.item.injection_start_date;
      vm.resultList[2].group_data =  data.item.injection_start_time;
      vm.resultList[3].group_data =  data.item.injection_end_date;
      vm.resultList[4].group_data =  data.item.injection_end_time;
      vm.resultList[5].group_data =  data.item.injection_date;
      vm.resultList[6].group_data =  data.item.unit;
      vm.resultList[7].group_data =  data.item.injection_cnt;
      vm.resultList[8].group_data =  data.item.route;
      vm.resultList[9].group_data =  data.item.rate_change;
      vm.resultList[10].group_data = data.item.rate_reason;
      vm.resultList[0].group_seq =  data.item.seq;

      vm.dataList = vm.resultList; 
    }
  },
}
</script>
 