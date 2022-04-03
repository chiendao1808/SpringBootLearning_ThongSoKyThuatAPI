package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.ThuongHieuDTO;
import com.example.thongsokythuatproject.entities.ThuongHieu;
import com.example.thongsokythuatproject.repository.ThuongHieuRepo;
import com.example.thongsokythuatproject.services.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThuongHieuServcieImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepo thuongHieuRepo;

    @Override
    public Page<ThuongHieu> findAll(Pageable pageable) {
        return thuongHieuRepo.findAll(pageable);
    }

    @Override
    public Optional<ThuongHieu> findById(int id) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(id);
        if(thuongHieuOptional.isPresent()) return thuongHieuOptional;
        else return Optional.empty();
    }

    @Override
    public Page<ThuongHieu> findByTenThuongHieu(String tenThuongHieu, Pageable pageable) {
        Page<ThuongHieu> thuongHieuPage = thuongHieuRepo.findByTenThuongHieu(tenThuongHieu,pageable);
        return thuongHieuPage;
    }

    @Override
    public Optional<ThuongHieu> saveThuongHieu(ThuongHieu thuongHieu) {
        if(thuongHieuRepo.findById(thuongHieu.getId()).isPresent()) return Optional.empty();
        else return Optional.ofNullable(thuongHieuRepo.save(thuongHieu));
    }

    @Override
    public Optional<ThuongHieu> updateThuongHieu(ThuongHieuDTO thuongHieuDTO) {
        if(!thuongHieuRepo.findById(thuongHieuDTO.getId()).isPresent())
            return Optional.empty();
        else{
            ThuongHieu thuongHieuToUpdate = thuongHieuRepo.findById(thuongHieuDTO.getId()).get();
            thuongHieuToUpdate.setTenThuongHieu(thuongHieuDTO.getTenThuongHieu());
            thuongHieuToUpdate.setXoa(thuongHieuDTO.isXoa());
            return Optional.ofNullable(thuongHieuRepo.save(thuongHieuToUpdate));
        }
    }

    @Override
    public boolean deleteThuongHieu(int id) {
        if(!thuongHieuRepo.findById(id).isPresent())
        return false;
        else {
           return thuongHieuRepo.delete(id)>0?true:false;
        }
    }
}
