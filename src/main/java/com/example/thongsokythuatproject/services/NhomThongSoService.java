package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.NhomThongSoDTO;
import com.example.thongsokythuatproject.entities.NhomThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NhomThongSoService {


    public Page<NhomThongSo> findAll(Pageable pageable);

    public Optional<NhomThongSo> findById(int id);

    public Page<NhomThongSo> findByTenThongSo(String tenNhomThongSo,Pageable pageable);

    public Optional<NhomThongSo> save(NhomThongSo nhomThongSo);

    public Optional<NhomThongSo> update(NhomThongSoDTO nhomThongSoDTO);


    public boolean delete (int id);

}
