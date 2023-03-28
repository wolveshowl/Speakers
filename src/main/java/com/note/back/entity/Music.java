package com.note.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Music {

    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Long id;
    @Column(length = 32, nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private Genre genre;
    @Column(nullable = false)
    private Integer playTime;
    @Column(length = 128, nullable = false)
    private String url;
    @Column(nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime createDate;
}
