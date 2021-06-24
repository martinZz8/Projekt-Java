import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    user: {
      id: null,
      email: null,
      dob: null,
      age: null,
      firstName: null,
      lastName: null
    },
    administrator: {
      id: null,
      email: null
    }
  },
  getters: {
    userLoggedIn(state) {
      return (state.user.id != null && state.user.email != null);
    },
    administratorLoggedIn(state) {
      return (state.administrator.id != null && state.administrator.email != null);
    }
  },
  mutations: {
    loginUser(state, u) {
      state.user.id=u.id;
      state.user.email=u.email;
      state.user.dob = u.dob;
      state.user.age = u.age;
      state.user.firstName = u.firstName;
      state.user.lastName = u.lastName;
    },
    logoutUser(state) {
      state.user.id = null;
      state.user.email = null;
      state.user.dob = null;
      state.user.age = null;
      state.user.firstName = null;
      state.user.lastName = null;
    },
    loginAdministrator(state, u) {
      state.administrator.id=u.id;
      state.administrator.email=u.email;
    },
    logoutAdministrator(state) {
      state.administrator.id = null;
      state.administrator.email = null;
    },
  },
  actions: {
  },
  modules: {
  }
})
