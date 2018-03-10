package org.obarcia.demo.components.mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Implementación del JavaMailSender para poder usar un protocolo "fake"
 * donde no se envía nada.
 * 
 * @author obarcia
 */
public class MailSenderImpl extends JavaMailSenderImpl
{
    /**
     * Instancia del LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MailSenderImpl.class.getName());
    
    @Override
    public void send(MimeMessage mm) throws MailException
    {
        if (getProtocol() != null) {
            switch (getProtocol()) {
                case "fake": break;
                case "log":
                    LOGGER.log(Level.INFO, mm.toString());
                    break;
                default:
                    super.send(mm);
                    break;
            }
        }
    }
    @Override
    public void send(MimeMessage... mms) throws MailException
    {
        if (getProtocol() != null) {
            switch (getProtocol()) {
                case "fake": break;
                case "log":
                    LOGGER.log(Level.INFO, mms[0].toString());
                    break;
                default:
                    super.send(mms);
                    break;
            }
        }
    }
    @Override
    public void send(MimeMessagePreparator mmp) throws MailException
    {
        if (getProtocol() != null) {
            switch (getProtocol()) {
                case "fake": break;
                case "log":
                    LOGGER.log(Level.INFO, mmp.toString());
                    break;
                default:
                    super.send(mmp);
                    break;
            }
        }
    }
    @Override
    public void send(MimeMessagePreparator... mmps) throws MailException
    {
        if (getProtocol() != null) {
            switch (getProtocol()) {
                case "fake": break;
                case "log":
                    LOGGER.log(Level.INFO, mmps[0].toString());
                    break;
                default:
                    super.send(mmps);
                    break;
            }
        }
    }
    @Override
    public void send(SimpleMailMessage smm) throws MailException
    {
        if (getProtocol() != null) {
            LOGGER.log(Level.SEVERE, "Protocol: " + getProtocol());
        
            switch (getProtocol()) {
                case "fake": break;
                case "log":
                    LOGGER.log(Level.INFO, smm.toString());
                    break;
                default:
                    super.send(smm);
                    break;
            }
        }
    }
    @Override
    public void send(SimpleMailMessage... smms) throws MailException
    {
        if (getProtocol() != null) {
            switch (getProtocol()) {
                case "fake": break;
                case "log":
                    LOGGER.log(Level.INFO, smms[0].toString());
                    break;
                default:
                    super.send(smms);
                    break;
            }
        }
    }
}