package com.example.projectswp.data_view_model.blog;

import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.ultil.Ultil;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class BlogListVM {
    private int totalRecord;
    private int pageNumber;
    private int pageSize;
    private List<Blog> listModel;
    private BlogListVM(){

    }
    public static BlogListVM create(List<Blog> list, int pageNumber, int pageSize) {
        if (list == null) {
            return null;
        }
        List<Blog> blogPage = Ultil.getSubListByPage(list, pageNumber, pageSize);
        BlogListVM blogListVM = new BlogListVM();
        blogListVM.setTotalRecord(list.size());
        blogListVM.setPageNumber(pageNumber);
        blogListVM.setPageSize(pageSize);
        blogListVM.setListModel(blogPage);
        return blogListVM;
    }
}
