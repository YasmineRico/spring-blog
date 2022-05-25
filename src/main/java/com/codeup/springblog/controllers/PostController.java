package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.EmailService;

@Controller
public class PostController {

    private PostRepository postsDao;
    private UserRepository usersDao;

    @Autowired
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Post post = postsDao.getById(id);
//        User user = usersDao.getOne(id);

        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
//        model.addAttribute("email", user.getEmail());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setUser(loggedInUser);
        postsDao.save(newPost);

        String emailSubject = "New Blog Post";
        String emailBody = "Your post '" + title + "' is now live.";
        emailService.prepareAndSend(newPost, emailSubject, emailBody);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.getId() == postsDao.getById(id).getUser().getId())
            // delete post
            postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post postToEdit = postsDao.getById(id);

        if (loggedInUser.getId() == postsDao.getById(id).getUser().getId())
            model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        // post object from database currently
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post editedPost = postsDao.getById(id);

        if (loggedInUser.getId() == postsDao.getOne(id).getUser().getId()) {
            editedPost.setTitle(post.getTitle());
            editedPost.setBody(post.getBody());
            postsDao.save(editedPost);
        }
        return "redirect:/posts";
    }
}