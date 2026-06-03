<template>
  <div class="container">
    <el-page-header content="Products" />

    <el-alert
      v-if="error"
      :title="error"
      type="error"
      show-icon
      class="mb"
    />

    <el-skeleton v-if="loading" :rows="6" animated />

    <el-row v-else :gutter="20">
      <el-col
        v-for="product in products"
        :key="product.id"
        :xs="24"
        :sm="12"
        :md="8"
        :lg="6"
      >
        <el-card shadow="hover" class="card">
          <img :src="product.image" class="image" />

          <h3 class="title">{{ product.title }}</h3>

          <p class="price">${{ product.price }}</p>

          <el-button
            type="primary"
            @click="addToCart(product)"
          >
            Add to cart
          </el-button>
        </el-card>
      </el-col>
    </el-row>

    <el-divider />

    <el-badge :value="cart.length" class="cart-info">
      Cart items
    </el-badge>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"
import { ElMessage } from "element-plus"

const products = ref([])
const loading = ref(false)
const error = ref(null)

const cart = ref([])

const loadCart = () => {
  const saved = localStorage.getItem("cart")
  cart.value = saved ? JSON.parse(saved) : []
}

const saveCart = () => {
  localStorage.setItem("cart", JSON.stringify(cart.value))
}

const addToCart = (product) => {
  const existing = cart.value.find(p => p.id === product.id)

  if (existing) {
    existing.qty += 1
  } else {
    cart.value.push({ ...product, qty: 1 })
  }

  saveCart()

  ElMessage({
    message: "Added to cart",
    type: "success",
  })
}

const fetchProducts = async () => {
  loading.value = true
  error.value = null

  try {
    const res = await axios.get("https://fakestoreapi.com/products")
    products.value = res.data
  } catch (e) {
    error.value = "Failed to load products"
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchProducts()
  loadCart()
})
</script>

<style scoped>
.container {
  padding: 20px;
}

.card {
  margin-bottom: 20px;
}

.image {
  width: 100%;
  height: 180px;
  object-fit: contain;
  margin-bottom: 10px;
}

.title {
  font-size: 14px;
  height: 40px;
  overflow: hidden;
}

.price {
  font-weight: bold;
  color: #67c23a;
}

.mb {
  margin-bottom: 20px;
}

.cart-info {
  margin-top: 20px;
}
</style>