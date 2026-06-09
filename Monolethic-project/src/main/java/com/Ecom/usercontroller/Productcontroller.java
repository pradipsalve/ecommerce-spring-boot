package com.Ecom.usercontroller;

import com.Ecom.DTO.ProductRequest;
import com.Ecom.DTO.ProductResponse;
import com.Ecom.userservice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")

public class Productcontroller {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> createproduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<ProductResponse>
                (productService.createproduct(productRequest), HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateproduct(@PathVariable Long id,
                                                         @RequestBody ProductRequest productRequest){
        return productService.updateproduct(id,productRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteproduct(@PathVariable Long id){
       boolean deleted= productService.deletproduct(id);

       return deleted ? ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }



    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>>searchproduct(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchproduct(keyword));

    }


}
