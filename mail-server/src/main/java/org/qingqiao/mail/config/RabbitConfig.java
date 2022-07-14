package org.qingqiao.mail.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //创建队列
    @Bean
    Queue queue(){
        return new Queue("qingqiao.mail.welcome");
    }
    //声明消息发送方式
    //durable 是否长期有效
    //autoDelete 无连接时是否自动删除
    @Bean
    public FanoutExchange emailExchange(){
        return new FanoutExchange("email-exchange",true,false);
    }
    //绑定队列
    @Bean
    public BindingBuilder.DestinationConfigurer bindingEmailDirect(){
        return BindingBuilder.bind(queue());
    }
}
