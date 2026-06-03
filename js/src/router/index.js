import { createRouter, createWebHistory } from "vue-router"

import Login from "../components/login/Page.vue"
import Products from "../components/products/Page.vue"
import Cart from "../components/cart/List.vue"
// import Orders from "../pages/Orders.vue"
import Payment from "../components/payment/Page.vue"

const routes = [
  { path: "/", redirect: "/products" },

  { path: "/profile", component: Login },

  { path: "/products", component: Products },
  { path: "/cart", component: Cart },
//   { path: "/orders", component: Orders },
  { path: "/payment", component: Payment },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router