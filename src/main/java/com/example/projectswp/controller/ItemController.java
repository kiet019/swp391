package com.example.projectswp.controller;

import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/Item")
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ItemController {
    @Autowired
    ItemsRepository itemsRepository;
    @GetMapping("/{itemID}")
    public ResponseEntity<Items> getItem(@PathVariable int itemID) {
        Items items = itemsRepository.getItem(itemID);
        return items != null ? ResponseEntity.ok(items) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("")
    public ResponseEntity<List<Items>> getItems() {
        List<Items> item = itemsRepository.getItems();
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/CreateItem")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Items> createItem(@RequestBody Items addItems) {
        boolean result = itemsRepository.addItems(addItems);
        URI uri = URI.create("localhost:8080/api/Item/" + itemsRepository.getLastItem().getID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @GetMapping("/GetAllBriefItemAndBriefRequest/{share}")
    public ResponseEntity<List<Items>> getAllBriefItemAndBriefRequest(@PathVariable boolean share) {
        List<Items> item = itemsRepository.getAllBriefItemAndBriefRequest(share);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/GetItemDetail/{itemID}")
    public ResponseEntity<Items> getItemDetail(@PathVariable int itemID) {
        Items items = itemsRepository.getItemDetail(itemID);
        return items != null ? ResponseEntity.ok(items) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/GetBriefItemByAndBriefRequestUserID/{userID}")
    public ResponseEntity<List<Items>> getBriefItemByUserId(@PathVariable int userID) {
        List<Items> item = itemsRepository.getBriefItemByUserId(userID);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/SearchBriefItemByTitle/{itemTitle}")
    public ResponseEntity<List<Items>> searchBriefItemByUserId(@PathVariable String itemTitle) {
        List<Items> item = itemsRepository.searchBriefItemByItemTitle(itemTitle);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/SearchBriefItemBySubCategoryID/{subcategoryID}")
    public ResponseEntity<List<Items>> searchBriefItemBySubCategoryID(@PathVariable int subcategoryID) {
        List<Items> item = itemsRepository.searchBriefItemBySubCategoryID(subcategoryID);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/SearchBriefItemByCategoryID/{categoryID}")
    public ResponseEntity<List<Items>> searchBriefItemByCategoryID(@PathVariable int categoryID) {
        List<Items> item = itemsRepository.searchBriefItemByCategoryID(categoryID);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/GetAllMyBriefItemAndBriefRequest/{userID}&{share}")
    public ResponseEntity<List<Items>> searchBriefItemBySubCategoryID(@PathVariable int userID, @PathVariable boolean share) {
        List<Items> item = itemsRepository.getAllBriefItemAndBriefRequestByUserID(userID, share);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("/UpdateItem/{itemID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Items> updateItem(@PathVariable int itemID, @RequestBody Items item) {
        boolean result = itemsRepository.updateItems(itemID, item);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @DeleteMapping("/DeleteItem/{itemID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Items> deleteItem(@PathVariable int itemID){
        boolean result = itemsRepository.deleteImage(itemID);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
