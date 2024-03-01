package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.entity.Model;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.ModelRepository;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ModelImplement implements ModelService {
    private final ModelRepository modelRepository;

    @Override
    public Model save(Model model) {
       return modelRepository.save(model);
    }

    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("brand", id));
    }

    @Override
    public Page<Model> getAllModel(Map<String, String> params) {

        return null;
    }

    @Override
    public List<Model> getModelByBrandId(Long brandId) {
        return modelRepository.findByBrandsId(brandId);
    }
}
