package com.spl.playerservice.entity;

import com.spl.commonlibrary.entity.BaseEntity;
import com.spl.playerservice.enums.BattingStyle;
import com.spl.playerservice.enums.BowlingStyle;
import com.spl.playerservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Players extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String mobile;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private BattingStyle battingStyle;
    @Enumerated(EnumType.STRING)
    private BowlingStyle bowlingStyle;
    @Builder.Default
    private Integer totalMatches=0;
    @Builder.Default
    private Integer totalRuns=0;
    @Builder.Default
    private Integer totalWickets=0;
    @Builder.Default
    private Boolean active=true;

}
