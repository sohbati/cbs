package com.sina.cbs.document.test;

import com.sina.cbs.document.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        new T1().start();
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    class T1 extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                    GenericMessage<String> message = new GenericMessage<>(Math.random() + "!!");
                    messagingTemplate.setDefaultDestination("/app");
                    messagingTemplate.setUserDestinationPrefix("/app");
                    messagingTemplate.convertAndSend("/topic/greetings", message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    @Async
    public void sendLoop() throws Exception {
        //Thread.sleep(10000);
        for (int i = 0; i < 10; i++) {
            greeting2();
           // Thread.sleep(1000);

        }
    }

    @SendTo("/topic/greetings")
    public Greeting greeting2() throws Exception {

      //  Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(Math.random()+"") + "!");
    }
}
