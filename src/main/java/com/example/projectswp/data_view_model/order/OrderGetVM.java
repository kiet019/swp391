package com.example.projectswp.data_view_model.order;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderGetVM {
    private int pageNumber;
    private int pageSize;
    private Integer OrderStatus;

}
