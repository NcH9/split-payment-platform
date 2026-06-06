import { createRouter, createWebHistory } from "vue-router"

const routes = [
  { 
    path: "/", 
    redirect: "/products" 
  },
  {
    path: "/profile",
    component: () => import("../components/login/Page.vue")
  },
  {
    path: "/products",
    component: () => import("../components/products/Page.vue")
  },
  {
    path: "/cart", 
    component: () => import("../components/cart/Page.vue"),
    meta: {
      requiresAuth: true
    }
  },
  { 
    path: "/orders", 
    component: () => import("../components/orders/Page.vue"),
    meta: {
      requiresAuth: true
    }
  },
  { 
    path: "/payment", 
    component: () => import("../components/payments/Page.vue"),
    meta: {
      requiresAuth: true
    }
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  const isLoggedIn = !!localStorage.getItem("authData")

  if (to.meta.requiresAuth && !isLoggedIn) {
    next("/login")
    return
  }

  next()
})

export default router