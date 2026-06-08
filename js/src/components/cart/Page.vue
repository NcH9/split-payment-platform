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

      <el-button type="primary" size="large" @click="createOrder">
        Order now
      </el-button>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router"
import { ElMessage } from "element-plus"
import OrderMixin from "../../mixins/orderMixin.js"

const router = useRouter()

export default {
  mixins: [OrderMixin],
  data() {
    return {
      cart: [],
    }
  },
  computed: {
    totalPrice() {
      return this.cart
        .reduce((sum, item) => sum + item.price * item.qty, 0)
        .toFixed(2)
    },
  },
  methods: {
    loadCart() {
      const data = localStorage.getItem("cart")
      this.cart = data ? JSON.parse(data) : []
    },
    saveCart() {
      localStorage.setItem("cart", JSON.stringify(this.cart))
    },
    removeItem(id) {
      this.cart = this.cart.filter(item => item.id !== id)
      this.saveCart()

      ElMessage({
        type: "info",
        message: "Item removed",
      })
    },
    async createOrder() {
      if (this.cart.length === 0) return

      const amount = this.totalPrice

      try {
        const newOrderId = await this.saveOrder();

        router.push({
          path: "/payment",
          query: {
            orderId: newOrderId,
            priceToPay: amount,
          },
        })

        ElMessage({
          type: "success",
          message: "Redirecting to payment...",
        })
      } catch (error) {

      }
    }
  },
  mounted() {
    this.loadCart()
  },
}
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