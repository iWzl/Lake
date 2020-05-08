package com.upuphub.lake.lnklake.tcp;

import com.upuphub.lake.lnklake.tcp.netty.NettyClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LnklakeTcpApplicationTests {

    @Autowired
    NettyClient nettyClient;

    @Test
    void contextLoads() {
    }

    @Test
    void nettyTest(){
        nettyClient.sendMsg("Hello");
    }

}
