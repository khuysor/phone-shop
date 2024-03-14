package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.Mapper.ProductMapper;
import com.huysor.ecommerce.phoneshop.dto.ProductImportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;
import com.huysor.ecommerce.phoneshop.exception.ApiException;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.ProductImportRepository;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductImplement implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportRepository productImportRepository;
    private final ProductMapper productMapper;

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public Product save(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        Product product = getProduct(productImportDTO.getProductId());
        Integer available = 0;
        if (product.getAvailableUnit() != null) {
            available = product.getAvailableUnit();
        }
        product.setAvailableUnit(available + productImportDTO.getImportUnit());
        productRepository.save(product);
        ProductImportHistory productImportHistory = productMapper.toProduct(productImportDTO);
        productImportRepository.save(productImportHistory);
    }

    @Override
    public void setPrice(Long id, BigDecimal Price) {
        Product product = getProduct(id);
        product.setSalePrice(Price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long id, Integer unit) {

    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        String text = "Product with color id =%s and color id=$d was not found ";
        return productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, text.formatted(modelId, colorId)));
    }

    @Override
    public Map<Integer, String> uploadProduct(MultipartFile file) {
        Map<Integer, String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("product");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Integer rowNumber = 0;
                try {

                    Row row = rowIterator.next();
                    int cellIndex = 0;
                    Cell cellNo = row.getCell(cellIndex);
                    rowNumber = (int) cellNo.getNumericCellValue();
                    System.out.println("row"+cellNo);
                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();

                    System.out.println("model"+modelId);
                    Cell color = row.getCell(cellIndex++);
                    Long colorId = (long) color.getNumericCellValue();
                    System.out.println("color"+colorId);

                    Cell uploadPrice = row.getCell(cellIndex++);
                    Double importPrice = uploadPrice.getNumericCellValue();
                    System.out.println("price"+importPrice);

                    Cell dateAdd = row.getCell(cellIndex++);
                    LocalDateTime importDate = dateAdd.getLocalDateTimeCellValue();
                    System.out.println("date"+importDate);

                    Cell getUnit = row.getCell(cellIndex++);
                    Integer unit = (int) getUnit.getNumericCellValue();
                    if(unit<1){
                        throw  new ApiException(HttpStatus.BAD_REQUEST,"Unit must be greater then 0");
                    }
                    System.out.println("unit"+unit);
                    Product product = getByModelIdAndColorId(modelId, colorId);
                    Integer availableUnit = 0;
                    if (product.getAvailableUnit() != null) {
                        availableUnit = product.getAvailableUnit();
                    }
                    product.setAvailableUnit(availableUnit + unit);
                    ProductImportHistory productImportHistory = new ProductImportHistory();
                    productImportHistory.setImportUnit(unit);
                    productImportHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    productImportHistory.setProduct(product);
                    productImportHistory.setDateImport(importDate);
                    productImportRepository.save(productImportHistory);
                } catch (Exception e) {
                    map.put(rowNumber, e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

}
