package com.example.schoolmanagementsystem.Service;

import com.example.schoolmanagementsystem.Api.ApiException;
import com.example.schoolmanagementsystem.DTO.AddressDTO;
import com.example.schoolmanagementsystem.Model.Address;
import com.example.schoolmanagementsystem.Model.Teacher;
import com.example.schoolmanagementsystem.Repository.AddressRepository;
import com.example.schoolmanagementsystem.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public void addAddress(AddressDTO addressDTO){
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());

        if(teacher == null){
            throw new ApiException("teacher not found");
        }

        Address address = new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildNumber(),teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO){
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());

        if(address == null){
            throw new ApiException("address not found");
        }
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildNumber(addressDTO.getBuildNumber());

        addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId){

        // check if address exist:
        Address address = addressRepository.findAddressById(addressId);
        if(address == null){
            throw new ApiException("address id not found");
        }

        //check if teacher with this address exist:
        Teacher teacher = teacherRepository.giveMeTeacherByAddressId(address.getId());
        if(teacher == null){
            throw new ApiException("teacher not found");
        }

        // delete address from teacher;
        teacher.setAddress(null);
        teacherRepository.save(teacher);

        addressRepository.delete(address);
    }

}
