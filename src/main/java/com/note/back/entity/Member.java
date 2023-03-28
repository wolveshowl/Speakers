package com.note.back.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(length = 256, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(length = 512, nullable = false)
    private String profilePicture;
    @Column(length = 16, nullable = false)
    private String nickname;
    @Column(length = 64)
    private String email;
    @Column(length = 1)
    private char gender;
    @Column(length = 3)
    private Integer age;
    @Column(length = 1)
    @ColumnDefault("1")
    private Byte isUse;

}
