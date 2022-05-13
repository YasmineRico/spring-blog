package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class PostController{

    @GetMapping("/posts")
    @ResponseBody
    public String getPosts() {
        return "Posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getSinglePost(@PathVariable int id) {
        return "view an individual post, id=" + id + "!";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }

}

