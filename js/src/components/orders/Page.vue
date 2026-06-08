<template>
    <div>
        <h2>My Orders</h2>

        <el-alert
            v-if="error"
            :title="error"
            type="error"
            show-icon
        />

        <el-table
            v-loading="loading"
            :data="orders"
            style="width: 100%"
        >
            <el-table-column
                prop="id"
                label="Order ID"
                width="120"
            />

            <el-table-column
                prop="paidPrice"
                label="Paid Price"
            />

            <el-table-column
                prop="fullPrice"
                label="Full Price"
            >
                <template #default="{ row }">
                    ${{ row.fullPrice }}
                </template>
            </el-table-column>

            <el-table-column
                label="Actions"
            >
                <template #default="{ row }">
                    <el-button
                        type="primary"
                        @click="redirectToPayment(row)"
                    >
                        Pay
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-empty
            v-if="!loading && orders.length === 0"
            description="No orders found"
        />
    </div>
</template>

<script>
import axios from "axios"
import OrderMixin from "../../mixins/orderMixin.js"

export default {
    mixins: [OrderMixin],
    async mounted() {
        await this.loadOrders()
    },
    methods: {
        redirectToPayment(order) {
            this.$router.push({
                path: "/payment",
                query: {
                    orderId: order.id,
                    priceToPay: order.fullPrice,
                },
            })
        }
    }
}
</script>