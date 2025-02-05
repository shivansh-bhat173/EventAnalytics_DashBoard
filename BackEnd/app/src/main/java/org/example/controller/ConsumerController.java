package org.example.controller;

//import io.prometheus.client.Counter;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerController {

//    private final Counter kafkaEventsCounter;
    private final Counter myEventCounter;

    public ConsumerController(MeterRegistry meterRegistry) {
//        kafkaEventsCounter = Counter.build()
//                .name("kafka_events_received_total")
//                .help("Total number of Kafka events received")
//                .register();
        this.myEventCounter = meterRegistry.counter("button_click_total", "event", "occurred");
    }

    @KafkaListener(topics = "testy", groupId = "metrics-consumer-group")
    public void listen(String eventData) {
        System.out.println("Received event: " + eventData);
        myEventCounter.increment();
    }
}