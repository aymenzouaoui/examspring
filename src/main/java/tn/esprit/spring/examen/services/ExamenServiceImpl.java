package tn.esprit.spring.examen.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.examen.entities.Bank;
import tn.esprit.spring.examen.entities.Compte;
import tn.esprit.spring.examen.entities.Transaction;
import tn.esprit.spring.examen.entities.enumerations.TypeCompte;
import tn.esprit.spring.examen.entities.enumerations.TypeTransaction;
import tn.esprit.spring.examen.repositories.BankRepository;
import tn.esprit.spring.examen.repositories.CompteRepository;
import tn.esprit.spring.examen.repositories.TransactionRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ExamenServiceImpl implements IExamenService {

    BankRepository bankRepository;
    CompteRepository compteRepository;
    TransactionRepository transactionRepository;

    @Override
    public Bank ajouterBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Compte ajouterCompte(Compte compte, String agenceBank) {
        Bank bank = bankRepository.findByAgence(agenceBank);
        Compte compte1 = compteRepository.save(compte);
        bank.getComptes().add(compte1);
        bankRepository.save(bank);
        return compte1;
    }

    /*@Override
    public String ajouterTransaction(Transaction transaction) {
        if (transaction.getType().equals(TypeTransaction.VIREMENT)) {
            transaction.setExpediteur(compteRepository.findById(transaction.getExpediteur().getIdCompte()).get());
            transaction.setDestinataire(compteRepository.findById(transaction.getDestinataire().getIdCompte()).get());
        }
        else {
            if (transaction.getType().equals(TypeTransaction.VERSEMENT)) {
                transaction.setDestinataire(compteRepository.findById(transaction.getDestinataire().getIdCompte()).get());
            } else {
                transaction.setExpediteur(compteRepository.findById(transaction.getExpediteur().getIdCompte()).get());
            }
        }
        if (transaction.getType().equals(TypeTransaction.VIREMENT) && (transaction.getExpediteur().getType().equals(TypeCompte.EPARGNE))){
            return "On ne peut pas faire un virement à partir d’un compte épargne";
        } else {
            if ((transaction.getType().equals(TypeTransaction.VIREMENT) && !(transaction.getExpediteur().getType().equals(TypeCompte.EPARGNE) && (transaction.getExpediteur().getSolde() < transaction.getMontant()+3)))){
                return "On ne peut pas faire un virement : Solde insuffisant";
            } else {
                if ((transaction.getType().equals(TypeTransaction.RETRAIT)&&(transaction.getExpediteur().getSolde() < transaction.getMontant()+2))){
                    return "On ne peut pas faire un retrait : Solde insuffisant";
                } else {
                    if (transaction.getType().equals(TypeTransaction.VIREMENT)){
                            Compte expediteur = compteRepository.findById(transaction.getExpediteur().getIdCompte()).get();
                            Compte destinataire = compteRepository.findById(transaction.getDestinataire().getIdCompte()).get();
                            expediteur.setSolde(expediteur.getSolde() - (transaction.getMontant() + 3));
                            destinataire.setSolde(destinataire.getSolde() + transaction.getMontant());
                            compteRepository.save(expediteur);
                            compteRepository.save(destinataire);
                            transactionRepository.save(transaction);
                            return "VIREMENT de "+transaction.getMontant()+" DT de compte "+expediteur.getIdCompte()+" vers le compte "+destinataire.getIdCompte()+" approuvée avec succès";

                    }else {
                        if (transaction.getType().equals(TypeTransaction.RETRAIT)){
                            Compte expediteur = compteRepository.findById(transaction.getExpediteur().getIdCompte()).get();
                            expediteur.setSolde(expediteur.getSolde() - (transaction.getMontant() + 2));
                            compteRepository.save(expediteur);
                            transactionRepository.save(transaction);
                            return "RETRAIT de "+transaction.getMontant()+" DT de compte "+expediteur.getIdCompte()+" approuvée avec succès.";
                        }else{
                            Compte destinataire = compteRepository.findById(transaction.getDestinataire().getIdCompte()).get();
                            if (destinataire.getType().equals(TypeCompte.EPARGNE)){
                                destinataire.setSolde(destinataire.getSolde() + transaction.getMontant());
                            }else {
                                destinataire.setSolde(destinataire.getSolde() + (transaction.getMontant()-2));
                            }
                            compteRepository.save(destinataire);
                            transactionRepository.save(transaction);
                            return "Versement de "+transaction.getMontant()+" DT vers le compte "+destinataire.getIdCompte()+" approuvée avec succès.";

                        }
                    }
                }
            }

        }
    }*/

    @Override
    public String ajouterVirement(Transaction transaction) {
        transaction.setExpediteur(compteRepository.findByCode(transaction.getExpediteur().getCode()));
        transaction.setDestinataire(compteRepository.findByCode(transaction.getDestinataire().getCode()));
        if (transaction.getType().equals(TypeTransaction.VIREMENT) && (transaction.getExpediteur().getType().equals(TypeCompte.EPARGNE))) {
            return "On ne peut pas faire un virement à partir d’un compte épargne";
        } else {
            if ( !(transaction.getExpediteur().getType().equals(TypeCompte.EPARGNE)) && (transaction.getExpediteur().getSolde() < (transaction.getMontant() + 3))||(transaction.getExpediteur().getType().equals(TypeCompte.EPARGNE)) && (transaction.getExpediteur().getSolde() < transaction.getMontant())) {

                return "On ne peut pas faire un virement : Solde insuffisant";
            } else {
                Compte expediteur = compteRepository.findByCode(transaction.getExpediteur().getCode());
                Compte destinataire = compteRepository.findByCode(transaction.getDestinataire().getCode());
                expediteur.setSolde(expediteur.getSolde() - (transaction.getMontant() + 3));
                destinataire.setSolde(destinataire.getSolde() + transaction.getMontant());
                transaction.setDateTransaction(new Date());
                compteRepository.save(expediteur);
                compteRepository.save(destinataire);
                transactionRepository.save(transaction);
                return "VIREMENT de "+transaction.getMontant()+" DT de compte "+expediteur.getIdCompte()+" vers le compte "+destinataire.getIdCompte()+" approuvée avec succès";
            }
        }
    }

    @Override
    public String ajouterRetrait(Transaction transaction) {
        transaction.setExpediteur(compteRepository.findByCode(transaction.getExpediteur().getCode()));
        if ((transaction.getType().equals(TypeTransaction.RETRAIT)&&(transaction.getExpediteur().getSolde() < transaction.getMontant()+2))){
            return "On ne peut pas faire un retrait : Solde insuffisant";
        } else {
            Compte expediteur = compteRepository.findByCode(transaction.getExpediteur().getCode());
            expediteur.setSolde(expediteur.getSolde() - (transaction.getMontant() + 2));
            compteRepository.save(expediteur);
            transactionRepository.save(transaction);
            return "RETRAIT de "+transaction.getMontant()+" DT de compte "+expediteur.getIdCompte()+" approuvée avec succès.";
        }
    }

    @Override
    public String ajouterVersement(Transaction transaction) {
        transaction.setDestinataire(compteRepository.findByCode(transaction.getDestinataire().getCode()));
        Compte destinataire = compteRepository.findByCode(transaction.getDestinataire().getCode());
        if (destinataire.getType().equals(TypeCompte.EPARGNE)){
            destinataire.setSolde(destinataire.getSolde() + transaction.getMontant());
        }else {
            destinataire.setSolde(destinataire.getSolde() + (transaction.getMontant()-2));
        }
        compteRepository.save(destinataire);
        transactionRepository.save(transaction);
        return "Versement de "+transaction.getMontant()+" DT vers le compte "+destinataire.getIdCompte()+" approuvée avec succès.";
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void getAllTransactionByDate() {
        for (Transaction transaction: transactionRepository.findByDateTransaction(new Date())) {
            log.info(transaction.toString());
        }
    }

    @Override
    public List<Transaction> getAllTransactionByBankId(long idBank) {
        return transactionRepository.findByBankIdBank(idBank);
    }

    @Override
    public List<Transaction> getAllTransactionByTypeAndCompte(TypeTransaction type, long idCompte) {
        return transactionRepository.findByDestinataireIdCompteOrExpediteurIdCompteAndType(idCompte,idCompte,type);
    }
}
