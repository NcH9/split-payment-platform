<template>
  <el-card class="box">
    <h2>Register</h2>

    <el-form>
      <el-form-item label="Username">
        <el-input v-model="username" placeholder="Enter username" />
      </el-form-item>

      <el-button type="success" @click="register">
        Register
      </el-button>
    </el-form>
  </el-card>
</template>

<script>
import LoginMixin from "./mixins/login.js"

export default {
    data() {
        return {
            username: "",
        }
    },
    mixins: [LoginMixin],
}
</script>

<style scoped>
.box {
  max-width: 400px;
  margin: 60px auto;
}
</style>