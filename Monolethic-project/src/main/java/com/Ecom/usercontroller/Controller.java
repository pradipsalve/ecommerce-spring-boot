package com.Ecom.usercontroller;

import com.Ecom.DTO.UserRequest;
import com.Ecom.DTO.UserResponse;
import com.Ecom.Entity.User;


import com.Ecom.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> AddUser(@RequestBody UserRequest userRequest){

       userService.adduser(userRequest);
       return ResponseEntity.ok("user Added successfully");
    }


    @GetMapping()
    public ResponseEntity<List<UserResponse>>GetallUser(){
        return new ResponseEntity<>(userService.getalluser(), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>GetallUserbyid(@PathVariable Long id){
        return userService.getalluserbyid(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->
                   ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){

       boolean updated = userService.updateUser(id,userRequest);
       if(updated)
           return ResponseEntity.ok("user updated successfully");
           return ResponseEntity.notFound().build();
       }





}
