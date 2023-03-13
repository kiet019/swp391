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
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ItemController {
    @Autowired
    ItemsRepository itemsRepository;
    @GetMapping("")
    public ResponseEntity<List<Items>> getItems() {
        List<Items> item = itemsRepository.getItems();
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/Item/CreateItem")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Items> createItem(@RequestBody Items addItems) {
        boolean result = itemsRepository.addItems(addItems);
        URI uri = URI.create("localhost:8080/api/Item/" + itemsRepository.getLastItem().getID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @GetMapping("/GetAllBriefItemAndBriefRequest")
        public ResponseEntity<List<Items>> getAllBriefItemAndBriefRequest(@PathVariable boolean share, @PathVariable boolean status) {
        List<Items> item = itemsRepository.getAllBriefItemAndBriefRequest(share, status);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/Item/GetItemDetail")
    public ResponseEntity<Items> getItemDetail(@PathVariable int itemID) {
        Items items = itemsRepository.getItemDetail(itemID);
        return items != null ? ResponseEntity.ok(items) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/Item/GetRequestDetail")
    public ResponseEntity<Items> getRequestDetail(@PathVariable int itemID) {
        Items items = itemsRepository.getItemDetail(itemID);
        return items != null ? ResponseEntity.ok(items) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/Item/GetBriefItemByAndBriefRequestUserID")
    public ResponseEntity<List<Items>> getBriefItemByUserId(@PathVariable int userID, @PathVariable boolean status, @PathVariable boolean share) {
        List<Items> item = itemsRepository.getBriefItemByAndBriefRequestUserID(userID, status, share);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/Item/SearchBriefItemByTitle")
    public ResponseEntity<List<Items>> searchBriefItemByUserId(@PathVariable String itemTitle,@PathVariable boolean status) {
        List<Items> item = itemsRepository.searchBriefItemByItemTitle(itemTitle, status);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/Item/SearchBriefItemBySubCategoryID")
    public ResponseEntity<List<Items>> searchBriefItemBySubCategoryID(@PathVariable int subcategoryID, @PathVariable boolean status) {
        List<Items> item = itemsRepository.searchBriefItemBySubCategoryID(subcategoryID, status);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/Item/SearchBriefItemByCategoryID")
        public ResponseEntity<List<Items>> searchBriefItemByCategoryID(@PathVariable int categoryID, @PathVariable boolean status, @PathVariable boolean share) {
        List<Items> item = itemsRepository.searchBriefItemByCategoryID(categoryID, status, share);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/Item/DeleteItem/")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Items> deleteItem(@RequestBody int itemID){
        boolean result = itemsRepository.deleteItem(itemID);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
    @PutMapping("/Item/UpdateItem/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Items> updateItem(@RequestBody Items item) {
        try {
            boolean result = false;
            if (itemsRepository.getItem(item.getID()) != null) {
                result = itemsRepository.updateItems(item);
            }
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
