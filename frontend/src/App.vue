<template>
  <div id="app" class="d-flex flex-column min-vh-100">
    <div id="nav" class="wrapper flex-grow-1">
      <router-link to="/">Home</router-link> |
      <router-link to="/lists">Lists</router-link> |
      <router-link v-if="!userLogInFlag" to="/logIn">Log in</router-link> |
      <a v-if="userLogInFlag" @click="logOut">Log out</a>
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
  padding: 30px;
}

#nav a{
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
