package com.huysor.ecommerce.phoneshop.service;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.BrandRepository;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import com.huysor.ecommerce.phoneshop.servicesImplement.BrandImplement;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandImplTest {
@Mock
    private BrandRepository brandRepository;
    private BrandService brandService;
    @BeforeEach
    public void setUp(){
        brandService=new BrandImplement(brandRepository);

    }

//    public void testCreate(){
//        //given
//        Brands brands =new Brands();
//        brands.setName("apple");
//        brands.setId(1);
//        // when
//        when(brandRepository.save(any(Brands.class))).thenReturn(brands);
//         Brands brandReturn=   brandService.create(new Brands());
//        // then
//        assertEquals(1,brandReturn.getId());
//        assertEquals("apple",brandReturn.getName());
//
//
//    }
    @Test
    public void testCreate(){
        // given
        Brands brands = new Brands();
        brands.setName("apple");
        // when
        brandRepository.save(brands);
        //then
        verify(brandRepository,times(1)).save(brands);
    }

    @Test
    void testGetId(){
//        given
            Brands brands =new Brands();
            brands.setName("apple");
            brands.setId(1);
//        when
            when(brandRepository.findById(1)).thenReturn(Optional.of(brands));
          Brands brandsReturn=  brandService.getBrandById(1);
//        then
        assertEquals(1,brandsReturn.getId());
        assertEquals("apple",brandsReturn.getName());
    }
    @Test
    void testFindIdError(){
//        when
       when(brandRepository.findById(2)).thenReturn(Optional.empty());
       assertThatThrownBy(()->brandService.getBrandById(2))
               .isInstanceOf(ResourceNotFoundException.class)
               .hasMessage("brand with id = 2 not found");
    }


}
