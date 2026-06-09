package com.Ecom.userservice;

import aj.org.objectweb.asm.commons.Remapper;
import com.Ecom.DTO.AddressDto;
import com.Ecom.DTO.UserRequest;
import com.Ecom.DTO.UserResponse;
import com.Ecom.Entity.Address;
import com.Ecom.Entity.User;
import com.Ecom.userRepo.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Data
@Service
@RequiredArgsConstructor


public class UserService {
    private final UserRepository userRepository;


    public List<UserResponse> getalluser() {
        return userRepository.findAll()
                .stream()
                .map(this::maptoUserResponse)
                .collect(Collectors.toList());
    }



    public void adduser(UserRequest userRequest) {
        User user = new User();
        updatUserfromRequest(user,userRequest);
         userRepository.save(user);
    }



    public boolean updateUser(Long id, UserRequest userRequest) {

        return userRepository.findById(id)
                .map(existinguser-> {
                    updatUserfromRequest(existinguser, userRequest);
                    userRepository.save(existinguser);
                    return true;
                }).orElse(false);
                }



    private void updatUserfromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setCity(userRequest.getAddress().getCity());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setState(userRequest.getAddress().getState());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setStreet(userRequest.getAddress().getStreet());

        }
    }


    public UserResponse maptoUserResponse(User user){

        UserResponse response = new UserResponse();
        response.setId(user.getId());
       response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress()!=null){
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getZipcode());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCountry(user.getAddress().getCountry());
            response.setAddress(addressDto);
        }
        return  response;

    }


    public Optional <UserResponse> getalluserbyid(Long id) {
       return userRepository.findById(id)
                .map(this::maptoUserResponse);
    }




}
