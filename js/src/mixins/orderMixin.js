import axios from 'axios';
export default {
    data() {
        return {
            orders: [],
            loading: false,
            error: null
        }
    },
    methods: {
        async loadOrders() {
            this.loading = true

            try {
                const authData = JSON.parse(
                    localStorage.getItem("authData")
                )

                if (!authData) {
                    this.error = "User is not logged in"
                    return
                }

                const response = await axios.get(
                    `/users/${authData.id}/orders`
                )

                this.orders = response.data
            } catch (error) {
                console.error(error)
                this.error = "Failed to load orders"
            } finally {
                this.loading = false
            }
        },
        async saveOrder() {
            try {
                this.loading = true;
                const authData = JSON.parse(
                    localStorage.getItem("authData")
                )

                if (!authData) {
                    this.error = "User is not logged in"
                    return
                }

                const response = await axios.post(`/orders`, {
                    userId: authData.id,
                    paidPrice: 0,
                    fullPrice: this.totalPrice,
                })

                return response.data.id
            } catch (error) {
                this.loading = false;
                console.error(error)
                this.error = "Failed to create order"
            } finally {
                this.loading = false;
            }
        },
    }
}