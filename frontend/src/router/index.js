import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Lists from '../views/Lists.vue'
import LogIn from '../views/LogIn.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/lists',
    name: 'lists',
    component: Lists
  },
  {
    path: '/logIn',
    name: 'logIn',
    component: LogIn
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
