<template>
    <div>
        <el-tabs v-if="!isLoggedIn" type="border-card">
            <el-tab-pane label="Login" name="login">
                <Login />
            </el-tab-pane>

            <el-tab-pane label="Register" name="register">
                <Register />
            </el-tab-pane>
        </el-tabs>
        <div v-else>
            <h2>Welcome, {{ userData?.name }}</h2>
            <el-button type="danger" @click="logout">
                Logout
            </el-button>
        </div>
    </div>
</template>

<script>
import LoginMixin from './mixins/login.js';
import Login from './Login.vue';
import Register from './Register.vue';

export default {
    name: 'LoginPage',
    mixins: [LoginMixin],
    components: {
        Login,
        Register,
    },
    data() {
        return {
            userData: null,
        }
    },
    computed: {
        isLoggedIn() {
            const authData = JSON.parse(
                localStorage.getItem("authData")
            )
            this.userData = authData
            return authData && authData !== "null" && authData !== "undefined"
        }
    },
    methods: {
        logout() {
            localStorage.removeItem("authData")
            this.userData = null
        }
    },
}
</script>