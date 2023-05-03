<template>
    <v-container grid-list-xl fluid>
      <v-layout row wrap>
       <dateType />
      </v-layout>
    </v-container>
</template>

<script>
export default {
  data () {
    return {
      
    }
  },

  created() {
    const vm = this;
    const page_name = "visit";
    
    //axios 통신으로 spring에 데이터 가져오기
    vm.$axios.post('http://localhost:8080/pageSetting/getInfo', {
        headers : {"Content-Type" : "application/json"},
        page_name: 'visit'
      })
    .then(function(response) {
      console.log
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){

        console.log(response.data.data);

      }else{
        alert('오류가 발생하였습니다.' + response.data.resultMsg);
        vm.$router.push({ name: 'Error', params: { errorCode: response.data.statusCode } });
      }
    })
    .catch(function(error) {
      console.log(error);
    });
  }
}
</script>

<style>

</style>
