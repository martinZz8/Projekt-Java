<template>
    <div class="list">
      <b-container v-if="userLogInFlag" fluid>
        <b-row align-h="center" style="min-height: 89.3vh;">
          <b-col cols="auto p-0"></b-col>
          <b-col cols="12" sm="10" md="8" lg="8" xl="6" style="background-color: rgba(155, 214, 215, 1);">
            <b-row style="height:100%;">
              <b-col cols="4" style="border-right: 1px solid black;">
                <b-row class="p-2" align-h="center" style="border-bottom: 2px dashed black;">
                  <p style="font-weight: 600;">Lists</p>
                  <b-button class="ml-auto" variant="success" size="sm" @click="addShoppingList">Add list</b-button>
                </b-row>
                <b-row v-if="lists.length>0" align-h="center" class="p-2">
                  <!--Here will be loop over all shopping lists belonging to user-->                  
                </b-row>
                <b-row v-else align-h="center" class="p-2">
                  <p>There are no lists to show</p>                
                </b-row>
              </b-col>
              <b-col cols="8">
                <b-row class="p-2" align-h="center" style="border-bottom: 2px dashed black;">
                  <p style="font-weight: 600;">Products inside list</p>
                  <b-button class="ml-auto" variant="success" size="sm" :disabled="canAddProduct" @click="addProductToList">Add product</b-button>
                </b-row>
                <b-row v-if="products.length>0" align-h="center" class="p-2">
                  <!--Here will be loop over all shopping lists belonging to user-->
                </b-row>
                <b-row v-else align-h="center" class="p-2">
                  <p>There are no products to show</p>                
                </b-row>
              </b-col>
            </b-row>
          </b-col>
          <b-col cols="auto p-0"></b-col>
        </b-row>
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
      lists: [],
      products: [],
      selected_list_id: null,
      flag: false
    }
  },
  mounted () {
    this.downloadAllLists();
  },
  computed: {
    userLogInFlag() {
        return this.$store.getters.userLoggedIn;
    },
    canAddProduct() {
      return !(this.selected_list_id != null && this.lists.length>0);
    }
  },
  methods: {
    downloadAllLists() {

    },
    downloadAllProductsInList() {
    },
    addShoppingList() {

    },
    addProductToList() {

    }
  }
}
</script>