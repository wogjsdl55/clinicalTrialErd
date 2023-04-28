<template>
    <v-container grid-list-xl fluid>
      <v-layout row wrap>

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
    const pageNo = "1";
    
    //axios 통신으로 spring에 데이터 가져오기
    vm.$axios.get('http://localhost:8080/bioInfo/getInfo?pageNo='+ pageNo, {
    headers : {"Content-Type" : "application/json"},
    })
    .then(function(response) {
      //0:실패, 1: 성공, 99:에러
      if(response.data.resultCode == 1){
        if(response.data.statusCode != 200){
          vm.$router.push({ name: 'Error', params: { errorCode: response.data.statusCode } });
        }

        // eval 함수를 사용하면, 문자열을 넣어도 코드로 인식하게된다
        vm.itmes = eval(response.data.items);

      }else{
        alert('오류가 발생하였습니다.' + response.data.resultMsg);
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
