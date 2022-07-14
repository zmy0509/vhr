package org.qingqiao.mail.consume;

import org.qingqiao.vhr.bean.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
@RabbitListener(queues = "qingqiao.mail.welcome")
public class MailSend {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    TemplateEngine templateEngine;

    @RabbitHandler
    public void consume(Employee employee) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom("1055218733@qq.com");
            //发送的地址
            helper.setTo(employee.getEmail());
            //发送时间
            helper.setSentDate(new Date());
            //邮件主题
            helper.setSubject("欢迎入职");
            //设置模板属性
            Context context = new Context();
            context.setVariable("empName",employee.getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            context.setVariable("jobLevelName",employee.getJobLevel().getName());
            context.setVariable("posName",employee.getPosition().getName());
            String process = templateEngine.process("mail",context);
            //发送内容
            helper.setText(process,true);
            //发送邮件
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
