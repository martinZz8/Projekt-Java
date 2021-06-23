import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    user: {
      email: null,
      dob: null,
      firstName: null,
      lastName: null
    },
    administrator: {
      email: null
    }
  },
  getters: {
    userLoggedIn(state) {
      return (state.user.email != null);
    },
    administratorLoggedIn(state) {
      return (state.administrator.email != null);
    }
  },
  mutations: {
    loginUser(state, u) {
      state.user.email=u.email;
      state.user.dob = u.dob;
      state.user.firstName = u.firstName;
      state.user.lastName = u.lastName;
    },
    logoutUser(state) {
      state.user.email = null;
      state.user.dob = null;
      state.user.firstName = null;
      state.user.lastName = null;
    },
    loginAdministrator(state, u) {
      state.administrator.email=u.email;
    },
    logoutAdministrator(state) {
      state.administrator.email = null;
    },
  },
  actions: {
  },
  modules: {
  }
})
