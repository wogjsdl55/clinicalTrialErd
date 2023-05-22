<template>
    <div>
     <datepicker 
        v-model= "datePick"
        :format= "format"
        :language="ko"
        :change="changeDate()"
        style="outline: solid; width: 200px; margin: 10px;"
    />

    </div>
</template>



<script>
import { ko } from 'vuejs-datepicker/dist/locale';
import Datepicker from 'vuejs-datepicker';
    export default {
    props: ["results"],
    data () {
    return {
        resultDate: [],
        datePick: '',
        resultData: this.results.group_data,
        pickDate: '',
        format: 'yyyy-MM-dd',
        ko :ko
        }
    },
    created() {
        const vm = this;
        vm.datePick = vm.results.group_data;
        vm.$EventBus.$on('datePick', function(datePick){ vm.datePick = datePick; });
    },
    updated(){
        const vm = this;
        vm.timeData = this.results.group_data;
    },
    methods: {
        changeDate(){
            this.resultDate[0] = this.results.group_etc1;
            this.resultDate[1] = this.datePick;
            
            this.$EventBus.$emit('pickDate', this.resultDate)
        }
    },
    components: {
        Datepicker
    }

  }
</script>