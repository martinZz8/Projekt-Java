<template>
    <div>
        <b-container v-if="!userLogInFlag" fluid>
            <b-row align-h="center">            
                <b-col class="border mt-5 pt-2 pb-2" cols="10" sm="9" md="8" lg="5" xl="4">
                    <b-form >
                        <h2>Login form</h2>
                        <div class="text-left">
                            <b-form-group id="input-group-1" label="Your email:" label-for="input-1">
                                <b-form-input id="input-1" v-model="form.email" placeholder="Enter your email" type="email" required></b-form-input>
                            </b-form-group>
                            <b-form-group id="input-group-2" label="Your password:" label-for="input-2">
                                <b-form-input id="input-2" v-model="form.password" placeholder="Enter your password" type="text" required></b-form-input>
                            </b-form-group>
                            <b-button @click="logIn" variant="primary">Log in</b-button>
                        </div>
                    </b-form>
                </b-col> 
            </b-row>
        </b-container>
        <b-container v-else>
            <p>You are already logged in.</p>
        </b-container>
    </div>
</template>

<script>
import UserStorage from '../models/UserStorage'

export default {
  name: 'LogIn',
  data: () => {
    return {
        form: {
            email: '',
            password: ''
        }
    }
  },
  computed: {
    userLogInFlag() {
        return this.$store.getters.userLoggedIn;
    }
  },
  methods: {
    logIn() {
        if(this.form.password != '')
        {
            // eslint-disable-next-line no-useless-escape
            const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            if (re.test(String(this.form.email).toLowerCase())) {
                //let comp = this;
                //axios localhost:8081/verify with body user (JSON)
                //if verify is proper, save user in storage (with data retrieved from backend-api)
                let user = new UserStorage(this.form.email, "1995-06-03", "Adam", "Małysz");
                this.$store.commit('loginUser', user);
                this.$router.push('/lists');
            }
            else
            {
                alert("Podano zły schemat emaila");
            }
        }
        else
        {
            alert("Nie podano hasła");
        }
    }
  }
}
</script>