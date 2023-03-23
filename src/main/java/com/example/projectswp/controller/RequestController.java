package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.data_view_model.request.RequestIdVM;
import com.example.projectswp.model.Items;
import com.example.projectswp.model.Request;
import com.example.projectswp.repositories.ItemsRepository;
import com.example.projectswp.repositories.RequestRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class RequestController {
    public static final int ACCEPT_STATUS = 1;
    public static final int DENY_STATUS = 2;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ItemsRepository itemsRepository;
    @GetMapping("/item/request/sharer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getRequestByItem(){
        int uid = Ultil.getUserId();
        List<Integer> itemIdList = getListItemIDbyUserID(uid);
        List<Request> requestList = new ArrayList<>();
        for (int itemId : itemIdList) {
            List<Request> requestsOfItem = requestRepository.getRequestByItemId(itemId);
            addRange(requestList, requestsOfItem);
        }
        return ResponseEntity.ok(requestList);
    }
    @GetMapping("/request/reciever")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getRequestByUserId(){
        int uid = Ultil.getUserId();
        List<Request> requestList = requestRepository.getRequestByUserId(uid);
        return ResponseEntity.ok(requestList);
    }
    @GetMapping("/request")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getRequest(@RequestParam int RequestId){
        Request request = requestRepository.getRequestByID(RequestId);
        if(request != null)
            return ResponseEntity.ok(request);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/requestdetail/accept")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> acceptRequest(@RequestBody RequestIdVM requestIdVM) {
        Request request = requestRepository.getRequestByID(requestIdVM.getRequestId());
        if(request == null) {
            return ResponseEntity.badRequest().body(ReturnMessage.create("error at request not found"));
        }
        boolean isAccepted = requestRepository.acceptRequest(request);
        if(isAccepted){
            requestRepository.denyOtherRequestWhichPassItemQuantity(request);
            return ResponseEntity.ok(ReturnMessage.create("success"));
        }
        return ResponseEntity.badRequest().body(ReturnMessage.create("error at AcceptRequestDetail"));
    }
    @PatchMapping("/requestdetail/deny")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> denyRequest(@RequestBody RequestIdVM requestIdVM) {
        boolean isUpdated = requestRepository.updateStatus(requestIdVM.getRequestId(), DENY_STATUS);
        if(isUpdated)
            return ResponseEntity.ok(ReturnMessage.create("success"));
        return ResponseEntity.notFound().build();
    }

    private List<Integer> getListItemIDbyUserID(int userID) {
        List<Integer> listItemID = new ArrayList<>();
        List<Items> itemsList = itemsRepository.getItems();
        for(Items items : itemsList) {
            if(items.getUserId() == userID) {
                listItemID.add(items.getItemID());
            }
        }
        return listItemID;
    }
    private void addRange(List<Request> requestList, List<Request> requestListToAdd) {
        if(requestList == null || requestListToAdd == null)
            return;

        for(Request request : requestListToAdd) {
            requestList.add(request);
        }
    }
}
