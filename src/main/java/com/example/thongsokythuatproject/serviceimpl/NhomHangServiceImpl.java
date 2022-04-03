package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.NhomHangDTO;
import com.example.thongsokythuatproject.entities.NhomHang;
import com.example.thongsokythuatproject.repository.NhomHangRepo;
import com.example.thongsokythuatproject.services.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhomHangServiceImpl implements NhomHangService {

    @Autowired
    private NhomHangRepo nhomHangRepo;

    @Override
    public Page<NhomHang> findAll(Pageable pageable) {
        try{
            return nhomHangRepo.findAll(pageable);
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> findById(int id) {
        Optional<NhomHang> nhomHangOptional =nhomHangRepo.findById(id);
        if(nhomHangOptional.isPresent())
            return nhomHangOptional;
        else
        return Optional.empty();
    }

    @Override
    public Page<NhomHang> findByTenNhomHang(String tenNhomHang, Pageable pageable) {
        try {
            return nhomHangRepo.findByTenNhomHang(tenNhomHang,pageable);
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> saveNhomHang(NhomHang nhomHang) {
        if(nhomHangRepo.findById(nhomHang.getId()).isPresent())
            return Optional.empty();
        else {
            return Optional.ofNullable(nhomHangRepo.save(nhomHang));
        }
    }

    @Override
    public Optional<NhomHang> updateNhomHang(NhomHangDTO nhomHangDTO) {
        if(!nhomHangRepo.findById(nhomHangDTO.getId()).isPresent())
        return Optional.empty();
        else {
            NhomHang nhomHangToUpdate = nhomHangRepo.findById(nhomHangDTO.getId()).get();
            nhomHangToUpdate.setMaNhomHang(nhomHangDTO.getMaNhomHang());
            nhomHangToUpdate.setTenNhomHang(nhomHangDTO.getTenNhomHang());
            nhomHangToUpdate.setXoa(nhomHangDTO.isXoa());
            return Optional.of(nhomHangRepo.save(nhomHangToUpdate));
        }
    }

    @Override
    public boolean deleteNhomHang(int id) {
        if(!nhomHangRepo.findById(id).isPresent())
        return false;
        else {
            NhomHang nhomHangToDelete = nhomHangRepo.findById(id).get();
            nhomHangToDelete.setXoa(true);
            nhomHangRepo.save(nhomHangToDelete);
            return true;
        }
    }
}
