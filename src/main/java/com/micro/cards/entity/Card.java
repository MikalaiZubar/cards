package com.micro.cards.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long cardId;

    @Column
    private String mobileNumber;

    @Column
    private String cardNumber;

    @Column
    private String cardType;

    @Column
    private long totalLimit;

    @Column
    private long amountUsed;

    @Column
    private long availableAmount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
