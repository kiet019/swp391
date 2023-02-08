package com.example.projectswp.controller;

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
    ItemsRepository itemsRepository = new ItemsRepository();

    @GetMapping("/{itemID}")
    public ResponseEntity<Items> getCategory(@PathVariable int id) {
        Items category = itemsRepository.getItem(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("")
    public ResponseEntity<List<Items>> getItem() {
        List<Items> item = itemsRepository.getItems();
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("")
    public ResponseEntity<Items> insertCategory(@RequestBody Items addItems) {
        boolean result = itemsRepository.addItems(addItems);
        URI uri = URI.create("localhost:8080/api/items/" + itemsRepository.getLastItem().getID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("")
    public ResponseEntity<Items> updateItems(@RequestBody Items updateItem) {
        boolean result = false;
        if (itemsRepository.getItem(updateItem.getID()) != null) {
            result = itemsRepository.updateItems(updateItem);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
