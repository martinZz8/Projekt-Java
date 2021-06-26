<template>
    <div class="register">
        <b-container v-if="!userLogInFlag" fluid>
            <b-row class="space"></b-row>
            <b-row align-h="center">
                <b-col class="border border-dark rounded pt-2 pb-2" cols="10" sm="9" md="8" lg="5" xl="4" style="background-color: rgba(152, 217, 211, 0.8);">
                    <b-card v-if="error_register.error_card_flag" bg-variant="danger" text-variant="white" header="Register failed" class="text-center">
                        <b-row>
                            <b-col cols="11">
                                <b-card-text>{{error_register.error_text}}</b-card-text>
                            </b-col>
                            <b-col cols="1">
                                <b-icon style="cursor: pointer;" scale="1.5" icon="x-circle-fill" @click="error_register.error_card_flag=false"></b-icon>
                            </b-col>
                        </b-row>
                    </b-card>
                    <b-card v-if="success_register.success_card_flag" bg-variant="success" text-variant="white" header="Register failed" class="text-center">
                        <b-row>
                            <b-col cols="11">
                                <b-card-text>{{success_register.success_text}}</b-card-text>
                            </b-col>
                            <b-col cols="1">
                                <b-icon style="cursor: pointer;" scale="1.5" icon="x-circle-fill" @click="success_register.success_card_flag=false"></b-icon>
                            </b-col>
                        </b-row>
                    </b-card>
                    <b-form>
                        <h2>Register form</h2>
                        <div class="text-left">
                            <b-form-group id="input-group-1" label="Your email:" label-for="input-1">
                                <b-form-input id="input-1" v-model="form.email" @keyup.enter="register" placeholder="Enter your email" type="email"></b-form-input>
                            </b-form-group>
                            <b-form-group id="input-group-2" label="Your first name:" label-for="input-2">
                                <b-form-input id="input-2" v-model="form.first_name" @keyup.enter="register" placeholder="Enter your first name (can be empty)" type="text"></b-form-input>
                            </b-form-group>
                            <b-form-group id="input-group-3" label="Your last name:" label-for="input-3">
                                <b-form-input id="input-3" v-model="form.last_name" @keyup.enter="register" placeholder="Enter your last name (can be empty)" type="text"></b-form-input>
                            </b-form-group>
                            <b-form-group id="input-group-4" label="Your date of birth:" label-for="input-4">
                                <b-form-input id="input-4" v-model="form.dob" @keyup.enter="register" placeholder="dd.mm.yyyy" type="date"></b-form-input>
                            </b-form-group>
                            <b-form-group id="input-group-5" label="Your password:" label-for="input-5">
                                <b-form-input id="input-5" v-model="form.password" @keyup.enter="register" placeholder="Enter your password" type="password"></b-form-input>
                            </b-form-group>
                            <b-form-checkbox v-model="terms_accept_checkbox">I accept the terms and conditions</b-form-checkbox>
                            <b-row class="p-2">
                                <b-button @click="register" variant="primary">Register</b-button>
                                <span class="ml-auto">Already have account? <router-link style="color: black;" to="/logIn">Log in</router-link></span>
                            </b-row>
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
export default {
  name: 'LogIn',
  data: () => {
    return {
        form: {
            email: '',
            first_name: '',
            last_name: '',
            dob: '',
            password: ''
        },
        terms_accept_checkbox: false,
        error_register: {
            error_card_flag: false,
            error_text: ''
        },
        success_register: {
            success_card_flag: false,
            success_text: ''
        }
    }
  },
  computed: {
    userLogInFlag() {
        return this.$store.getters.userLoggedIn;
    }
  },
  watch: {
    terms_accept_checkbox: function(val) {
        if(val==true)
            this.error_register.error_card_flag=false;
    }
  },
  methods: {
    register () {
        // eslint-disable-next-line no-useless-escape
        const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        if (re.test(String(this.form.email).toLowerCase()))
        {
            if(this.form.password != '')
            {
                if(this.form.dob!='')
                {
                    let dob_array = this.form.dob.split('-');
                    let dob_Date = new Date(dob_array[0], (parseInt(dob_array[1])-1).toString(10), dob_array[2]);
                    if(dob_Date < new Date())
                    {
                        if(this.terms_accept_checkbox)
                        {
                            this.axios({
                                method: "POST",
                                url: 'http://localhost:8081/user/add',
                                headers: {
                                    "Content-Type": "application/json"
                                },
                                data: {
                                    email: this.form.email,
                                    first_name: this.form.first_name,
                                    last_name: this.form.last_name,
                                    dob: this.form.dob,
                                    password: this.form.password,
                                    blocked: "0"
                                }
                            }).then(response => {
                                if(response.status==201)
                                {
                                    this.success_register.success_text="You have successfully registered. Now you can log in.";
                                    this.error_register.error_card_flag = false;
                                    this.success_register.success_card_flag = true;                                    
                                }
                            }).catch(error => {
                                if(error.response.status==403)
                                {
                                    this.error_register.error_text = 'User with this email exists - type other email';
                                    this.error_register.error_card_flag = true;
                                }
                                else
                                {
                                    console.log(error.response);
                                    this.error_register.error_text = 'Server error, please try later';
                                    this.error_register.error_card_flag = true;
                                }                                
                            });
                            
                        }
                        else
                        {
                            this.error_register.error_text = 'Accept terms and conditions';
                            this.error_register.error_card_flag = true;
                        }
                    }
                    else
                    {
                        this.error_register.error_text = 'Selected date of birth is invalid';
                        this.error_register.error_card_flag = true;
                    }
                }
                else
                {
                    this.error_register.error_text = 'Select your date of birth';
                    this.error_register.error_card_flag = true;
                }
            }
            else
            {
                this.error_register.error_text = 'Password input is empty';
                this.error_register.error_card_flag = true;
            }
        }
        else
        {
            this.error_register.error_text = 'Given email schema is not valid';
            this.error_register.error_card_flag = true;
        }
    }
  }
}
</script>

<style scoped>
div.register {
    color: black;
}

div.row.space {
    min-height: 60px;
}

@media all and (min-width: 768px) {
    div.row.space {
        min-height: 100px;
    }
}
</style>>
