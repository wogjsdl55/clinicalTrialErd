<template>
  <v-container grid-list-xl fluid>
    <v-card color="#f2f4f7"  height="55" class="font-weight-bold">
        <v-card-text>인구학적 조사</v-card-text>
    </v-card>
          
      <v-card  v-for="(data, index) in dataList" v-bind:key="data.group_type" :key="index">
          <v-card color="#f2f4f7" height="auto" width="300" style="display: flex;"> 
            <v-card-text >{{ data.group_etc1 }}</v-card-text>

            <v-card width="0" >
              <component  v-bind:is="data.group_type" />
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
      items: [],
      dataList: {},
    }
  },

  created() {
    const vm = this;
    
    //axios 통신으로 spring에 데이터 가져오기
    vm.$axios.post('http://localhost:8080/pageSetting/getInfo', {
    headers : {"Content-Type" : "application/json"},
      //페이지명 설정
      group_name: 'Investigation'
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
  methods: {
    saveButton(){
    }
  }
}
</script>

<style>

</style>
