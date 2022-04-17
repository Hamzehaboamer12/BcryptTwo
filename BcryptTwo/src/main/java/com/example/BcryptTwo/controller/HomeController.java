package com.example.BcryptTwo.controller;


import com.example.BcryptTwo.model.cust;
import com.example.BcryptTwo.model.Posts;
import com.example.BcryptTwo.repository.postsRepo;
import com.example.BcryptTwo.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    userRepo UserRepository;
    @Autowired
    postsRepo postsRepo;



    @GetMapping("/home/{username}")
    public String getHomepage(@PathVariable String username, Model m) {
        cust siteUserPage = UserRepository.findByUsername(username);
        m.addAttribute("username", username.toUpperCase());
        m.addAttribute("siteUser", siteUserPage);
        m.addAttribute("posts", siteUserPage.getPostsOfThisUser());
        List<Posts> posts = postsRepo.findAll();
        m.addAttribute("posts", posts);
        return "home";
    }

    @PostMapping("/add-post")
    public RedirectView addPost(long siteUserId, String text) {
        cust postsByUser = UserRepository.getById(siteUserId);
        Posts postsToAdd = new Posts(text);
        postsToAdd.setPostsByUser(postsByUser);
        postsRepo.save(postsToAdd);
        String username = postsByUser.getUsername();

        return new RedirectView("/home/" + username);

    }
}
