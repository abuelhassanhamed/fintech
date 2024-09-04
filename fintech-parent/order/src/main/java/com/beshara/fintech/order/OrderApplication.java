package com.beshara.fintech.order;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import com.beshara.fintech.order.domain.events.OrderCreatedEvent;
import com.beshara.fintech.tracing.TracimgContextAccessor;
import com.beshara.fintech.tracing.TracingContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fintech.events.order.Order;
import com.fintech.events.order.OrderReviewedEvent;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Context;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@SpringBootApplication
@Deployment(resources = "classpath:order.bpmn")
public class OrderApplication {
	
	private static final String OPERATION_NAME = "handleOrderReviewTask";
    private static final String TRACING_COMPONENT = "Order";
	
	@Autowired
	private StreamBridge orderPublisher;
	
	@Autowired
	private ObjectMapper  mapper;
	
	@Autowired
	OpenTelemetry openTelemetry;

	public static void main(String[] args) {
		
		SpringApplication.run(OrderApplication.class, args);
		System.out.println("ssss");
	
	}
	//@Observed(name="Camunda Task")
    @JobWorker(type = "OrderReviewedEvent")
	public void throwOrderReviewedEvent(final ActivatedJob job) {
	
		final Tracer tracer = openTelemetry.getTracer(TRACING_COMPONENT);
        final SpanBuilder spanBuilder = tracer.spanBuilder(OPERATION_NAME);
        
        
//    	LinkedHashMap ctx = (LinkedHashMap)job.getVariable("context"); 
    		String traceParent=(String) job.getVariable("traceParent");
    		TracingContextHolder holder= new TracingContextHolder();
    		holder.getContext().put("traceparent", traceParent);
    	    final TracimgContextAccessor exportedSpanData = TracimgContextAccessor.createSetter();
    	    io.opentelemetry.context.propagation.TextMapPropagator textMapPropagator = openTelemetry.getPropagators().getTextMapPropagator();
    	   // textMapPropagator.inject(Context.current(), holder, exportedSpanData);
    		Context ct=textMapPropagator.extract( Context.current(), holder, exportedSpanData);
    		spanBuilder.setParent(ct);
     
		
		System.out.println(textMapPropagator.getClass().getCanonicalName());
		System.out.println(ct.toString());
		
		 final Span activeSpan = spanBuilder.startSpan();
	        try (Scope outboxSpanScope = activeSpan.makeCurrent()) {
	
	  OrderReviewedEvent orderReviewedEent=new OrderReviewedEvent();
	  Order ord=new Order();
	 //  ord.setOrderId((String) job.getVariable("id"));
	  orderReviewedEent.setPayload(ord);
	  orderPublisher.send("order-flow-binding", orderReviewedEent);
	        }catch(Exception e) {
	        	//spanBuilder.setAttribute("error",true);
	        	activeSpan.setAttribute("error",true);
	        }
	        finally {
	            activeSpan.end();
	        }	
	  
	}
    
//	@KafkaListener(id = "ORDERGROUP", topics = "order-flow")
//    public void listen(String in) {
//        System.out.println(in);
//    }

   
    
    
    
    
    
    @Bean
    @GlobalChannelInterceptor(patterns = "*")
    public ChannelInterceptor customInterceptor() {
        return new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
            	Iterator<Entry<String, Object>> it=message.getHeaders().entrySet().iterator();
            	while (it.hasNext()) {
            		Entry<String, Object> e=it.next();
            		System.out.println("message header :"+e.getKey()+" value : "+e.getValue().toString());
            	}
            //	message.getHeaders().put("type",message.getPayload().getClass().getName());

            //    message = MessageBuilder.fromMessage(message).setHeader("type",message.getPayload().getClass().getSimpleName()).build();
                
            
             //   message = MessageBuilder.fromMessage(message).setHeader("spring.cloud.function.routing-expression","headers['type'] == \"OrderCreatedEvent\" ? 'handleOrderCreation' : 'handleOrderDeletion'").build();
                
                
                
                return message;
                
            }
        };
    }
	
}
