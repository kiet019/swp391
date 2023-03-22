package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.Item.ItemDeleteVM;
import com.example.projectswp.data_view_model.blog.BlogDenyVM;
import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
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
@RequestMapping("/api/item")
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ItemController {
    @Autowired
    ItemsRepository itemsRepository;
    @GetMapping("")
    public ResponseEntity<List<Items>> getItems() {
        List<Items> item = itemsRepository.getItems();
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Items> createItem(@RequestBody Items addItems) {
        boolean result = itemsRepository.addItems(addItems);
        URI uri = URI.create("localhost:8080/api/Item/" + itemsRepository.getLastItem().getItemID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @GetMapping("/getAllBriefItemAndBriefRequest")
        public ResponseEntity<List<Items>> getAllBriefItemAndBriefRequest(@RequestParam boolean share, @RequestParam boolean status, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Items> item = itemsRepository.getAllBriefItemAndBriefRequest(share, status, pageNumber, pageSize);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getItemDetail")
    public ResponseEntity<Items> getItemDetail(@RequestParam int itemID) {
        Items items = itemsRepository.getItemDetail(itemID);
        return items != null ? ResponseEntity.ok(items) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getBriefItemByOrBriefRequestUserID")
    public ResponseEntity<List<Items>> getBriefItemByUserId(@RequestParam int userID, @RequestParam boolean status, @RequestParam boolean share, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Items> item = itemsRepository.getBriefItemByOrBriefRequestUserID(userID, status, share, pageNumber, pageSize);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/searchBriefItemByTitle")
    public ResponseEntity<List<Items>> searchBriefItemByUserId(@RequestParam String itemTitle,@RequestParam boolean status) {
        List<Items> item = itemsRepository.searchBriefItemByItemTitle(itemTitle, status);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/searchBriefItemBySubCategoryID")
    public ResponseEntity<List<Items>> searchBriefItemBySubCategoryID(@RequestParam int subCategoryID, @RequestParam boolean status) {
        List<Items> item = itemsRepository.searchBriefItemBySubCategoryID(subCategoryID, status);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/searchBriefItemOrBriefRequestByCategoryID")
        public ResponseEntity<List<Items>> searchBriefItemOrBriefRequestByCategoryID(@RequestParam int categoryID, @RequestParam boolean status, @RequestParam boolean share, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Items> item = itemsRepository.searchBriefItemByCategoryID(categoryID, status, share, pageNumber, pageSize);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ReturnMessage> blogDeny(@RequestBody ItemDeleteVM itemDeleteVM){
        boolean isDeleted = itemsRepository.deleteItem(itemDeleteVM);
        return isDeleted ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateItem(@RequestBody Items item) {
        try {
            boolean result = false;
            if (itemsRepository.getItem(item.getItemID()) != null) {
                result = itemsRepository.updateItems(item);
            }
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }
    @GetMapping("/getAllMyBriefItemAndBriefRequest")
    public ResponseEntity<List<Items>> getAllMyBriefItemAndBriefRequest(@RequestParam int userID, @RequestParam boolean share) {
        List<Items> item = itemsRepository.getAllBriefItemAndBriefRequestByUserID(userID, share);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getAllShareFree")
    public ResponseEntity<List<Items>> getAllShareFree(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam int userID) {
        List<Items> item = itemsRepository.getAllShareFree(pageNumber, pageSize, userID);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getAllMyShareAndRequest")
    public ResponseEntity<List<Items>> getAllMyShareAndRequest(@RequestParam boolean share, @RequestParam boolean status, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Items> item = itemsRepository.getAllMyShareAndRequest(share, status, pageNumber, pageSize);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getListAllOtherPersonRequestItem")
    public ResponseEntity<List<Items>> getListAllOtherPersonRequestItem(@RequestParam boolean share, @RequestParam boolean status, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Items> item = itemsRepository.getListAllOtherPersonRequestItem(share, status, pageNumber, pageSize);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getListAllMyRequestItem")
    public ResponseEntity<List<Items>> getListAllMyRequestItem(@RequestParam boolean share, @RequestParam boolean status, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Items> item = itemsRepository.getListAllMyRequestItem(share, status, pageNumber, pageSize);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
