<template>
    <div>
    <v-radio-group
    inline
    style="outline: solid; margin: 10px; width: 47rem;"
    v-model="item"
    >
        <v-radio name="radio" v-for="(resultsEtc, index) in resultsEtcs" v-bind:key="index" :label=resultsEtcs[index] :value="resultsEtcs[index]" ></v-radio>
    </v-radio-group>
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