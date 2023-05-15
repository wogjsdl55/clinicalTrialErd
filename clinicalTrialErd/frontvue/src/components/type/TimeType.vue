<template>
    
    <v-text-field
        model-value="00:00:00"
        :format= "format"
        type="time"
        style="outline: solid; width: 200px; margin: 10px;"
    ></v-text-field>
</template>



<script>

    export default {
    data () {
    return {
            format: 'hh:mm',
        }
    },

  }
</script>