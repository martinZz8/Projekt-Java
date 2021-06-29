<template>
  <div class="home">
    <b-container fluid>
      <b-row class="space"></b-row>
      <b-row align-h="center">
        <h2>Main page</h2>
      </b-row>
    </b-container>    
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    
  }
}
</script>

<style scoped>
div.home {
    color: black;
}

div.row.space {
    min-height: 90px;
}

@media all and (min-width: 768px) {
    div.row.space {
        min-height: 150px;
    }
}
</style>