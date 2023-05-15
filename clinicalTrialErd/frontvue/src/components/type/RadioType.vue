<template>
    <v-radio-group
    
    inline
    style="margin: 10px;"
    >
    <v-radio label="Option1" value="radio-1" ></v-radio>
    <v-radio label="Option2" value="radio-2" ></v-radio>
</v-radio-group>
</template>



<script>
    export default {
    data() {
        return {
            column: null,
            inline: null,      
        }
    },

  }
</script>