package com.cjb.main.RabbitMQ.work_queues;

import com.cjb.main.RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
1、声明队列
2、创建连接
3、创建通道
4、通道声明队列
5、制定消息
6、发送消息，使用默认交换机
*/
public class Producer02 {
    //声明队列
    private static final String QUEUE ="queue";
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtil.getConnection("/route");
            //创建与Exchange的通道，每个连接可以创建多个通道，每个通道代表一个会话任务
            channel = connection.createChannel();

            //通道绑定队列
            /**
             * 声明队列，如果Rabbit中没有此队列将自动创建
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否自动删除此队列
             * param5:队列参数
             * String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
             *
             */
            channel.queueDeclare(QUEUE,true,false,false,null);//通道绑定邮件队列

            for(int i = 0;i<10;i++){
                String message = new String("mq 发送消息。。。");
                /**
                              * 消息发布方法
                              * param1：Exchange的名称，如果没有指定，则使用Default Exchange
                              * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
                              * param3:消息包含的属性
                              * param4：消息体
                  * 这里没有指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但是不能显示绑定或解除绑定
                  * 默认的交换机，routingKey等于队列名称
                 */
                //String exchange, String routingKey, BasicProperties props, byte[] body
                channel.basicPublish("",QUEUE,null,message.getBytes("utf-8"));
                System.out.println("mq消息发送成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}