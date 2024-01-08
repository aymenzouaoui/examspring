package tn.esprit.spring.examen.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.examen.entities.Bank;
import tn.esprit.spring.examen.entities.Compte;
import tn.esprit.spring.examen.entities.Transaction;
import tn.esprit.spring.examen.entities.enumerations.TypeTransaction;
import tn.esprit.spring.examen.services.IExamenService;

import java.util.List;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
public class ExamenController {

    IExamenService examenService;

    @PostMapping("addBank")
    public Bank ajouterBank(@RequestBody Bank bank){
        return examenService.ajouterBank(bank);
    }
    @PostMapping("addcompte")
    public Compte ajouterCompte(@RequestBody Compte compte, @RequestParam("agenceBank") String agenceBank){
        return examenService.ajouterCompte(compte,agenceBank);
    }
    /*@PostMapping("addTransaction")
    public String ajouterTransaction(@RequestBody Transaction transaction){
        return examenService.ajouterTransaction(transaction);
    }*/
 @PostMapping("addVirement")
    public String ajouterVirement(@RequestBody Transaction transaction){
        return examenService.ajouterVirement(transaction);
    }
    @PostMapping("addRetrait")
    public String ajouterRetrait(@RequestBody Transaction transaction){
        return examenService.ajouterRetrait(transaction);
    }
 @PostMapping("addVersement")
    public String ajouterVersement(@RequestBody Transaction transaction){
        return examenService.ajouterVersement(transaction);
    }
    @GetMapping("getTransactionByBank")
    public List<Transaction> transactionByBank(@RequestParam("idBank") long idBank){
        return examenService.getAllTransactionByBankId(idBank);
    }
    @GetMapping("getTransactionByCompteAndType")
    public List<Transaction> transactionByCompteAndType(@RequestParam("idCompte") long idCompte, @RequestParam("type")TypeTransaction type){
        return examenService.getAllTransactionByTypeAndCompte(type, idCompte);
    }
}
