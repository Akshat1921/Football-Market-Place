package com.footballFinal.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
//    @JoinColumn(name = "league_id")
    @JsonIgnore
    private League league;
    private String name;
    private String sponsor;
    private int standing;
    private String revenue;
    @Column(name = "market_value")
    private String marketValue;
    private String stadium;
    private List<String> formations;

    @OneToOne(mappedBy = "team")
    private Coach coach;
//
//    private Teamstats teamStats;
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<Player> players;

}
