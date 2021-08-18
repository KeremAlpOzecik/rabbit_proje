package com.ramazan.consumer_service.service;

import com.ramazan.consumer_service.dto.RegisterStudentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RabbitMQReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);
    private ClassroomService classroomService;

    public RabbitMQReceiver(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(RegisterStudentRequest registerStudentRequest) {
        logger.info("Details Received is.. " + registerStudentRequest.toString());
        classroomService.addStudent(registerStudentRequest);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}