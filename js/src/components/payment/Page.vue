<template>
  <div class="payment">
    <h2>💳 Payment</h2>

    <el-card>
      <p><b>Total amount:</b> ${{ amount }}</p>

      <el-divider />

      <h3>Select payment percentage</h3>

      <el-radio-group v-model="selectedPercent">
        <el-radio-button
          v-for="p in availablePercents"
          :key="p"
          :label="p"
        >
          {{ p }}%
        </el-radio-button>
      </el-radio-group>

      <el-divider />

      <p>
        You will pay:
        <b>${{ calculatedAmount }}</b>
      </p>

      <el-button type="primary" size="large" @click="pay">
        Pay now
      </el-button>
    </el-card>
  </div>
</template>

<script setup>
import { computed, ref } from "vue"
import { useRoute } from "vue-router"
import { ElMessage } from "element-plus"

const route = useRoute()

const amount = Number(route.query.amount || 0)

const availablePercents = amount >= 100
  ? [25, 50, 75, 100]
  : [100]

const selectedPercent = ref(100)

const calculatedAmount = computed(() => {
  return ((amount * selectedPercent.value) / 100).toFixed(2)
})

const pay = () => {
  ElMessage({
    type: "success",
    message: `Payment of $${calculatedAmount.value} successful`,
  })
}
</script>

<style scoped>
.payment {
  max-width: 600px;
  margin: 0 auto;
}
</style>