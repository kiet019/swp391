package com.example.projectswp.data_view_model.cartdetail;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDetailConfirmVM {
    private String note;
    private List<Integer> listCartDetailID;
}
