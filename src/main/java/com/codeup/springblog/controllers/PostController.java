package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.codeup.springblog.repositories.PostRepository;
import services.EmailService;
import services.StringService;

import java.util.List;


@Controller

public class PostController {

    private PostRepository postsDao;
    private UserRepository usersDao;



    public PostController(PostRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }

    private EmailService emailService;

    public PostController(PostRepository postsDao, StringService stringService, UserRepository usersDao,
                          EmailService emailService
    ){
        this.stringService = stringService;
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();

        model.addAttribute("stringService", stringService);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Post post = postsDao.findById(id);
        User user = usersDao.findById(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        model.addAttribute("email", user.getEmail());
        return "posts/show";
    }
    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body, @RequestParam String email) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost.setUser(usersDao.findById(1));
        postsDao.save(newPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        // delete post
        postsDao.deleteById(id);
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postsDao.findById(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }
    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        // post object from database
        Post editedPost = postsDao.findById(id);
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());
        postsDao.save(editedPost);
        return "redirect:/posts";
    }
}

