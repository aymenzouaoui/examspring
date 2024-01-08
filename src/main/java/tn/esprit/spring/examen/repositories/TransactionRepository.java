package tn.esprit.spring.examen.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.examen.entities.Transaction;
import tn.esprit.spring.examen.entities.enumerations.TypeTransaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    List<Transaction> findByDateTransaction(Date date);

    @Query("select transaction from Transaction transaction  join Bank bank on ((transaction.destinataire member bank.comptes) or (transaction.expediteur member bank.comptes)) where bank.idBank=:idBank")
    List<Transaction> findByBankIdBank(long idBank);

    List<Transaction> findByDestinataireIdCompteOrExpediteurIdCompteAndType(long destintaire, long expediteur, TypeTransaction type);
}
