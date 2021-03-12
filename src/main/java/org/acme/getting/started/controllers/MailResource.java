package org.acme.getting.started.controllers;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.getting.started.entities.Mail;
import org.acme.getting.started.services.MailService;

@Path("/email")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MailResource { 

    private MailService mailService;

    public MailResource(MailService mailService) {
        this.mailService = mailService;
    }

    @GET
    public List<Mail> getAll() {
        return mailService.findAll();
    }

    @POST
    @Transactional
    public void sendEmail(Mail mail) { 
        mailService.sendAndSaveEmail(mail);
    }

    @GET
    @Path("/{to}")
    public List<Mail> getByTo(@PathParam("to") String to) {
        return mailService.findByTo(to);
    }
}