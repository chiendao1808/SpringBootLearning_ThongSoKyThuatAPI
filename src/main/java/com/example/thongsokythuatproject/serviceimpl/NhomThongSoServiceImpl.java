package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.NhomThongSoDTO;
import com.example.thongsokythuatproject.entities.NhomThongSo;
import com.example.thongsokythuatproject.repository.HangHoaRepo;
import com.example.thongsokythuatproject.repository.NhomThongSoRepo;
import com.example.thongsokythuatproject.services.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhomThongSoServiceImpl implements NhomThongSoService {

    @Autowired
    private NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    private HangHoaRepo hangHoaRepo;

    @Override
    public Page<NhomThongSo> findAll(Pageable pageable) {
        try {
            return nhomThongSoRepo.findAll(pageable);
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomThongSo> findById(int id) {
        Optional<NhomThongSo> nhomThongSoOptional =nhomThongSoRepo.findById(id);
        return nhomThongSoOptional.isPresent()?nhomThongSoOptional:Optional.empty();
    }

    @Override
    public Page<NhomThongSo> findByTenThongSo(String tenNhomThongSo, Pageable pageable) {
        Page<NhomThongSo> nhomThongSoPage = nhomThongSoRepo.findByTenNhomThongSo(tenNhomThongSo,pageable);
        return nhomThongSoPage;
    }

    @Override
    public Optional<NhomThongSo> save(NhomThongSo nhomThongSo) {
        if(nhomThongSoRepo.findById(nhomThongSo.getId()).isPresent()) return Optional.empty();
        else {
            return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSo));
        }
    }

    @Override
    public Optional<NhomThongSo> update(NhomThongSoDTO nhomThongSoDTO) {
        try {
            if (!nhomThongSoRepo.findById(nhomThongSoDTO.getId()).isPresent()) return Optional.empty();
            else {
                NhomThongSo nhomThongSoToUpdate = nhomThongSoRepo.findById(nhomThongSoDTO.getId()).get();
                nhomThongSoToUpdate.setMaNhomThongSo(nhomThongSoDTO.getMaNhomThongSo());
                nhomThongSoToUpdate.setTenNhomThongSo(nhomThongSoDTO.getTenNhomThongSo());
                if(hangHoaRepo.findById(nhomThongSoDTO.getId()).isPresent())
                nhomThongSoToUpdate.setHangHoa(hangHoaRepo.findById(nhomThongSoDTO.getId()).get());
                nhomThongSoToUpdate.setXoa(false);
                return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSoToUpdate));
            }
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(int id) {
        if(!nhomThongSoRepo.findById(id).isPresent()) return false;
        else {
            return nhomThongSoRepo.delete(id)>0?true:false;
        }
    }
}
