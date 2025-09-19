package com.emranhss.merchandise.service;

import com.emranhss.merchandise.dto.AdminDTO;
import com.emranhss.merchandise.entity.Admin;
import com.emranhss.merchandise.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public List<Admin> getAllAdmin() {
        return adminRepo.findAll();
    }


    public List<AdminDTO> getAllRoleAdminResponseDTOS() {
        return adminRepo.findAll().stream().map(admin -> {
            AdminDTO dto = new AdminDTO();


            dto.setId(admin.getId());
            dto.setEmail(admin.getEmail());
            dto.setName(admin.getName());
            dto.setAddress(admin.getAddress());
            dto.setPhone(admin.getPhone());
            dto.setGender(admin.getGender());
            dto.setPhoto(admin.getPhoto());
            dto.setDateOfBirth(admin.getDateOfBirth());


            return dto;
        }).toList();
    }



    public Admin save(Admin admin) {
        return adminRepo.save(admin);
    }

    public void delete(Long id) {
        adminRepo.deleteById(id);
    }

    public Admin getProfileByUserId(int userId) {
        return adminRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(" Admin not found"));
    }
}