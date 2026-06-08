ABOUT:  
A simple full-stack project that demonstrates an order management system with support for split and partial payments.  
The system allows users to create orders and pay them in multiple installments instead of a single full payment. Each order can have multiple related payments, while each payment belongs to one order.  

STACK:  
Backend: Java, Spring Boot, Flyway
Frontend: Vue 3, Axios, Element Plus  
Database: PostgreSQL  
DevOps: Docker, Docker Compose  

PURPOSE:  
This project is built for learning and practicing backend architecture, REST API design, and real-world payment flow modeling similar to modern fintech systems.  
  
HOW TO INSTALL (after git clone):  
cd split-payment-platform  
docker compose up -d  
  
cd js  
npm install  
npm run dev (for development)  
  
Default page would be   
http://localhost:5173/products  
(if everything is done correctly)  
  
Good Luck!
