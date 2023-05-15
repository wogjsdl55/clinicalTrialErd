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
    data () {
    return {
        datePick: '',
        resultDate: '',
        pickDate: '',
        format: 'yyyy-MM-dd',
        ko :ko
        }
    },
    created() {
        const vm = this;
        vm.$EventBus.$on('datePick', function(datePick){ vm.datePick = datePick; });
    },
    methods: {
        changeDate(){
            this.$EventBus.$emit('pickDate', this.datePick)
        }
    },
    components: {
        Datepicker
    }

  }
</script>