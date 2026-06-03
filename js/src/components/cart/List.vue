<template>
  <div>
    <h2>🛒 Cart</h2>

    <el-empty v-if="cart.length === 0" description="Cart is empty" />

    <el-table v-else :data="cart" style="width: 100%">
      
      <el-table-column label="Product">
        <template #default="{ row }">
          <div class="product">
            <img :src="row.image" class="img" />
            <span>{{ row.title }}</span>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="Price">
        <template #default="{ row }">
          ${{ row.price }}
        </template>
      </el-table-column>

      <el-table-column label="Qty">
        <template #default="{ row }">
          <el-input-number
            v-model="row.qty"
            :min="1"
            @change="saveCart"
          />
        </template>
      </el-table-column>

      <el-table-column label="Total">
        <template #default="{ row }">
          ${{ (row.price * row.qty).toFixed(2) }}
        </template>
      </el-table-column>

      <el-table-column label="Actions">
        <template #default="{ row }">
          <el-button type="danger" @click="removeItem(row.id)">
            Remove
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <div v-if="cart.length > 0" class="summary">
      <h3>Total: ${{ totalPrice }}</h3>

      <el-button type="primary" size="large" @click="createPayment">
        Create payment
      </el-button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue"
import { useRouter } from "vue-router"
import { ElMessage } from "element-plus"

const router = useRouter()
const cart = ref([])

const loadCart = () => {
  const data = localStorage.getItem("cart")
  cart.value = data ? JSON.parse(data) : []
}

const saveCart = () => {
  localStorage.setItem("cart", JSON.stringify(cart.value))
}

const removeItem = (id) => {
  cart.value = cart.value.filter(item => item.id !== id)
  saveCart()

  ElMessage({
    type: "info",
    message: "Item removed",
  })
}

const totalPrice = computed(() => {
  return cart.value
    .reduce((sum, item) => sum + item.price * item.qty, 0)
    .toFixed(2)
})

const createPayment = () => {
  if (cart.value.length === 0) return

  const amount = totalPrice.value

  router.push({
    path: "/payment",
    query: {
      amount,
    },
  })

  ElMessage({
    type: "success",
    message: "Redirecting to payment...",
  })
}

onMounted(() => {
  loadCart()
})
</script>

<style scoped>
.product {
  display: flex;
  align-items: center;
  gap: 10px;
}

.img {
  width: 50px;
  height: 50px;
  object-fit: contain;
}

.summary {
  margin-top: 20px;
  text-align: right;
}
</style>