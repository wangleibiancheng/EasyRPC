package customer;


import netty.NettyClient;
import publicinterface.HelloService;

public class ClientBootstrap {
    // 这里定义协议头
    public static final String providerName = "HelloService#hello#";
    private static String serviceName = "/publicinterface.HelloService1";
    public static void main(String[] args) throws Exception {
        // 创建一个消费者
        NettyClient customer = new NettyClient();
        //服务发现
        String address = customer.getAddress(serviceName);
        NettyClient.initClient(address);
        // 创建代理对象 服务引用
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (; ; ) {
            Thread.sleep(2 * 1000);
            // 通过代理对象调用服务提供者的方法(服务)
            String res = service.hello("你好 dubbo~");
            System.out.println("调用的结果 res= " + res);
        }
    }
}
