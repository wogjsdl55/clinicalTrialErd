<template>
    <div>
    <v-radio-group
    inline
    style="outline: solid; margin: 10px; width: 47rem;"
    v-model="item"
    v-bind="item"
    >
        <v-radio name="radio" v-for="(resultsEtc, index) in resultsEtcs" v-bind:key="index" :label=resultsEtcs[index] :value="resultsEtcs[index]" ></v-radio>
    </v-radio-group>
    
    </div>
</template>



<script>
    export default {
    props: ["results"],
    data () {
        return {
            item: [],
            dataCheck: [],
            resultsEtc1: this.results.group_etc1,
            resultsEtcs: eval(this.results.group_etc),
        }
    },
    created(){
        const vm = this;
        vm.item = vm.results.group_data;
    },
    updated(){ 
        //this.item = this.results.group_data;
        this.dataCheck[0] = this.resultsEtc1;
        this.dataCheck[1] = this.item;

        this.$emit('dataCheck', this.dataCheck);
    },
    methods:{ 
    }

  }
</script>