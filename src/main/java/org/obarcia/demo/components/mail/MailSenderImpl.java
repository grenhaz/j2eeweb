package org.obarcia.demo.components.mail;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 *
 * @author Heck
 */
public class MailSenderImpl extends JavaMailSenderImpl
{
    @Override
    public void send(MimeMessage mm) throws MailException
    {
        if (getProtocol() != null && !getProtocol().equals("fake")) {
            super.send(mm);
        }
    }
    @Override
    public void send(MimeMessage... mms) throws MailException
    {
        if (getProtocol() != null && !getProtocol().equals("fake")) {
            super.send(mms);
        }
    }
    @Override
    public void send(MimeMessagePreparator mmp) throws MailException
    {
        if (getProtocol() != null && !getProtocol().equals("fake")) {
            super.send(mmp);
        }
    }
    @Override
    public void send(MimeMessagePreparator... mmps) throws MailException
    {
        if (getProtocol() != null && !getProtocol().equals("fake")) {
            super.send(mmps);
        }
    }
    @Override
    public void send(SimpleMailMessage smm) throws MailException
    {
        if (getProtocol() != null && !getProtocol().equals("fake")) {
            super.send(smm);
        }
    }
    @Override
    public void send(SimpleMailMessage... smms) throws MailException
    {
        if (getProtocol() != null && !getProtocol().equals("fake")) {
            super.send(smms);
        }
    }
}
