package com;

import com.baozi.business.BusinessNotify;
import com.config.NettyBootConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
@RequestMapping(value = "/server")
public class SpringbootNettyServerDemoApplication implements CommandLineRunner {

    @Resource
    private NettyBootConfig bootConfig;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyServerDemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        bootConfig.start();
    }

    @RequestMapping(value = "/demo-send")
    public Boolean sendMsg(@RequestParam("msg") String msg, @RequestParam("clientId") String clientId){
        return BusinessNotify.noticeSingleClient(clientId,msg,true,false);
    }
}
