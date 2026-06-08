import api from '@/axios/axios';

export default {
    methods: {
        async login() {
            try {
                const response = await api.get(`/login/${this.id}`);
                localStorage.setItem('authData', JSON.stringify(response.data));
            } catch (error) {
                console.error('Login failed:', error);
            }
        },
        async register() {
            try {
                const response = await api.post(`/register/${this.username}`);
                localStorage.setItem('authData', JSON.stringify(response.data));
            } catch (error) {
                console.error('Registration failed:', error);
            }
        },
        logout() {
            localStorage.removeItem('authData');
            localStorage.removeItem('username');
        },
        checkAuth() {
            return localStorage.getItem('authData') !== null;
        }
    }
}