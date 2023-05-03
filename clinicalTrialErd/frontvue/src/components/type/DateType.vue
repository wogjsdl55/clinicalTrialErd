<template>
    
    <datepicker placeholder="Select Date" />
</template>



<script>
import Datepicker from 'vuejs-datepicker';

    export default {
    data: () => ({
        
      menu: false,
      modal: false,
      isActive: false,
      activatorNode: document.createElement("div"),
      menu2: false
    }),
    components: {
        Datepicker
    }

  }
</script>