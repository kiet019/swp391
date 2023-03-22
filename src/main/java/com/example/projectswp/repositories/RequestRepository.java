package com.example.projectswp.repositories;

import com.example.projectswp.model.Items;
import com.example.projectswp.model.Request;
import com.example.projectswp.repositories.rowMapper.RequestRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RequestRepository {
    public static final RequestRowMapper REQUEST_ROW_MAPPER = new RequestRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ItemsRepository itemsRepository;
    @Autowired
    OrderRepository orderRepository;

    public List<Request> getRequestByItemId(int itemId) {
        String sql = "SELECT * FROM dbo.Requests WHERE ItemID = ?";
        List<Request> requestList = jdbcTemplate.query(sql, REQUEST_ROW_MAPPER, itemId);
        return requestList;
    }
    public List<Request> getRequestByUserId(int userId) {
        String sql = "SELECT * FROM dbo.Requests WHERE UserID = ? ";
        List<Request> requestList = jdbcTemplate.query(sql, REQUEST_ROW_MAPPER, userId);
        return requestList;
    }

    public Request getRequestByID(int requestID) {
        String sql = "SELECT * FROM dbo.Requests WHERE RequestID = ?";
        List<Request> requestList= jdbcTemplate.query(sql,REQUEST_ROW_MAPPER,requestID);
        return requestList.size() != 0 ? requestList.get(0) : null;
    }

    public boolean addRequest(int userID, int itemQuality, int itemID, String address, String note, Date created, int status, Date update) {
        int check = 0;
        String sql = "insert into dbo.Requests([UserID], [ItemQuantity], [ItemID], [Address], [Note], [DateCreate], [Status], [DateChangeStatus])\n" +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        check = jdbcTemplate.update(sql, userID, itemQuality, itemID, address, note, created, status, update);
        return  check > 0;
    }

    public boolean updateStatus(int requestId,int status) {
        String sql = "UPDATE dbo.Requests SET Status = ?, DateChangeStatus = ? WHERE RequestID = ?";
        int rowAffected = jdbcTemplate.update(sql,status, Ultil.getCurrentDate(), requestId);
        return rowAffected > 0;
    }
    public boolean acceptRequest(Request request) {
        Items items = itemsRepository.getItem(request.getItemID());
        if(items != null) {
            if(items.getItemShareAmount() < request.getItemQuantity()) {
                return false;
            }
            if (items.getItemShareAmount() == request.getItemQuantity()) {
                items.setStatus(false);
            }
            items.setItemShareAmount(items.getItemShareAmount() - request.getItemQuantity());

        }
        if(itemsRepository.updateItems(items) && updateStatus(request.getRequestID(), 2)) {
            Request currentRequest = getRequestByID(request.getRequestID());
            if(currentRequest != null) {
                orderRepository.createOrder(currentRequest);
            }
            return true;
        }
        return false;
    }

    public boolean denyOtherRequestWhichPassItemQuantity(Request request) {
        Items items = itemsRepository.getItem(request.getItemID());
        if(items != null) {
            return false;
        }
        List<Request> requestList = getRequestByItemId(items.getItemID());
        if(requestList == null || requestList.size() == 0)
            return true;

        for(Request curRequest: requestList) {
            if (items.isStatus() || items.getItemShareAmount() == 0){
                denyRequest(request.getRequestID());
            }
            if(request.getItemQuantity() > items.getItemShareAmount()) {
                denyRequest(request.getRequestID());
            }
        }
        return true;
    }
    public boolean denyRequest(int requestId){
        return updateStatus(requestId, 2);
    }



}
