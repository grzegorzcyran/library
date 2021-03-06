package com.grcy.library.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {

    @Id
    private Long id;

    private String title;
    private String author;

}
