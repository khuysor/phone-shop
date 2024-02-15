package com.huysor.ecommerce.phoneshop.repository;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
public class BrandRepositoryTest  {
    @Autowired
    private BrandRepository brandRepository;
    @Test
    public void testFindByNameLike(){
        //given
        Brands brands= new Brands();
        brands.setName("Apple");
        Brands brands1= new Brands();
        brands1.setName("Samsung");
        brandRepository.save(brands);
        brandRepository.save(brands1);
        //when
        List<Brands> listBrand=        brandRepository.findByNameLike("%A%");
        //then
        assertEquals(1,listBrand.size());
        assertEquals("Apple",listBrand.get(0).getName());
        assertEquals(1,listBrand.get(0).getId());

    }
}
