package com.grupp2javaee.catforum;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class CatforumApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatforumApplication.class, args);
	}

	@Autowired
	private AccountRepository accountRepository;

	
	@Override
	public void run(String... args) throws Exception {

		//Sparar account
		accountRepository.save(new Account("Joel", "KattJulle", "joel@katten.se", "abc123", "Jag heter Joel, och jag gillar Katter och långa promenader. Gärna samtidigt."));
		accountRepository.save(new Account("Katarina", "Kattis", "kattis@katten.se", "abc123","Jag heter Kattis, och jag har fyra (4) katter och en uggla"));

		//Hämta alla konton

		List<Account> accountList = accountRepository.findAll();
		System.out.println("(\n============ Lista av alla kattkompisar ============");
		System.out.println(accountList);

	}

}
