package com.pepit.mail;

import com.pepit.dto.ProductDto;
import com.sendgrid.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class MailSender {

    public static void sendGridMail(List<ProductDto> productsToDisplay, String email){
        String bestProduct = productsToDisplay.get(0).getProperties()
                .entrySet()
                .stream()
                .map(product -> "<b>" + product.getKey() + "</b>: " + product.getValue() + "<br>\n")
                .collect(Collectors.joining());
        Email from = new Email("filter@compare-it.com");
        String subject = "[COMPARE-IT] Un filtre à remonté de nouveaux résultats !";
        Email to = new Email(email);
        Content content = new Content("text/html",
                "<h1>Compare IT</h1> \n" +
                        "<h2>Un de vos filtres a de nouveaux résultats !</h2>\n" +
                        "<p>Ce produit semble vous correspondre !</p> \n" +
                        "<div><img style=\"display:inline-block; vertical-align: middle;\" \n" +
                        "\t\t\tsrc=\"" + productsToDisplay.get(0).getProperties().get("img") + "\"                    \n" +
                        "\t\t\twidth=\"400\" \n" +
                        "\t\t\theight=\"300\"> \n" +
                        "<div style=\"display:inline-block; margin-bottom:1%\">\n" +
                        "\t<h2>" + productsToDisplay.get(0).getProperties().get("name") + "</h2> \n" +
                        "\t<p>" + bestProduct + "</p>\n" +
                        "</div>\n" +
                        "<p> Mais aussi... </p>\n" +
                        "<div style=\"margin-top: 1%\">\n" +
                        "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                        "\t\t\tsrc=\"" + productsToDisplay.get(1).getProperties().get("img") + "\"                    \n" +
                        "\t\t\twidth=\"100\" \n" +
                        "\t\t\theight=\"50\">\n" +
                        "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                        "\t\t\tsrc=\"" + productsToDisplay.get(2).getProperties().get("img") + "\"                    \n" +
                        "\t\t\twidth=\"100\" \n" +
                        "\t\t\theight=\"50\">\n" +
                        "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                        "\t\t\tsrc=\"" + productsToDisplay.get(3).getProperties().get("img") + "\"                    \n" +
                        "\t\t\twidth=\"100\" \n" +
                        "\t\t\theight=\"50\">\n" +
                        "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                        "\t\t\tsrc=\"" + productsToDisplay.get(4).getProperties().get("img") + "\"                    \n" +
                        "\t\t\twidth=\"100\" \n" +
                        "\t\t\theight=\"50\">\n" +
                        "</div>\n" +
                        "</div> \n" +
                        "<a href=\"https://www.mozilla.org/firefox/products/\"><br> Retrouvez l'intégralité de vos résultats sur compare-it !</a>");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        };
    }


    public static void sendMessage(List<ProductDto> productsToDisplay, String email) {
        String bestProduct = productsToDisplay.get(0).getProperties()
                .entrySet()
                .stream()
                .map(product -> "<b>" + product.getKey() + "</b>: " + product.getValue() + "<br>\n")
                .collect(Collectors.joining());
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        String password = "Compareit59000";
                        String username = "compare.it.fr@gmail.com";
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("[COMPARE-IT] Un filtre à remonté de nouveaux résultats !");
            message.setContent("<h1>Compare IT</h1> \n" +
                    "<h2>Un de vos filtres a de nouveaux résultats !</h2>\n" +
                    "<p>Ce produit semble vous correspondre !</p> \n" +
                    "<div><img style=\"display:inline-block; vertical-align: middle;\" \n" +
                    "\t\t\tsrc=\"" + productsToDisplay.get(0).getProperties().get("img") + "\"                    \n" +
                    "\t\t\twidth=\"400\" \n" +
                    "\t\t\theight=\"300\"> \n" +
                    "<div style=\"display:inline-block; margin-bottom:1%\">\n" +
                    "\t<h2>" + productsToDisplay.get(0).getProperties().get("name") + "</h2> \n" +
                    "\t<p>" + bestProduct + "</p>\n" +
                    "</div>\n" +
                    "<p> Mais aussi... </p>\n" +
                    "<div style=\"margin-top: 1%\">\n" +
                    "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                    "\t\t\tsrc=\"" + productsToDisplay.get(1).getProperties().get("img") + "\"                    \n" +
                    "\t\t\twidth=\"100\" \n" +
                    "\t\t\theight=\"50\">\n" +
                    "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                    "\t\t\tsrc=\"" + productsToDisplay.get(2).getProperties().get("img") + "\"                    \n" +
                    "\t\t\twidth=\"100\" \n" +
                    "\t\t\theight=\"50\">\n" +
                    "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                    "\t\t\tsrc=\"" + productsToDisplay.get(3).getProperties().get("img") + "\"                    \n" +
                    "\t\t\twidth=\"100\" \n" +
                    "\t\t\theight=\"50\">\n" +
                    "<img style=\"display:inline-block; vertical-align: middle;\" \n" +
                    "\t\t\tsrc=\"" + productsToDisplay.get(4).getProperties().get("img") + "\"                    \n" +
                    "\t\t\twidth=\"100\" \n" +
                    "\t\t\theight=\"50\">\n" +
                    "</div>\n" +
                    "</div> \n" +
                    "<a href=\"https://www.mozilla.org/firefox/products/\"><br> Retrouvez l'intégralité de vos résultats sur compare-it !</a>", "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
