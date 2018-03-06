package org.obarcia.demo.services;

import javax.servlet.http.HttpServletRequest;
import org.obarcia.demo.components.mail.MailSenderImpl;
import org.obarcia.demo.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de envío de emails.
 * 
 * @author obarcia
 */
@Service
public class MailServiceImpl implements MailService
{
    /**
     * Instancia del servicio para el envío de emails.
     */
    @Autowired
    private MailSenderImpl mailSender;
    
    @Override
    public void sendmailActivation(HttpServletRequest request, User user)
    {
        String url = request.getContextPath() + "/user/activate?k=" + user.getUkey();
        
        // TODO: Enviar el email
        SimpleMailMessage emailObj = new SimpleMailMessage();
        emailObj.setTo(user.getEmail());
        emailObj.setSubject("EWQ");
        emailObj.setText("QWE");
        mailSender.send(emailObj);
    }
    @Override
    public void sendmailRecovery(HttpServletRequest request, User user)
    {
        String url = request.getContextPath() + "/user/recover?k=" + user.getUkey();
        
        // TODO: Enviar el email
    }
}
