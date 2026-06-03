<template>
    <el-menu-item index="/cart">
        <el-badge :value="cart.length" class="cart-info">Cart items</el-badge>
    </el-menu-item>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const cart = ref([])

const loadCart = () => {
  const saved = localStorage.getItem("cart")
  cart.value = saved ? JSON.parse(saved) : []
}
onMounted(() => {
    loadCart();
})
</script>