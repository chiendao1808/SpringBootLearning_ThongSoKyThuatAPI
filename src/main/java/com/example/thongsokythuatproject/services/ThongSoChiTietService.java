package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.ThongSoChiTietDTO;
import com.example.thongsokythuatproject.entities.ThongSoChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public interface ThongSoChiTietService {

    public Page<ThongSoChiTiet> findAll(Pageable pageable);

    public Optional<ThongSoChiTiet> findById(int id);

    public Page<ThongSoChiTiet> findByTen (String tenThongSo, Pageable pageable);

    public Page<ThongSoChiTiet>findByThongSoKiThuat(int thongSoKiThuatId, Pageable pageable);

    public Optional<ThongSoChiTiet> saveThongSoChiTiet(ThongSoChiTiet thongSoChiTiet);

    public Optional<ThongSoChiTiet> updateThongSoChiTiet(ThongSoChiTietDTO thongSoChiTietDTO);

    public boolean deleteThongSoChiTiet(int id);

}
