<template>
  <el-card class="box">
    <h2>Login</h2>

    <el-form>
      <el-form-item label="User ID">
        <el-input v-model="id" placeholder="Enter user id" />
      </el-form-item>

      <el-button type="primary" @click="login">
        Login
      </el-button>
    </el-form>
  </el-card>
</template>

<script>
import LoginMixin from "./mixins/login.js"

export default {
  mixins: [LoginMixin],
  data() {
    return {
      id: "",
    }
  },
}
</script>

<style scoped>
.box {
  max-width: 400px;
  margin: 60px auto;
}
</style>