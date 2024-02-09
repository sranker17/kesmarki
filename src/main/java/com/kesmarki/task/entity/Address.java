package com.kesmarki.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ADDRESS")
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity {

    @Comment("Country of the address")
    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Comment("City of the address")
    @Column(name = "CITY", nullable = false)
    private String city;

    @Comment("Zip code of the address")
    @Column(name = "ZIP", nullable = false)
    private String zip;

    @Comment("Street of the address")
    @Column(name = "STREET", nullable = false)
    private String street;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Set<Contact> contacts = new HashSet<>();
}
