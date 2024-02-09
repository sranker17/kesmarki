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
@Table(name = "CONTACT")
@EntityListeners(AuditingEntityListener.class)
public class Contact extends BaseEntity {

    @Comment("Type of the contact")
    @Column(name = "CONTACT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @Comment("Data of the given contact")
    @Column(name = "DATA", nullable = false)
    private String data;
}
