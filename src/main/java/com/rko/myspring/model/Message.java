package com.rko.myspring.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Message {

    @ManyToOne
    private User expediteur;
    @ManyToOne
    private User destinataire;

    //@NotBlank
    private String content;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public User getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(User expediteur) {
        this.expediteur = expediteur;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
