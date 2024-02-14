package com.huysor.ecommerce.phoneshop.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data

public class PageDTO {
    private List<?>list;
    private PaginationDTO paginationDTO;
    public PageDTO(Page<?> page){
        this.list=page.getContent();
        this.paginationDTO=PaginationDTO
                .builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .totalPage(page.getTotalPages())
                .numberOfElement(page.getTotalElements())

                .build()

        ;
    }

}
