import Vuex from "vuex";
import Vue from 'vue';

// 데이터 유지를 위해 import

Vue.use(Vuex);

// store 생성
export default new Vuex.Store({
    state: {
        token: null,
        user_email: null
    },
    //state값들을 리턴하기 위한 getter 로직
    getters: {
        isLogin(state){
            return state.token == null ? false : true ;
        },
        userEmail(state){
            console.log(state);
            return state.userEmail;
        }
    },
    //state를 변화시키는로직
    mutations: {  // commit 으로 부를 수 있다.
        setToken(state, _token){
            state.token = _token;
        },
        setUserEmail(state, _userEmail){
            state.userEmail = _userEmail;
        }
    },
    // mutations를 호출하는 서비스 로직으로 dispatch를 사용해서 호출한다.
    actions: { // dispatch 로 부를 수 있다.
        setToken:({commit} , _token) => {
            commit('setToken' , _token);
        },
        user_email:({commit} , _userEmail) => {
            commit('setUserEmail' , _userEmail);
        }
    },

})