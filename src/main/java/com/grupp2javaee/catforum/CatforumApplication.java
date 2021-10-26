package com.grupp2javaee.catforum;

import com.grupp2javaee.catforum.model.User;
import com.grupp2javaee.catforum.model.UserRepository;
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

	//@Autowired
	//private AccountRepository accountRepository;

	@Bean //Behövs Bean? Connection med MongDB
	CommandLineRunner init(UserRepository accountRepository) {

		return args -> {

				//Sparar account
				accountRepository.save(new User("Joel", "KattJulle", "joel@katten.se", "Jag heter Joel, och jag gillar Katter och långa promenader. Gärna samtidigt."));
				accountRepository.save(new User("Katarina", "Kattis", "kattis@katten.se", "Jag heter Kattis, och jag har fyra (4) katter och en uggla"));
				//Hämta alla konton
				List<User> userList = accountRepository.findAll();
				System.out.println("(\n============ Lista av alla kattkompisar ============");
				System.out.println(userList);

			/*Role userRole = accountRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}*/
		};
	}

}
