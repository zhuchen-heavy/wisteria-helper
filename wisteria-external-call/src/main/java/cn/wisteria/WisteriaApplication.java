package cn.wisteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

@SpringBootApplication
public class WisteriaApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(WisteriaApplication.class, args);
    }

}
