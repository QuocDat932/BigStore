package codejava.Controller;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.RemoveAuthorizedClientOAuth2AuthorizationFailureHandler.OAuth2AuthorizedClientRemover;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class LoginGG {
	 Principal a =  null; 
	
    @GetMapping
    public String welcome() {
        return "Welcome to Google !!";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        System.out.println("username : " + principal.getName());
        System.out.println("username : " + principal.hashCode());
        System.out.println("username : " + principal.toString() );
        this.a = principal;
        return principal;
    }

    @GetMapping("/loginGG")
    public Principal get() {
    	String[] args =   {
    			"asd","asd"
    			
    	} ;
		SpringApplication.run(LoginGG.class, args); 
        
       return this.a;
    }

}
