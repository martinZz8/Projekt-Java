<template>
    <div class="list">
        <p>This is List site</p>
        <p>{{userLogInFlag}}</p>
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