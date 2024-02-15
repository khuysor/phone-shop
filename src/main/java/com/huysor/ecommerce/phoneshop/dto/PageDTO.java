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
        PaginationDTO.PaginationDTOBuilder builder = PaginationDTO
                .builder();
        builder.empty(page.isEmpty());
        builder.first(page.isFirst());
        builder.last(page.isLast());
        builder.pageSize(page.getPageable().getPageSize());
        builder.totalPage(page.getTotalPages());
        builder.numberOfElement(page.getTotalElements());
        this.paginationDTO= builder
                .build()

        ;

    }

}
