package tn.esprit.spring.examen.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idBank;
    String nom;
    String agence;
    String adresse;
    @OneToMany
    @ToString.Exclude
    Set<Compte> comptes;
}
