<template>
    <div>
      <v-btn
        color="secondary"
        class="ma-2"
        @click="dialog = true"
      >
        삭제
      </v-btn>
      <v-dialog
        v-model="dialog"
        width="300"
      >
        <v-card>
          <v-card-title>알림</v-card-title>
          <v-card-text>
            삭제 하시겠습니까?
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" variant="text" @click="dialog = false">
              취소
            </v-btn>
            <v-btn color="green-darken-1" variant="text" @click="$emit('deleteButton')">
            확인
          </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
</template>



<script>
    export default {
    props: ["results"],
    data () {
        return {
            dialog: false,
        }
    },
  }
</script>