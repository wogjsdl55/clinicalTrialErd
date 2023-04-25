<template>
  <div>
    <template v-if="!$route.meta.allowAnonymous">
      <v-app id="inspire">
        <div class="app-container">
          <toolbar @toggleNavigationBar="drawer = !drawer"/>
          <navigation :toggle="drawer"/>
          <v-content>
            <breadcrumbs />
            <router-view/>
            <page-footer />
          </v-content>
        </div>
      </v-app>
    </template>
    <template v-else>
      <transition>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </transition>
    </template>
  </div>
</template>

<script>
export default {
  name: 'App',
  created() {
    //메인 컴포넌트를 렌더링하면서 토큰체크
    // let token = this.$store.getters.getToken;
    // if (token.access == null && token.refresh == null) { //다 없으면 로그인 페이지로
    //   //이미 로그인 페이지가 떠있는 상태에서 새로 고침하면 중복 에러 떠서 이렇게 처리함
    //   this.$router.push({name: 'Login'}).catch(() => {}); 
    // }
  },
  data() {
    return {
      drawer: true
    }
  }
}
</script>

<style>
  .v-btn:hover:before{
    color: transparent !important;
  }
</style>
