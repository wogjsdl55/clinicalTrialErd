<template>
    <div>
      <v-btn color="secondary" class="ma-2" @click="dialog = true">수정</v-btn>
      <v-dialog v-model="dialog" width="300">
        <v-card>
          <v-card-title>수정</v-card-title>
          <v-card-actions>
            <v-btn color="primary" variant="text" @click="dialog = false">취소</v-btn>
            <v-btn color="green-darken-1" variant="text" @click="$emit('deleteButton')">확인</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
</template>



<script>
    export default {
    props: ["results"],
    data () { return { dialog: false } },
  }
</script>