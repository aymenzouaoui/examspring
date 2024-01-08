package tn.esprit.spring.examen.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.examen.entities.Compte;

@Repository
public interface CompteRepository extends CrudRepository<Compte,Long> {
    Compte findByCode(long code);
}
