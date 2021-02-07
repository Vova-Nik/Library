package com.nikollenko.library.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="USERS")
@Data
public class User {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> books;


    public boolean takeBook(Book book){
        books.add(book);
        return true;
    }

    public boolean returnBook(Book book){
        if(!books.contains(book)){
            return false;
        }
        books.remove(book);
        return true;
    }
}
