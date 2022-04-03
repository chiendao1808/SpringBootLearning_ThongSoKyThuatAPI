package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.ThongSoKiThuatDTO;
import com.example.thongsokythuatproject.entities.ThongSoKiThuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ThongSoKiThuatService {

    public Page<ThongSoKiThuat> findAll(Pageable pageable);

    public Optional<ThongSoKiThuat> findById(int id);

    public Page<ThongSoKiThuat> findByTenThongSo(String tenThongSo, Pageable pageable);

    public Page<ThongSoKiThuat> findByNhomHang(int nhomHangId, Pageable pageable);

    public Page<ThongSoKiThuat> findByNhomThongSo(int nhomThongSoId, Pageable pageable);

    public Optional<ThongSoKiThuat> save(ThongSoKiThuat thongSoKiThuat);

    public Optional<ThongSoKiThuat> update(ThongSoKiThuatDTO thongSoKiThuatDTO);

   public boolean delete(int id);
}
