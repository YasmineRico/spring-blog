package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    @GetMapping
    @ResponseBody
    public String allAlbums(){
        return "This is where you would view all albums";
    }

    //to get one album to show up
    @GetMapping("/{id}")
    @ResponseBody
    public String showOneAlbum(@PathVariable long id){
        return "This is where you would view one album, with the id " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createAlbumsForm(){
        return "this is where you will view the form to create an album in the db";
    }

    @PostMapping
    @ResponseBody
    public String createAlbum(){
        return "This is where the logic would be for when the create albums form is submitted";
    }
}
