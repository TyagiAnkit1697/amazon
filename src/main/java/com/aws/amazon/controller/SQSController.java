package com.aws.amazon.controller;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.aws.amazon.model.SQSMessege;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/aws/sqs")
public class SQSController {
    @Autowired
    AmazonSQS sqs;

    @Value("${aws.sqs.url}")
    private String queueURL;

    @PostMapping("/produceMessage")
    public String produceSQSMessege(@RequestBody SQSMessege msg){
        SendMessageRequest request = new SendMessageRequest(queueURL, msg.getMessege());
        String msgID = sqs.sendMessage(request).getMessageId();
        log.info("messege id is: {} ",msgID);
      return  msgID;
    }
    @GetMapping("/consumeMessage")
    public List<Message> readMessegeFromQueue(){
      List<Message> messegs =  sqs.receiveMessage(new ReceiveMessageRequest(queueURL).withWaitTimeSeconds(10).withMaxNumberOfMessages(10))
                .getMessages();
      messegs.stream().forEach(a->{
          log.info("message: {}, messege_ID: {}", a.getBody(), a.getMessageId());
          deleteMessageFromQueue(a);
      });
      return messegs;
    }

    @DeleteMapping
    public void deleteMessageFromQueue(Message msg){
        sqs.deleteMessage(new DeleteMessageRequest(queueURL, msg.getReceiptHandle()));
        log.warn("Messege has been deleted: Messege: {}, Messege_ID: {}",msg.getBody(), msg.getMessageId());
    }
}
