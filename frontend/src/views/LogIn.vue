<template>
    <div>
        <b-container v-if="!userLogInFlag" fluid>
            <b-row class="space"></b-row>
            <b-row align-h="center">
                <b-col class="border border-dark rounded pt-2 pb-2" cols="10" sm="9" md="8" lg="5" xl="4" style="background-color: rgba(135, 182, 199, 0.3);">
                    <b-card v-if="error_login.error_card_flag" bg-variant="danger" text-variant="white" header="Login failed" class="text-center">
                        <b-row>
                            <b-col cols="11">
                                <b-card-text>{{error_login.error_text}}</b-card-text>
                            </b-col>
                            <b-col cols="1">
                                <b-icon style="cursor: pointer;" scale="1.5" icon="x-circle-fill" @click="error_login.error_card_flag=false"></b-icon>
                            </b-col>
                        </b-row>
                    </b-card>
                    <b-form>
                        <h2>Login form</h2>
                        <div class="text-left">
                            <b-form-group id="input-group-1" label="Your email:" label-for="input-1">
                                <b-form-input id="input-1" v-model="form.email" @keyup.enter="logIn" placeholder="Enter your email" type="email"></b-form-input>
                            </b-form-group>
                            <b-form-group id="input-group-2" label="Your password:" label-for="input-2">
                                <b-form-input id="input-2" v-model="form.password" @keyup.enter="logIn" placeholder="Enter your password" type="password"></b-form-input>
                            </b-form-group>
                            <b-button @click="logIn" variant="primary">Log in</b-button>
                        </div>
                    </b-form>
                </b-col> 
            </b-row>
        </b-container>
        <b-container v-else class="pt-5">
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
        },
        error_login: {
            error_card_flag: false,
            error_text: ''
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
                this.axios({
                    method: "POST",
                    url: 'http://localhost:8081/user/verify',
                    headers: {
                        "Content-Type": "application/json"
                    },
                    data: {
                        email: this.form.email,
                        password: this.form.password
                    }
                }).then(response => {
                    //verify if data is valid
                    if(response.status==202)
                    {
                        this.error_login.error_text = '';
                        this.error_login.error_card_flag = false;
                        //get users data
                        this.axios({
                            method: "GET",
                            url: 'http://localhost:8081/user/'+response.data.user_id,
                            headers: {
                                "Content-Type": "application/json"
                            }
                        }).then(response2 => {
                            if(response2.status==200)
                            {
                                let user = new UserStorage(response2.data.id, response2.data.email, response2.data.dob, response2.data.age, response2.data.first_name, response2.data.last_name);
                                this.$store.commit('loginUser', user);
                                this.$router.push('/lists');
                            }
                            else
                            {
                                console.log(response);
                            }
                        }).catch(() => {
                            this.error_login.error_text = "Serwer error, please try later";
                            this.error_login.error_card_flag = true;
                        });
                    }
                    else
                    {
                        console.log(response);
                    }
                }).catch(error => {
                    if (error.response.status==401)
                    {
                        this.error_login.error_text = error.response.data.description;
                        this.error_login.error_card_flag = true;
                    }
                });                
            }
            else
            {
                this.error_login.error_text = 'Podano zły schemat adresu email';
                this.error_login.error_card_flag = true;
            }
        }
        else
        {
            this.error_login.error_text = 'Nie podano hasła';
            this.error_login.error_card_flag = true;
        }
    }
  }
}
</script>

<style scoped>
div.row.space {
    min-height: 90px;
}

@media all and (min-width: 768px) {
    div.row.space {
        min-height: 150px;
    }
}
</style>