package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.entity.Model;
import org.springframework.data.domain.Page;


import java.util.Map;

public interface ModelService {
     Model save(Model model);
     Model getModelById(Integer id);
     Page<Model> getAllModel(Map<String,String>params);
}
