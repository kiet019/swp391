package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.model.Images;
import com.example.projectswp.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController    {
    @Autowired
    ImageRepository imageRepository;
    @GetMapping("")
    public ResponseEntity<List<Images>> getImages() {
        List<Images> images = imageRepository.getImages();
        return images != null? ResponseEntity.ok(images) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{imageID}")
    public ResponseEntity<Images> getImage(@PathVariable int imageID) {
        Images images = imageRepository.getImage(imageID);
        return images != null? ResponseEntity.ok(images) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<Images> createImage(@RequestBody Images images){
        boolean result = imageRepository.addImage(images);
        URI uri = URI.create("localhost:8080/api/blogs/" + imageRepository.getLastImage() );
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PutMapping("/{imageID}")
    public ResponseEntity<Images> updateImage(@PathVariable int imageID, @RequestBody Images images){
        boolean result = imageRepository.updateImage(imageID, images);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/{imageID}")
    public ResponseEntity<Images> deleteImage(@PathVariable int imageID){
        boolean result = imageRepository.deleteImage(imageID);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
