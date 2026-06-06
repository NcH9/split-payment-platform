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
                prop="status"
                label="Status"
            />

            <el-table-column
                prop="totalAmount"
                label="Amount"
            >
                <template #default="{ row }">
                    ${{ row.totalAmount }}
                </template>
            </el-table-column>

            <el-table-column
                prop="createdAt"
                label="Created"
            />
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
}
</script>