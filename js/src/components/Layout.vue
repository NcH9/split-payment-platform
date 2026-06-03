<template>
  <el-container style="min-height: 100vh">
    
    <el-header>
      <el-menu
        mode="horizontal"
        router
        :default-active="$route.path"
      >
        <el-menu-item index="/products">Products</el-menu-item>
        <el-menu-item index="/orders">Orders</el-menu-item>
        <el-menu-item index="/payment">Payment</el-menu-item>

        <div style="flex: 1" />

        <CartPreview/>
        <el-menu-item index="/profile">Profile</el-menu-item>
      </el-menu>
    </el-header>

    <el-main>
      <router-view />
    </el-main>

  </el-container>
</template>

<script setup>
import CartPreview from "./cart/CartPreview.vue"

</script>