package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.ThuongHieuDTO;
import com.example.thongsokythuatproject.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ThuongHieuService {

    public Page<ThuongHieu> findAll(Pageable pageable);

    public Optional<ThuongHieu> findById(int id);

    public Page<ThuongHieu> findByTenThuongHieu( String tenThuongHieu, Pageable pageable);

    public Optional<ThuongHieu> saveThuongHieu(ThuongHieu thuongHieu);

    public Optional<ThuongHieu> updateThuongHieu(ThuongHieuDTO thuongHieuDTO);

    public boolean deleteThuongHieu(int id);
}
