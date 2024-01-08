package tn.esprit.spring.examen.entities;

import lombok.*;
import tn.esprit.spring.examen.entities.enumerations.TypeCompte;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idCompte;
    long code;
    @Enumerated(value = EnumType.STRING)
    TypeCompte type;
    double solde;

}
