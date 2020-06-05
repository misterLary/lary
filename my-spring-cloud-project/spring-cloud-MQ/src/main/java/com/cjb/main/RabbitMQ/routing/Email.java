package com.cjb.main.RabbitMQ.routing;

import com.cjb.main.RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
public class Email {

    //Routing 路由模式
    private static final String QUEUE_SMS ="queueSms";
    private static final String EXCHANGE = "messageChange";
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtil.getConnection(null);
            channel = connection.createChannel();
            //通道绑定交换机
            /**
                          * 参数明细
                          * 1、交换机名称
                          * 2、交换机类型，fanout、topic、direct、headers
                          */
            //Routing 路由模式
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
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
            channel.queueDeclare(QUEUE_SMS,true,false,false,null);//通道绑定短信队列
            //交换机和队列绑定
            /**
             * 参数明细
             * 1、队列名称
             * 2、交换机名称
             * 3、路由key
             */
            //Routing 路由模式
            channel.queueBind(QUEUE_SMS,EXCHANGE,QUEUE_SMS);
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                /**
                              * 消费者接收消息调用此方法
                              * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
                              * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志
                 (收到消息失败后是否需要重新发送)
                              * @param properties
                              * @param body
                              * @throws IOException
                 * String consumerTag, Envelope envelope, BasicProperties properties, byte[] body
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //交换机
                    String exchange = envelope.getExchange();
                    //路由key
                    String routingKey = envelope.getRoutingKey();
                    envelope.getDeliveryTag();
                    String msg = new String(body,"utf-8");
                    System.out.println("mq收到的消息是："+msg );
                }
            };
            System.out.println("消费者启动成功！");
            channel.basicConsume(QUEUE_SMS,true,consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}