package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.HangHoaThongSoDTO;
import com.example.thongsokythuatproject.entities.HangHoa;
import com.example.thongsokythuatproject.entities.HangHoaThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HangHoaThongSoService {

    public Page<HangHoaThongSo> findAll(Pageable pageable);

    public Optional<HangHoaThongSo> findById(int id);

    public Optional<HangHoaThongSo> save(HangHoaThongSo hangHoaThongSo);

    public Optional<HangHoaThongSo> update(HangHoaThongSoDTO hangHoaThongSoDTO);

    public boolean delete(int id);



}
