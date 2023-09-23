package com.footballFinal.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="team_id")

    @JsonIgnore
    private Team team;
    private String name;
    private Integer age;
    private Long weight;
    @Column(name="market_value")
    private String marketValue;
    private Long height;

    private String nationality;
    private String position;


//    private Playerstats playerstats;
}
