package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.entity.Color;

public interface ColorService {
    Color getColorById(Long id);
    Color save(Color color);
}
