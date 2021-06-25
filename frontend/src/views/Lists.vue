<template>
    <div class="list">
      <b-container v-if="userLogInFlag" fluid>
        <b-row align-h="center">
          <b-col cols="12" sm="10" md="8" lg="8" xl="6" class="p-0">
            <b-card v-if="error_get_array.error_card_flag" bg-variant="danger" text-variant="white" header="Failed to acquire lists" class="text-center">
              <b-row>
                <b-col cols="11">
                  <b-card-text>{{error_get_array.error_text}}</b-card-text>
                </b-col>
                <b-col cols="1">
                  <b-icon style="cursor: pointer;" scale="1.5" icon="x-circle-fill" @click="error_get_array.error_card_flag=false"></b-icon>
                </b-col>
              </b-row>
            </b-card>
          </b-col>
        </b-row>
        <b-row align-h="center" style="min-height: 89.3vh;">
          <b-col cols="auto p-0"></b-col>
          <b-col cols="12" sm="10" md="8" lg="8" xl="6" style="background-color: rgba(155, 214, 215, 1);">
            <b-row style="height:100%;">
              <b-col cols="5" style="border-right: 1px solid black;">
                <b-row class="p-2" align-h="center" style="border-bottom: 2px dashed black;">
                  <p style="font-weight: 600;">Lists</p>
                  <b-button class="ml-auto" variant="primary" size="sm" @click="$bvModal.show('add-list-modal')">Add list</b-button>
                  <b-modal id="add-list-modal" hide-footer>
                  <b-card v-if="error_add_list.error_card_flag" bg-variant="danger" text-variant="white" header="Failed to add list" class="text-center">
                    <b-row>
                      <b-col cols="11">
                        <b-card-text>{{error_add_list.error_text}}</b-card-text>
                      </b-col>
                      <b-col cols="1">
                        <b-icon style="cursor: pointer;" scale="1.5" icon="x-circle-fill" @click="error_add_list.error_card_flag=false"></b-icon>
                      </b-col>
                    </b-row>
                  </b-card>
                  <template #modal-title>
                    <h4>Add new list</h4>
                  </template>
                  <div class="d-block text-center">
                    <b-form-group id="input-list-group-1" class="text-left" label="List name:" label-for="input-list-1">
                      <b-form-input id="input-list-1" v-model="input_list_name" @keyup.enter="addList" placeholder="Enter list name" type="text"></b-form-input>
                    </b-form-group>
                  </div>
                  <b-button class="mt-3 float-left" variant="success" @click="addList">Add</b-button>
                  <b-button class="mt-3 float-right" @click="$bvModal.hide('add-list-modal')">Close</b-button>
                </b-modal>
                </b-row>
                <b-row v-if="lists.length>0" align-h="center" class="p-2">
                  <b-col cols="12">
                    <!--Here will be loop over all shopping lists belonging to user-->
                    <b-row align-h="center" v-for="(list, index) in lists" :key="index" class="list-item m-1 d-block">
                        <b-card no-body class="text-center p-2" :class="{'selected-list': selected_list_index==index}">
                          <b-row>
                            <b-col cols="10" class="item-with-pointer p-0" @click="selectList(index)">
                              {{list.name}}
                            </b-col>
                            <b-col cols="2" class="p-0">
                              <b-icon style="cursor: pointer;" icon="trash" v-b-tooltip.hover title="Delete list" @click="$bvModal.show('delete-list-modal'), saveDeleteUpdateListIndex(index)"></b-icon>
                              <br>
                              <b-icon style="cursor: pointer;" icon="pencil" v-b-tooltip.hover title="Edit list" @click="$bvModal.show('update-list-modal'), saveDeleteUpdateListIndex(index)"></b-icon>
                            </b-col>
                          </b-row>
                        </b-card>
                    </b-row>
                  </b-col>
                </b-row>
                <b-row v-else align-h="center" class="p-2">
                  <p>There are no lists to show</p>
                </b-row>
              </b-col>
              <b-col cols="7">
                <b-row class="p-2" align-h="center" style="border-bottom: 2px dashed black;">
                  <p style="font-weight: 600;">Products inside list</p>
                  <b-button class="ml-auto" variant="primary" size="sm" :disabled="disableAddProduct" @click="$bvModal.show('add-product-modal')">Add product</b-button>
                  <b-modal id="add-product-modal" hide-footer>
                  <b-card v-if="error_add_product.error_card_flag" bg-variant="danger" text-variant="white" header="Failed adding product to list" class="text-center">
                    <b-row>
                      <b-col cols="11">
                        <b-card-text>{{error_add_product.error_text}}</b-card-text>
                      </b-col>
                      <b-col cols="1">
                        <b-icon style="cursor: pointer;" scale="1.5" icon="x-circle-fill" @click="error_add_product.error_card_flag=false"></b-icon>
                      </b-col>
                    </b-row>
                  </b-card>
                  <template #modal-title>
                    <h4>Add new product to list</h4>
                  </template>
                  <div class="d-block text-center">
                    <b-form-group id="input-product-group-1" class="text-left" label="Product name:" label-for="input-product-1">
                      <b-form-input id="input-product-1" v-model="input_product_name" @keyup.enter="addProductToList" placeholder="Enter product name" type="text"></b-form-input>
                    </b-form-group>
                    <b-form-group id="input-product-group-1" class="text-left" label="Product price:" label-for="input-product-2">
                      <b-form-input id="input-product-2" v-model="input_product_price" @keyup.enter="addProductToList" placeholder="Enter product price (PLN - to two decimal places)" type="number"></b-form-input>
                    </b-form-group>
                    <b-form-group id="input-product-group-1" class="text-left" label="Product description:" label-for="input-product-3">
                      <b-form-input id="input-product-3" v-model="input_product_description" @keyup.enter="addProductToList" placeholder="Enter product description (can be mepty)" type="text"></b-form-input>
                    </b-form-group>
                    <b-form-group id="input-product-group-1" class="text-left" label="Product quantity:" label-for="input-product-4">
                      <b-form-input id="input-product-4" v-model="input_product_quantity" @keyup.enter="addProductToList" placeholder="Enter product quantity (positive integer different from 0)" type="number"></b-form-input>
                    </b-form-group>
                  </div>
                  <b-button class="mt-3 float-left" variant="success" @click="addProductToList">Add</b-button>
                  <b-button class="mt-3 float-right" @click="$bvModal.hide('add-product-modal')">Close</b-button>
                </b-modal>
                </b-row>
                <b-row v-if="products.length>0" align-h="center" class="p-2">
                  <b-col cols="12">
                    <!--Here will be loop over all shopping lists belonging to user-->
                    <b-row align-h="center" v-for="(product, index) in products" :key="index" class="product-item m-1 d-block">
                      <b-card no-body class="text-center p-1">

                        <b-row>
                          <b-col cols="11" class="p-0">
                            <div class="inline">
                              <span class="underline">Product:</span> {{product.name}}
                            </div>
                            <br>
                            <div class="inline">
                              <span class="underline">Price:</span> {{product.price}} PLN
                            </div>
                            <br>
                            <div class="inline">
                              <span class="underline">Description:</span> {{product.description}}
                            </div>
                            <br>
                            <div class="inline">
                              <span class="underline">Quantity:</span> {{product.quantity}}
                            </div>
                          </b-col>
                          <b-col cols="1" class="pl-0 pr-3">
                            <b-icon style="cursor: pointer;" icon="trash" v-b-tooltip.hover title="Delete product" @click="$bvModal.show('delete-product-modal'), saveDeleteUpdateProductIndex(index)"></b-icon>
                            <br>
                            <b-icon style="cursor: pointer;" icon="pencil" v-b-tooltip.hover title="Edit product" @click="$bvModal.show('update-product-modal'), saveDeleteUpdateProductIndex(index)"></b-icon>
                          </b-col>
                        </b-row>
                      </b-card>
                    </b-row>
                  </b-col>
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
      selected_list_index: null,
      selected_product_index: null,
      input_list_name: '',
      input_product_name: '',
      input_product_price: '',
      input_product_description: '',
      input_product_quantity: '',
      delete_update_list_index: '',
      delete_update_product_index: '',
      error_get_array: {
        error_card_flag: false,
        error_text: ''
      },
      error_add_list: {
        error_card_flag: false,
        error_text: ''
      },
      error_add_product: {
        error_card_flag: false,
        error_text: ''
      }
    }
  },
  mounted () {
    this.downloadAllLists();
  },
  computed: {
    userLogInFlag() {
        return this.$store.getters.userLoggedIn;
    },
    disableAddProduct() {
      return !(this.selected_list_index != null && this.lists.length>0);
    }
  },
  methods: {
    selectList(index) {
      this.selected_list_index = index;
      this.downloadAllProductsInList(this.lists[index].id);
    },
    saveDeleteUpdateListIndex(list_index) {
      this.delete_update_list_index = list_index;
    },
    saveDeleteUpdateProductIndex(product_index) {
      this.delete_update_product_index = product_index;
    },
    downloadAllLists() {
      this.axios({
        method: "GET",
        url: 'http://localhost:8081/user/all_shopping_lists/'+this.$store.state.user.email,
        headers: {
            "Content-Type": "application/json"
        }
      }).then(response => {
        if(response.status==200)
        {
          this.lists = response.data;
          this.error_get_array.error_card_flag = false;
        }
      }).catch(error => {
        console.log(error.response);
        this.error_get_array.error_text = 'There is problem with server connectivity - please try again later';
        this.error_get_array.error_card_flag = true;
      });
    },
    downloadAllProductsInList(list_id) {
      this.axios({
        method: "GET",
        url: 'http://localhost:8081/shopping_list/all_products/'+list_id,
        headers: {
            "Content-Type": "application/json"
        }
      }).then(response => {
        if(response.status==200)
        {
          this.products = response.data;
          this.error_get_array.error_card_flag = false;
        }
      }).catch(error => {
        console.log(error.response);
        this.error_get_array.error_text = 'There is problem with server connectivity - please try again later';
        this.error_get_array.error_card_flag = true;
      });
    },
    addList() {
      if(this.input_list_name!='')
      {
        this.axios({
          method: "POST",
          url: 'http://localhost:8081/shopping_list/add',
          headers: {
              "Content-Type": "application/json"
          },
          data: {
            name: this.input_list_name,
            user_id: this.$store.state.user.id
          }
        }).then(response => {
          if(response.status==201)
          {
            this.lists.push({id: response.data.id, name: response.data.name});
            this.error_get_array.error_card_flag = false;
            this.error_add_list.error_card_flag = false;
            this.$bvModal.hide('add-list-modal');
          }
        }).catch(error => {
          console.log(error.response);
          this.error_get_array.error_text = 'There is problem with server connectivity - please try again later';
          this.error_get_array.error_card_flag = true;
        });
      }
      else
      {
        this.error_add_list.error_text = 'Name of list cannot be empty';
        this.error_add_list.error_card_flag = true;
      }
    },
    addProductToList() {
      let added_product = null;
      if(this.input_product_name!='')
      {
        let product_quantity = parseInt(this.input_product_quantity);
        if(!isNaN(product_quantity) && product_quantity>0)
        {
          let product_price = parseFloat(this.input_product_price);
          console.log(product_price)
          if(!isNaN(product_price) && product_price>=0)
          {
            let rounded_price = Math.round((product_price + Number.EPSILON) * 100) / 100;
            console.log("Rounded price: "+rounded_price);
            this.axios({
              method: "POST",
              url: 'http://localhost:8081/product/add',
              headers: {
                  "Content-Type": "application/json"
              },
              data: {
                name: this.input_product_name,
                price: rounded_price,
                description: this.input_product_description
              }
            }).then(response => {
              if(response.status==201)
              {
                added_product = {id: response.data.id, name: response.data.name, price: response.data.price, description: response.data.description};
                this.axios({
                  method: "POST",
                  url: 'http://localhost:8081/products_in_lists/add',
                  headers: {
                    "Content-Type": "application/json"
                  },
                  data: {
                    shopping_list_id: this.lists[this.selected_list_index].id,
                    product_id: added_product.id,
                    quantity: product_quantity
                  }
                }).then(response2 => {
                  if(response2.status==201)
                  {
                    console.log("Product added successfully");
                    this.products.push(added_product);
                    this.error_add_product.error_card_flag = false;
                    this.error_get_array.error_card_flag = false;
                    this.$bvModal.hide('add-product-modal');
                  }
                }).catch(error => {
                  console.log(error.response);
                  this.error_get_array.error_text = 'There is problem with server connectivity - please try again later';
                  this.error_get_array.error_card_flag = true;
                  this.$bvModal.hide('add-product-modal');
                });
              }
            }).catch(error => {
              console.log(error.response);
              this.error_get_array.error_text = 'There is problem with server connectivity - please try again later';
              this.error_get_array.error_card_flag = true;
              this.$bvModal.hide('add-product-modal');
            });
          }
          else
          {
            this.error_add_product.error_text = 'Entered price is not a number or is not greater or equal than 0';
            this.error_add_product.error_card_flag = true;
          }
        }
        else
        {
          this.error_add_product.error_text = 'Quantity is not a number or is not greater than 0';
          this.error_add_product.error_card_flag = true;
        }
      }
      else
      {
        this.error_add_product.error_text = 'Name of product cannot be empty';
        this.error_add_product.error_card_flag = true;
      }
    },
    deleteList(list_index) {
      alert("Delete list: "+list_index);

      //After list delete in DB
      /*
      if(this.selected_list_index==list_index)
      {
        this.products=[];
      }
      this.lists.splice(list_index, 1);
      */
    },
    updateList(list_index) {
      alert("Update list: "+list_index);
    },
    deleteProduct(product_index) {
      alert("Delete product: "+product_index);

    },
    updateProduct(product_index) {
      alert("Update product: "+product_index);

    }
  }
}
</script>

<style scoped>
  div.row.list-item > div.card, div.row.product-item > div.card {
    background-color: rgba(14, 49, 10, 1);
    color: white;
  }

  div.item-with-pointer {
    cursor: pointer;
  }

  div.row.list-item > div.card:hover, div.row.product-item > div.card:hover {
    background-color: rgba(116, 235, 52, 1);
    color: black;
  }
  .selected-list {
    background-color: rgba(116, 235, 52, 1)!important;
    color: black!important;
  }

  span.underline {
    text-decoration: underline;
  }

  div.inline {
    display: inline;
  }
</style>>