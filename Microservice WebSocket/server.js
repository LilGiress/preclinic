require('dotenv').config();
const express = require('express');
const http = require('http');
const cors = require('cors');
const { Server } = require('socket.io');
const { initConsumer } = require('./kafka/consumer');

const app = express();
const server = http.createServer(app);

const io = new Server(server, {
    cors: {
        origin: "*", // à sécuriser en production
        methods: ["GET", "POST"],
    },
});

io.on('connection', (socket) => {
    console.log('✔️ Client WebSocket connecté');
    socket.on('disconnect', () => {
        console.log('❌ Client déconnecté');
    });
});

// Init Kafka + WebSocket
initConsumer(io).catch(console.error);

// Lancement du serveur
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`🚀 Serveur WebSocket lancé sur http://localhost:${PORT}`);
});
