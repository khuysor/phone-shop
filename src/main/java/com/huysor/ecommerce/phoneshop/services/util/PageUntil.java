package com.huysor.ecommerce.phoneshop.services.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUntil {
    int DEFAULT_PAGE_LIMIT=5;
    int DEFAULT_PAGE_NUMBER=1;
    String pageSize="_limit";
    String pageNumber="_page";

    static Pageable getPageAble(int pageNumber,int pageSize){
        if(pageNumber<DEFAULT_PAGE_NUMBER){
            pageNumber=DEFAULT_PAGE_NUMBER;
        }
        if(pageSize<1){
            pageSize=DEFAULT_PAGE_LIMIT;
        }
            Pageable pageAble= PageRequest.of(pageNumber-1,pageSize);
        return pageAble;
    }

}
