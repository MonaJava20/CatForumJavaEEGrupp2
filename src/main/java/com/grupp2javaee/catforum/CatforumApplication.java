package com.grupp2javaee.catforum;

import com.grupp2javaee.catforum.model.Role;
import com.grupp2javaee.catforum.model.RoleRepository;
import com.grupp2javaee.catforum.model.User;
import com.grupp2javaee.catforum.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CatforumApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(CatforumApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		return (args) -> {
			Role adminRole = roleRepository.findByRole("ADMIN");
			Role userRole;
			if (adminRole == null) {
				userRole = new Role();
				userRole.setRole("ADMIN");
				roleRepository.save(userRole);
			}

			userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}

		};
	}



	/*@Autowired
	private UserRepository accountRepository;

	@Override
	public void run(String... args) throws Exception {

		//Sparar account
		accountRepository.save(new User("Joel", "KattJulle", "joel@katten.se", "abc123", "Jag heter Joel, och jag gillar Katter och långa promenader. Gärna samtidigt."));
		accountRepository.save(new User("Katarina", "Kattis", "kattis@katten.se", "abc123","Jag heter Kattis, och jag har fyra (4) katter och en uggla"));

		//Hämta alla konton

		List<User> accountList = accountRepository.findAll();
		System.out.println("(\n============ Lista av alla kattkompisar ============");
		System.out.println(accountList);

	}*/

}
