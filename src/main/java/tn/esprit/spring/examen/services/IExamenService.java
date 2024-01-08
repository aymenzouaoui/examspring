package tn.esprit.spring.examen.services;

import tn.esprit.spring.examen.entities.Bank;
import tn.esprit.spring.examen.entities.Compte;
import tn.esprit.spring.examen.entities.Transaction;
import tn.esprit.spring.examen.entities.enumerations.TypeTransaction;

import java.util.List;

public interface IExamenService {
    Bank ajouterBank(Bank bank);
    Compte ajouterCompte(Compte compte, String agenceBank);
    //String ajouterTransaction(Transaction transaction);
    String ajouterVirement(Transaction transaction);
    String ajouterRetrait(Transaction transaction);
    String ajouterVersement(Transaction transaction);
    List<Transaction> getAllTransactionByBankId(long idBank);
    List<Transaction> getAllTransactionByTypeAndCompte(TypeTransaction type, long idCompte);
}
