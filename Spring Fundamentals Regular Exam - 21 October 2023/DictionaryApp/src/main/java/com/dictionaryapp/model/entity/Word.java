package com.dictionaryapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "words")
@Getter
@Setter
@NoArgsConstructor
public class Word extends BaseEntity {

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    @Length(min = 2, max = 80)
    private String translation;

    @Length(min = 2, max = 200)
    private String example;

    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne(optional = false)
    private Language language;

    @ManyToOne(optional = false)
    private User addedBy;
}
