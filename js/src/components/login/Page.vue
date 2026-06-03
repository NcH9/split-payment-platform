<template>
    <el-tabs>
        <el-tab-pane label="Login" name="login">
            
        </el-tab-pane>

        <el-tab-pane label="Register" name="register">
            Контент 2
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import LoginMixin from './mixins/login.js';
import Login from './Login.vue';
import Register from './Register.vue';

export default {
    name: 'LoginPage',
    mixins: [LoginMixin],
};
</script>