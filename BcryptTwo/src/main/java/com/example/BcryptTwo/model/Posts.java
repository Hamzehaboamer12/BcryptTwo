package com.example.BcryptTwo.model;


//import org.springframework.data.annotation.Id;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String text;

    @ManyToOne
    cust User;

    public Posts()
    {
    }

    public Posts(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public cust getPostsByUser() {
        return User;
    }

    public void setPostsByUser(cust postsByUser) {
        this.User = postsByUser;
    }
}
