import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

import com.koud.rabbitMQ.Receiver;

public class test1 {

	private RabbitTemplate rabbitTemplate;
	private Queue queue;
	private TopicExchange topicExchange;
	private Binding binding;
	private MessageListenerAdapter messageListenerAdapter;
	private Receiver receiver;


    static final String topicExchangeName = "spring-boot-exchange";
    static final String queueName = "spring-boot";
	
	@Before
	public void setUp() throws Exception {
		receiver = new Receiver();
		queue = new Queue(queueName, false);
		topicExchange = new TopicExchange(topicExchangeName);
		binding = BindingBuilder.bind(queue).to(topicExchange).with("foo.bar.#");
		messageListenerAdapter = new MessageListenerAdapter(receiver, "receiveMessage");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		rabbitTemplate = new RabbitTemplate();
		System.out.println("Sending new message...");
		rabbitTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", "Hello KOUDDDDDDDDDDD!");
	}
	
	@Bean
	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	            MessageListenerAdapter listenerAdapter) {
	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	        container.setConnectionFactory(connectionFactory);
	        container.setQueueNames(queueName);
	        container.setMessageListener(listenerAdapter);
	        return container;
	    }

}
