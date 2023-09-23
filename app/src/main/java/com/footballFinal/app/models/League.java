package com.footballFinal.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="league")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "league_id")
    private Long id;
//    @Column(nullable = false)
    private String name;
    @Column(name="broadcasting_rights")
    private String broadcastingRights;
    private String revenue;
    private int standing;
    @OneToMany(mappedBy = "league",cascade = CascadeType.ALL)
    private List<Team> teams;


//    @JoinColumn(name="fk_league_id",referencedColumnName = "league_id")
}