package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/{itemID}")
    public ResponseEntity<Items> getCategory(@PathVariable int itemID) {
        Items items = itemsRepository.getItem(itemID);
        return items != null ? ResponseEntity.ok(items) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("")
    public ResponseEntity<List<Items>> getItem() {
        List<Items> item = itemsRepository.getItems();
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("")
    public ResponseEntity<Items> createItem(@RequestBody Items addItems) {
        boolean result = itemsRepository.addItems(addItems);
        URI uri = URI.create("localhost:8080/api/items/" + itemsRepository.getLastItem().getID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/{itemID}")
    public ResponseEntity<Items> updateItem(@PathVariable int itemID, @RequestBody Items item) {
        boolean result = itemsRepository.updateItems(itemID, item);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/{itemID}")
    public ResponseEntity<Items> deleteItem(@PathVariable int itemID){
        boolean result = itemsRepository.deleteImage(itemID);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
