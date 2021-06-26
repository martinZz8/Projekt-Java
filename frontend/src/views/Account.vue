<template>
  <div class="account">
    <b-container v-if="userLogInFlag" fluid>
        <b-row class="space"></b-row>
        <b-row align-h="center">
            <b-col cols="10" sm="9" md="7" lg="5" xl="3">
                <b-card title="Account informations">
                    <b-card-text style="font-weight: 500;">
                        <p>Email: {{account.email}}</p>
                        <p>First name: {{account.firstName}}</p>
                        <p>Last name: {{account.lastName}}</p>
                        <p>Day of birth: {{account.dob}}</p>
                        <p>Age: {{account.age}}</p>
                    </b-card-text>
                </b-card>
            </b-col>
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
  name: 'Account',
  data: () => {
    return {
        account: {
            email: null,
            firstName: null,
            lastName: null,
            dob: null,
            age: null
        }        
    }
  },
  computed: {
    userLogInFlag() {
        return this.$store.getters.userLoggedIn;
    }
  },
  mounted () {
    if (this.$store.getters.userLoggedIn)
    {
        this.account.email = this.$store.state.user.email;
        this.account.firstName = this.$store.state.user.firstName;
        this.account.lastName = this.$store.state.user.lastName;
        this.account.dob = this.$store.state.user.dob;
        this.account.age = this.$store.state.user.age;
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
        min-height: 100px;
    }
}
</style>