package com.kesmarki.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PERSON")
@EntityListeners(AuditingEntityListener.class)
public class Person extends BaseEntity {

    @Comment("Name of the person")
    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TEMPORARY_ADDRESS_ID", referencedColumnName = "id")
    private Address temporaryAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PERMANENT_ADDRESS_ID", referencedColumnName = "id")
    private Address permanentAddress;
}
