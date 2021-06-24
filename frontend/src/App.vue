<template>
  <div id="app" class="d-flex flex-column min-vh-100">
    <div id="nav" class="flex-grow-1">
      <b-navbar type="dark" variant="dark">
        <b-navbar-nav>
          <b-nav-item to="/">Home</b-nav-item>
          <b-nav-item v-if="userLogInFlag" to="/lists">Lists</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown v-if="userLogInFlag" text="User" right>
            <b-dropdown-item to="/account">Account</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item v-if="!userLogInFlag" to="/logIn">Log in</b-nav-item>
          <b-nav-item v-if="userLogInFlag" @click="logOut">Log out</b-nav-item>
        </b-navbar-nav>
      </b-navbar>
      <router-view/>
    </div>
    <my-footer name='Maciej Harbuz'></my-footer>
  </div>
</template>

<script>
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    'my-footer': Footer
  },
  computed: {
    userLogInFlag() {
      return this.$store.getters.userLoggedIn;
    }
  },
  methods: {
    logOut() {
      this.$store.commit('logoutUser');
      this.$router.push('/');
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 0px;
  background-color: rgba(48, 133, 134, 1);
}

#nav a{
  font-weight: bold;
  color: white;
}

#nav a:hover{
  font-weight: bold;
  color: white;
  text-decoration: underline;
}

#nav a.dropdown-item{
  font-weight: bold;
  color: black;
}

#nav a.router-link-exact-active {
  color: #42b983!important;
}
</style>
