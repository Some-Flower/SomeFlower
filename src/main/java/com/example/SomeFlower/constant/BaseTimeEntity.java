package com.example.SomeFlower.constant;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {


    @Builder.Default
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
