package com.example.springbootblogapplication.config;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.models.Authority;
import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.repositories.AuthorityRepository;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;



    //tikrina ar sukurti pradiniai irasai ,jei nera , sukuria
    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();



            //user acc
            account1.setFirstName("user_first");
            account1.setLastName("user_last");
            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

//addmin acc
            account2.setFirstName("admin_first");
            account2.setLastName("admin_last");
            account2.setEmail("admin.admin@domain.com");
            account2.setPassword("password");

            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);
//pirmas postas
            Post post1 = new Post();
            post1.setTitle("Mano isvyka");
            post1.setBody("Nubėgau į autobusų stotį, nes man reikėjo susitikti su grupe, kuri vyksta kartu į kalnus. Kai atvykau, sutikau keletą senų draugų ir keletą naujų veidų. Kalnų kelionė visada yra kažkas ypatingo, o aš pasiruošusi šiam nuotykiui.\n" +
                    "\n" +
                    "Autobuse visi džiaugėsi ir kalbėjosi, kai aš žiūrėjau pro langą ir grožėjausi kraštovaizdžiu. Po kelių valandų kelionės, mes pasiekėme kalnus.");
            post1.setAccount(account1);


            //antras postas
            Post post2 = new Post();
            post2.setTitle("Mano dienaa kalnuose");
            post2.setBody("Kalnuose oras buvo gaivus, ir aš jautėsi kaip mano širdis plaka greičiau iš susijaudinimo. Mes pradėjome savo ėjimą kalnų taku ir stebėjome kaip aplinka keičiasi, tampa šaltesnė ir vaizdai įspūdingesni.\n" +
                    "\n" +
                    "Po keletos valandų ėjimo, mes pasiekėme viršūnę, ir aš negalėjau patikėti savo akimis. Vaizdas buvo tiesiog kvapą gniaužiantis. Mes visi susėdome ir atsigulėme ant žolės, grožėdamiesi debesimis ir tolumoje matomais kalnais.");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }

}
