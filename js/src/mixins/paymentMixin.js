import api from '@/axios/axios';
import { ElMessage } from "element-plus"

export default {
    data() {
        return {
            loading: false,
        }
    },
    methods: {
        async createPayment() {
            try {
                this.loading = true;
                const authData = JSON.parse(
                    localStorage.getItem("authData")
                )

                if (!authData) {
                    this.error = "User is not logged in"
                    return
                }

                const response = await api.post(`/payments`, {
                    userId: authData.id,
                    paidPrice: this.calculatedAmount,
                    orderId: this.orderId,
                })
                
                ElMessage({
                    type: "success",
                    message: `Payment of $${this.calculatedAmount} successful`,
                })
            } catch (error) {
                this.loading = false;
                console.error(error)
                ElMessage({
                    type: "error",
                    message: "Failed to create payment",
                })
            } finally {
                this.loading = false;
            }
        }
    }
}