package org.acme.getting.started.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Mail extends PanacheEntity {    
    public String to;
    public String title;
    public String body;
    public Instant date = Instant.now();

    public static List<Mail> listByTo(String to) {
        return list("to", to);
    }
}
