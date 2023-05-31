<template>
    <div>
        <datepicker 
            v-model= "datePick"
            :format= "format"
            :language="ko"
            :change="changeDate()"
            style="outline: solid; width: 200px; margin: 10px;"
        />
        <div v-show="false">{{ this.results.group_data }}</div>
    </div>
</template>



<script>
import { ko } from 'vuejs-datepicker/dist/locale';
import Datepicker from 'vuejs-datepicker';
    export default {
    props: ["results"],

    data () {
        return {
            ko :ko,
            pickDate: '',
            datePick: '',
            format: 'yyyy-MM-dd',
            resultData: [],
            resultsEtc1: this.results.group_etc1,
            resultsEtcs: eval(this.results.group_etc),
        }
    },
    created() {
        const vm = this;
        vm.datePick = vm.results.group_data;
        vm.$EventBus.$on('datePick', function(datePick){ vm.datePick = datePick; });
    },
    methods: {
        changeDate(){
            const vm = this;
            vm.resultData[0] = vm.results.group_etc1;

            if(vm.results.group_data !=""){
                vm.datePick = this.results.group_data;
                this.results.group_data = "";
            }else {
                vm.resultData[1] = vm.datePick;
            }
            vm.$EventBus.$emit('pickDate', vm.resultData)
        }
    },

    components: { Datepicker }
  }
</script>