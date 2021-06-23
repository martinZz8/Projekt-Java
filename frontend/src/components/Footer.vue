<template>
    <footer>
        <p id="inscription">Copyright &copy; - {{new Date().getFullYear()}} {{name}}</p>
    </footer>
</template>

<script>
export default {
  props: {
    name: String
  }
}
</script>

<style scoped>
footer{
    background: #222;
    padding: 6px;
}
p#inscription{
    margin: 0px;
    padding-top: 5px;
    padding-bottom: 5px;
    color: white;
    text-align: center;
}
</style>