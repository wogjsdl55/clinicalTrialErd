<template>
    <div>
    <v-text-field
        model-value="00:00:00"
        v-model="item"
        type="time"
        style="outline: solid; width: 200px; margin: 10px;"
    ></v-text-field>
    <div v-show="false">{{  this.results.group_data }}</div>
    </div>
</template>


<script>
    export default {
    props: ["results"],
    data () {
    return {
        dataCheck: [],
        item: this.results.group_data,
        resultsEtc1: this.results.group_etc1,
        resultsEtcs: eval(this.results.group_etc),
        }
    },
    created(){
        const vm = this;
        vm.item = vm.results.group_data;
        vm.$set(vm.dataCheck, 0, vm.results.group_etc1);
        vm.$set(vm.dataCheck, 1, vm.results.group_data);
        vm.$emit('dataCheck', vm.dataCheck);
    },
    beforeUpdate(){ 
        const vm = this;
        if(vm.results.group_data != ""){
            vm.item = vm.results.group_data;
            vm.results.group_data = "";
        }

        vm.$set(vm.dataCheck, 0, vm.resultsEtc1);
        vm.$set(vm.dataCheck, 1, vm.item);
        vm.$emit('dataCheck', vm.dataCheck);
    }
  }
</script>