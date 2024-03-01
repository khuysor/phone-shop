package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.entity.Color;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.ColorRepository;
import com.huysor.ecommerce.phoneshop.services.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ColorImplement implements ColorService {
    private  final ColorRepository colorRepository;
    @Override
    public Color getColorById(Long id) {
        return colorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Color",id));
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }
}

