package com.example.BcryptTwo.controller;


import com.example.BcryptTwo.model.cust;
import com.example.BcryptTwo.repository.userRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class userController {

    @Autowired
    userRepo UserRepo;

    @GetMapping("/")
    public String getLoginPage(){
        return "index.html";
    }


    @PostMapping("/login")
    @ResponseBody
    public RedirectView login(String username, String password){
        cust loggingInUser = UserRepo.findByUsername(username);
        if (loggingInUser == null){
            return new RedirectView("/");
        }

        boolean isPasswordCorrect = BCrypt.checkpw(password, loggingInUser.getPassword());

        if (!isPasswordCorrect)
        {
            return new RedirectView("/");
        }
        else
        {
            return new RedirectView("/home/" + username);
        }

    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        cust newUser = new cust(username, hashedPassword);
        UserRepo.save(newUser);

        return new RedirectView("/");
    }
}
