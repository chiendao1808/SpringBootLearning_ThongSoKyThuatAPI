package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.ThongSoChiTietDTO;
import com.example.thongsokythuatproject.entities.ThongSoChiTiet;
import com.example.thongsokythuatproject.entities.ThongSoKiThuat;
import com.example.thongsokythuatproject.repository.ThongSoChiTietRepo;
import com.example.thongsokythuatproject.repository.ThongSoKiThuatRepo;
import com.example.thongsokythuatproject.services.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThongSoChiTietServiceImpl implements ThongSoChiTietService {

    @Autowired
    private ThongSoChiTietRepo thongSoChiTietRepo;

    @Autowired
    private ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Override
    public Page<ThongSoChiTiet> findAll(Pageable pageable) {
        Page<ThongSoChiTiet> thongSoChiTietPage = thongSoChiTietRepo.findAll(pageable);
        return thongSoChiTietPage;
    }

    @Override
    public Optional<ThongSoChiTiet> findById(int id) {
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(id);
        if(thongSoChiTietOptional.isPresent()) return thongSoChiTietOptional;
        else return Optional.empty();
    }

    @Override
    public Page<ThongSoChiTiet> findByTen(String tenThongSo, Pageable pageable) {
        Page<ThongSoChiTiet> thongSoChiTietPage =thongSoChiTietRepo.findByTen(tenThongSo,pageable);
        return thongSoChiTietPage;
    }

    @Override
    public Page<ThongSoChiTiet> findByThongSoKiThuat(int thongSoKiThuatId, Pageable pageable) {
        Page<ThongSoChiTiet> thongSoChiTietPage =thongSoChiTietRepo.findByThongSoKiThuat(thongSoKiThuatId,pageable);
        return thongSoChiTietPage;
    }

    @Override
    public Optional<ThongSoChiTiet> saveThongSoChiTiet(ThongSoChiTiet thongSoChiTiet) {
        if(thongSoChiTietRepo.findById(thongSoChiTiet.getId()).isPresent()) return Optional.empty();
        else
        {
            return Optional.ofNullable(thongSoChiTietRepo.save(thongSoChiTiet));
        }
    }

    @Override
    public Optional<ThongSoChiTiet> updateThongSoChiTiet(ThongSoChiTietDTO thongSoChiTietDTO) {
        if(!thongSoChiTietRepo.findById(thongSoChiTietDTO.getId()).isPresent()) return Optional.empty();
        else {
            ThongSoChiTiet thongSoToUpdate = thongSoChiTietRepo.findById(thongSoChiTietDTO.getId()).get();
            thongSoToUpdate.setTen(thongSoChiTietDTO.getTen());
            thongSoToUpdate.setGiaTri(thongSoChiTietDTO.getGiaTri());
            if(thongSoKiThuatRepo.findById(thongSoChiTietDTO.getId()).isPresent())
            thongSoToUpdate.setThongSoKiThuat(thongSoKiThuatRepo.findById(thongSoChiTietDTO.getId()).get());
            return Optional.ofNullable(thongSoChiTietRepo.save(thongSoToUpdate));
        }
    }

    @Override
    public boolean deleteThongSoChiTiet(int id) {
        if(!thongSoChiTietRepo.findById(id).isPresent()) return false;
        else {
            return thongSoChiTietRepo.delete(id)>0?true:false;
        }
    }
}
