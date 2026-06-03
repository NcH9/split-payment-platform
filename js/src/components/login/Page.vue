<template>
    <div>
        <el-tabs v-if="isLoggedIn" type="border-card">
            <el-tab-pane label="Login" name="login">
                <Login />
            </el-tab-pane>

            <el-tab-pane label="Register" name="register">
                <Register />
            </el-tab-pane>
        </el-tabs>
        <div v-else>
            <h2>Welcome, {{ userData }}</h2>
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
            userData: localStorage.getItem('authData'),
        }
    },
    methods: {
        isLoggedIn() {
            const data = localStorage.getItem("authData")
            return data && data !== "null" && data !== "undefined"
        }
    }
}
</script>