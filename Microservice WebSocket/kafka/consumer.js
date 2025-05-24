const { Kafka } = require('kafkajs');
require('dotenv').config();

const kafka = new Kafka({
    clientId: 'websocket-service',
    brokers: [process.env.KAFKA_BROKERS],
});

const consumer = kafka.consumer({ groupId: process.env.KAFKA_GROUP });

const initConsumer = async (io) => {
    await consumer.connect();
    await consumer.subscribe({ topic: process.env.KAFKA_TOPIC, fromBeginning: false });

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            const payload = message.value.toString();
            console.log(`[Kafka] Message reçu: ${payload}`);
            io.emit('alert', payload); // émettre au client WebSocket
        },
    });
};

module.exports = { initConsumer };
