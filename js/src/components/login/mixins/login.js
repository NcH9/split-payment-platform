import axios from 'axios';
export default {
    methods: {
        async login() {
            try {
                const response = await api.get(`/login/${id}`);
                localStorage.setItem('authData', JSON.stringify(response.data));
                localStorage.setItem('username', response.data.name);
            } catch (error) {
                console.error('Login failed:', error);
            }
        },
        async register() {
            try {
                const response = await api.post('/register/{name}', { name: this.username });
                localStorage.setItem('authData', JSON.stringify(response.data));
                localStorage.setItem('username', response.data.name);
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