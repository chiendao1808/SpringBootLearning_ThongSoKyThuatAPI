package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.NhomHangDTO;
import com.example.thongsokythuatproject.entities.NhomHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NhomHangService {

    public Page<NhomHang> findAll(Pageable pageable);

    public Optional<NhomHang> findById(int id);

    public Page<NhomHang> findByTenNhomHang(String tenNhomHang, Pageable pageable);

    public Optional<NhomHang> saveNhomHang(NhomHang nhomHang);

    public Optional<NhomHang> updateNhomHang(NhomHangDTO nhomHangDTO);

    public boolean deleteNhomHang(int id);


}
