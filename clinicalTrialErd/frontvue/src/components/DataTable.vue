<template>
  <v-data-table
    class="table"
    :headers="headers"
    :items="itmes"
    :rows-per-page-items="[10, 25]">
    <template slot="items" slot-scope="props">
      <td class="text-xs-left">{{ props.item.APPLY_ENTP_NAME }}</td>
      <td class="text-xs-left">{{ props.item.APPROVAL_TIME.substring(0,10) }}</td>
      <td class="text-xs-left">{{ props.item.GOODS_NAME }}</td>
      <td class="text-xs-left">{{ props.item.CLINIC_EXAM_TITLE.substring(0,100) +'...' }}</td>
      <td class="text-xs-left">{{ props.item.CLINIC_STEP_NAME }}</td>
      <!-- <td class="text-xs-left">{{ props.item.address.city }}</td> -->
    </template>
  </v-data-table>
</template>

<script>
export default {
  data() {
    return {
      itmes: [],
      headers: [
        { text: '신청자', width:150,  value: 'APPLY_ENTP_NAME',   align: 'left', sortable: false },
        { text: '승인일', width:150,   value: 'APPROVAL_TIME',     align: 'left', sortable: true  },
        { text: '제품명',   value: 'GOODS_NAME',        align: 'left', sortable: false },
        { text: '시험제목', value: 'CLINIC_EXAM_TITLE', align: 'left', sortable: true},
        { text: '단계',   width:10,  value: 'CLINIC_STEP_NAME',  align: 'left', sortable: true},
      ]
    }
  },

  methods: {
   
  },

  created() {
    const vm = this;
    const pageNo = "1";
    
    //axios 통신으로 spring에 데이터 가져오기
    vm.$axios.get('/bioInfo/getInfo?pageNo='+ pageNo, {
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
  .table {
    border-radius: 3px;
    background-clip: border-box;
    border: 1px solid rgba(0, 0, 0, 0.125);
    box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.21);
    background-color: transparent;
  }
</style>
