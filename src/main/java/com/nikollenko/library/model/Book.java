package com.nikollenko.library.model;


import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="BOOKS")
@Data
public class Book {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "TITLE", length = 128)
    private String title;
    @Column(name = "AUTHOR", length = 64)
    private String author;
    @Column(name = "YEAR", length = 16)
    private String issueYear;

    @Column(name = "BUSY")
    private boolean busy;

}
