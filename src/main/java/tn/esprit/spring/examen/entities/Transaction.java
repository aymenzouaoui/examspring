package tn.esprit.spring.examen.entities;

import lombok.*;
import tn.esprit.spring.examen.entities.enumerations.TypeTransaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idTransaction;
    double montant;
    @Enumerated(value = EnumType.STRING)
    TypeTransaction type;
    @Temporal(value = TemporalType.DATE)
    Date dateTransaction;

    @ManyToOne
    @ToString.Exclude
    Compte expediteur;
    @ManyToOne
    @ToString.Exclude
    Compte destinataire;
}
