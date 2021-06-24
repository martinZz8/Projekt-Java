<template>
    <div class="list">
      <b-container v-if="userLogInFlag" fluid>
          <p>This is List site</p>
          <p>{{userLogInFlag}}</p>
        </b-container>
        <b-container v-else class="pt-5">
          <p>You are not able to see this site.</p>
          <router-link style="color: black;" to="/logIn">Log in</router-link> to view this content.
        </b-container>
    </div>
</template>

<script>

export default {
  name: 'Lists',
  data: () => {
    return {

    }
  },
  mounted () {
      
  },
  computed: {
    userLogInFlag() {
        return this.$store.getters.userLoggedIn;
    }
  },
  methods: {
    
  }
}
</script>