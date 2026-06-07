import axios from 'axios';

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

                const response = await axios.post(`/payments`, {
                    userId: authData.id,
                    paidPrice: this.calculatedAmount,
                    orderId: this.orderId,
                })
            } catch (error) {
                this.loading = false;
                console.error(error)
                this.error = "Failed to create payment"
            } finally {
                this.loading = false;
            }
        }
    }
}