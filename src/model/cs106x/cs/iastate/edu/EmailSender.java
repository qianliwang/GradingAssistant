package model.cs106x.cs.iastate.edu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	
	private String username = "";
    private String password = "";
	
    Session session = null;
    
	public EmailSender(String userName,final String pwd){
		
		this.username = userName;
		this.password = pwd;
		
		Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    session = Session.getInstance(props,
	            new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });
		
	}
	
	public void send(String sender,String recipient,String subject, String textBody, String attachmentPath){
		
		try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(sender));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(recipient));

	        message.setSubject(subject);

	        // create and fill the first message part
		    MimeBodyPart mbp1 = new MimeBodyPart();
//		    mbp1.setText("Dear COM S 103 Student:\n\nThe WK1HW is attached. Please let me know if you have any problem.\n\n Best Regards.\n Sen");
		    mbp1.setText(textBody);
		    
		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    
		    if(attachmentPath != null){
		    	
		    	 // create the second message part
			    MimeBodyPart mbp2 = new MimeBodyPart();

			    // attach the file to the message
			    mbp2.attachFile(attachmentPath);

			    mp.addBodyPart(mbp2);
			    
		    }
		   
		    // add the Multipart to the message
		    message.setContent(mp);

		    // set the Date: header
		    message.setSentDate(new Date());     

	        System.out.println("Sending");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }catch (IOException ioex) {
		    ioex.printStackTrace();
		}
		
	}
	
	public void send(String sender, ArrayList<String> toList, ArrayList<String> ccList, ArrayList<String> bccList,String subject, String textBody, String attachmentPath){
		try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(sender));
	        
	        for(String to: toList){
	        	
	        	message.addRecipients(Message.RecipientType.TO,
		                InternetAddress.parse(to));
	        }
	        for(String cc: ccList){
	        	message.addRecipients(Message.RecipientType.CC,
		                InternetAddress.parse(cc));
	        }
	        for(String bcc: bccList){
	        	message.addRecipients(Message.RecipientType.BCC,
		                InternetAddress.parse(bcc));
	        }
//	        message.setRecipients(Message.RecipientType.TO,	                InternetAddress.parse(recipient));

	        message.setSubject(subject);

	        // create and fill the first message part
		    MimeBodyPart mbp1 = new MimeBodyPart();
//		    mbp1.setText("Dear COM S 103 Student:\n\nThe WK1HW is attached. Please let me know if you have any problem.\n\n Best Regards.\n Sen");
		    mbp1.setText(textBody);
		    
		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    
		    if(attachmentPath != null){
		    	
		    	 // create the second message part
			    MimeBodyPart mbp2 = new MimeBodyPart();

			    // attach the file to the message
			    mbp2.attachFile(attachmentPath);

			    mp.addBodyPart(mbp2);
			    
		    }
		   
		    // add the Multipart to the message
		    message.setContent(mp);

		    // set the Date: header
		    message.setSentDate(new Date());     

	        System.out.println("Sending");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }catch (IOException ioex) {
		    ioex.printStackTrace();
		}
	}
	public void send(String sender, String recipient, ArrayList<String> ccList, ArrayList<String> bccList,String subject, String textBody, String attachmentPath){
		try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(sender));
	        	
	        message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse(recipient));

	        if(ccList != null){
	        	for(String cc: ccList){
		        	message.addRecipients(Message.RecipientType.CC,
			                InternetAddress.parse(cc));
		        }	
	        }
	        
	        if(bccList != null){
	        	for(String bcc: bccList){
		        	message.addRecipients(Message.RecipientType.BCC,
			                InternetAddress.parse(bcc));
		        }	
	        }
	        
//	        message.setRecipients(Message.RecipientType.TO,	                InternetAddress.parse(recipient));

	        message.setSubject(subject);

	        // create and fill the first message part
		    MimeBodyPart mbp1 = new MimeBodyPart();
//		    mbp1.setText("Dear COM S 103 Student:\n\nThe WK1HW is attached. Please let me know if you have any problem.\n\n Best Regards.\n Sen");
		    mbp1.setText(textBody);
		    
		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    
		    if(attachmentPath != null){
		    	
		    	 // create the second message part
			    MimeBodyPart mbp2 = new MimeBodyPart();

			    // attach the file to the message
			    mbp2.attachFile(attachmentPath);

			    mp.addBodyPart(mbp2);
			    
		    }
		   
		    // add the Multipart to the message
		    message.setContent(mp);

		    // set the Date: header
		    message.setSentDate(new Date());     

	        System.out.println("Sending");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }catch (IOException ioex) {
		    ioex.printStackTrace();
		}
	}
}
