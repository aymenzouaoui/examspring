package tn.esprit.spring.examen.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.examen.entities.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank,Long> {
   Bank findByAgence(String agenceBank);
}
