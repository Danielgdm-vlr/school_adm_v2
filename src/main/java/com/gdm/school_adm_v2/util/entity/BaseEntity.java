package com.gdm.school_adm_v2.util.entity;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@ToString
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private Long id;

    @Column(
            name = "active",
            nullable = false
    )
    @Getter
    @Setter
    private boolean active;

    public BaseEntity(boolean active) {

        this.active = active;
    }
}
