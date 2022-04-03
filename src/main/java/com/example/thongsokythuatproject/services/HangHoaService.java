package com.example.thongsokythuatproject.services;

import com.example.thongsokythuatproject.dto.HangHoaDTO;
import com.example.thongsokythuatproject.entities.HangHoa;
import com.example.thongsokythuatproject.repository.HangHoaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HangHoaService {


    public Page<HangHoa> findAll(Pageable pageable);

    public Optional<HangHoa> findById(int id);

    public Page<HangHoa> findByTenHangHoa(String name, Pageable page);

    public Optional<HangHoa> save(HangHoa hangHoa);

    public Optional<HangHoa> update(HangHoaDTO hangHoaDTO);

    public boolean delete(int id);








}
