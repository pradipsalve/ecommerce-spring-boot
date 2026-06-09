package com.Ecom.userservice;

import com.Ecom.DTO.ProductRequest;
import com.Ecom.DTO.ProductResponse;
import com.Ecom.Entity.Product;
import com.Ecom.userRepo.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createproduct(ProductRequest productRequest) {
        Product product = new Product();
        UpdateProductFromRequest(product, productRequest);
        Product saveproduct = productRepository.save(product);
        return MaptoProductFromResponse(saveproduct);
    }

    private void UpdateProductFromRequest(Product product, ProductRequest productRequest) {


        product.setActive(productRequest.getActive());
        product.setDiscription(productRequest.getDiscription());
        product.setName(productRequest.getName());
        product.setCategery(productRequest.getCategery());
        product.setImageurl(productRequest.getImageurl());
        product.setStackquantity(productRequest.getStackquantity());
        product.setPrice(productRequest.getPrice());


    }

    private ProductResponse MaptoProductFromResponse(Product saveproduct) {
        ProductResponse response = new ProductResponse();
        response.setId(saveproduct.getId());
        response.setName(saveproduct.getName());
        response.setActive(saveproduct.getActive());
        response.setDiscription(saveproduct.getDiscription());
        response.setCategery(saveproduct.getCategery());
        response.setImageurl(saveproduct.getImageurl());
        response.setStackquantity(saveproduct.getStackquantity());
        response.setPrice(saveproduct.getPrice());
        return response;


    }

    public Optional<ProductResponse> updateproduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existingproduct -> {
                    UpdateProductFromRequest(existingproduct, productRequest);
                    Product savedproduct = productRepository.save(existingproduct);
                    return MaptoProductFromResponse(savedproduct);
                });
    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findByActiveTrue()
                .stream().map(this::MaptoProductFromResponse)
                .collect(Collectors.toList());

    }

    public boolean deletproduct(Long id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                }).orElse(false);
    }

    public List<ProductResponse> searchproduct(String keyword) {
        return productRepository.searchproduct(keyword)
                .stream().map(this::MaptoProductFromResponse)
                .collect(Collectors.toList());
    }
}
