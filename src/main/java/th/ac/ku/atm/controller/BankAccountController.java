package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService accountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.accountService = bankAccountService ;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", accountService.getBankAccounts()) ;
        //model.addAttribute("bankaccounts", bankAccountService.getBankAccounts()) ;

        return "bankaccount" ;
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        accountService.openBankAccount(bankAccount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts()) ;

        return "redirect:bankaccount" ;
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", account) ;

        return "bankaccount-edit" ;
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {
        accountService.editBankAccount(bankAccount) ;
        model.addAttribute("bankaccounts", accountService.getBankAccounts()) ;
        return "redirect:/bankaccount" ;
    }

    @GetMapping("/delete/{id}")
    public String getDeleteBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = accountService.getBankAccount(id) ;
        model.addAttribute("bankAccount", account) ;

        return "bankaccount" ;
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                                @ModelAttribute BankAccount bankAccount,
                                Model model) {
        accountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts()) ;
        return "bankaccount" ;
    }

//    @PostMapping
//    public String registerBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
//        bankAccountService.createBankAccount(bankAccount);
//
//        model.addAttribute("allBankAccount", bankAccountService.getBankAccount()) ;
//        return "redirect:bankaccount" ;
//    }

}
