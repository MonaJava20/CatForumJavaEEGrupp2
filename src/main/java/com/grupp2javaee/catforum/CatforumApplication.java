package com.grupp2javaee.catforum;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import com.grupp2javaee.catforum.model.Role;
import com.grupp2javaee.catforum.model.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class CatforumApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatforumApplication.class, args);
	}

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		//Dessa kodrader raderar allt i databasen som gjordes vid den tidigare runnen
		//accountRepository.deleteAll();
		//roleRepository.deleteAll();

		//Detta skapar Rollen USER i MongoDB
		Role userRole = roleRepository.findByRole("USER");
		if (userRole == null) {
			Role newUserRole = new Role();
			newUserRole.setRole("USER");
			roleRepository.save(newUserRole);
		}

		//Detta skapar Rollen ADMIN i MongoDB
		Role adminRole = roleRepository.findByRole("ADMIN");
		if (adminRole == null) {
			Role newAdminRole = new Role();
			newAdminRole.setRole("ADMIN");
			roleRepository.save(newAdminRole);
		}

		/*
		//Sparar account
		accountRepository.save(new Account("Joel", "KattJulle", "joel@katten.se", "password", "Jag heter Joel, och jag gillar Katter och långa promenader. Gärna samtidigt.", userRole));
		accountRepository.save(new Account("Katarina", "Kattis", "kattis@katten.se", "password", "Jag heter Kattis, och jag har fyra (4) katter och en uggla", ""));
		accountRepository.save(new Account("Joel", "HundJulle", "joel@hunden.se", "password", "Jag heter Joel, men jag gillar Hundar och korta promenader. Aldrig samtidigt.", "USER"));
		*/

		//Hämtar alla konton
		List<Account> accountList = accountRepository.findAll();
		System.out.println("(\n============ Lista av alla kattkompisar ============");
		System.out.println(accountList.toString());

		//Hämtar unika account
		System.out.println("Här hittar vi kattkompisen med findByName('Joel'):");
		System.out.println("--------------------------------");
		for (Account account : accountRepository.findByName("Joel")) {
			System.out.println(account.toString());
		}

		System.out.println("Customers found with findByUsername('Kattis'):");
		System.out.println("--------------------------------");
		System.out.println(accountRepository.findByUsername("Kattis").toString());


	}

}
