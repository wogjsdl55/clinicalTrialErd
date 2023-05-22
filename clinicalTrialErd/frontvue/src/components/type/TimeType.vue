<template>
    <div>
    <v-text-field
        model-value="00:00:00"
        v-model="timeData"
        :format= "format"
        type="time"
        style="outline: solid; width: 200px; margin: 10px;"
    ></v-text-field>
        {{   }}
    </div>
</template>


<script>
    export default {
    props: ["results"],
    data () {
    return {
        dateCheck: [],
        resultData: this.results.group_data,
        timeData: '',
        format: 'hh:mm',
        }
    },
    created(){
        const vm = this;
        vm.timeData = vm.results.group_data;
    },
    updated(){ 
        const vm = this;
        vm.timeData = vm.results.group_data;
        vm.dateCheck[0] = vm.results.group_etc1;
        vm.dateCheck[1] = vm.timeData;

        vm.$emit('dataCheck', vm.dateCheck);
    },

  }
</script>