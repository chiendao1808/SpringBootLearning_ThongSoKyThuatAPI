package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.ThongSoKiThuatDTO;
import com.example.thongsokythuatproject.entities.ThongSoKiThuat;
import com.example.thongsokythuatproject.repository.NhomHangRepo;
import com.example.thongsokythuatproject.repository.NhomThongSoRepo;
import com.example.thongsokythuatproject.repository.ThongSoKiThuatRepo;
import com.example.thongsokythuatproject.services.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ThongSoKiThuatServiceImpl implements ThongSoKiThuatService {

    @Autowired
    private ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    private NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    private NhomHangRepo nhomHangRepo;

    @Override
    public Page<ThongSoKiThuat> findAll(Pageable pageable) {
        return thongSoKiThuatRepo.findAll(pageable);
    }

    @Override
    public Optional<ThongSoKiThuat> findById(int id) {
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(id);
        return thongSoKiThuatOptional.isPresent()?thongSoKiThuatOptional: Optional.empty();
    }

    @Override
    public Page<ThongSoKiThuat> findByTenThongSo(String tenThongSo, Pageable pageable) {
        Page<ThongSoKiThuat> thongSoKiThuatPage =thongSoKiThuatRepo.findByTenThongSo(tenThongSo,pageable);
        return thongSoKiThuatPage;
    }

    @Override
    public Page<ThongSoKiThuat> findByNhomHang(int nhomHangId, Pageable pageable) {
        Page<ThongSoKiThuat> thongSoKiThuatPage =thongSoKiThuatRepo.findByNhomHang(nhomHangId,pageable);
        return thongSoKiThuatPage;
    }

    @Override
    public Page<ThongSoKiThuat> findByNhomThongSo(int nhomThongSoId, Pageable pageable) {
        Page<ThongSoKiThuat> thongSoKiThuatPage =thongSoKiThuatRepo.findByNhomThongSo(nhomThongSoId, pageable);
        return thongSoKiThuatPage;
    }

    @Override
    public Optional<ThongSoKiThuat> save(ThongSoKiThuat thongSoKiThuat) {
        if(thongSoKiThuatRepo.findById(thongSoKiThuat.getId()).isPresent()) return Optional.empty();
        else return Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuat));
    }

    @Override
    public Optional<ThongSoKiThuat> update(ThongSoKiThuatDTO thongSoKiThuatDTO) {
        try {
            if (!thongSoKiThuatRepo.findById(thongSoKiThuatDTO.getId()).isPresent()) return Optional.empty();
            else {
                ThongSoKiThuat thongSoKiThuatToUpdate = thongSoKiThuatRepo.findById(thongSoKiThuatDTO.getId()).get();
                thongSoKiThuatToUpdate.setTenThongSo(thongSoKiThuatDTO.getTenThongSo());
                if(nhomThongSoRepo.findById(thongSoKiThuatDTO.getId()).isPresent())
                thongSoKiThuatToUpdate.setNhomThongSo(nhomThongSoRepo.findById(thongSoKiThuatDTO.getNhomThongSoId()).get());
                if(nhomHangRepo.findById(thongSoKiThuatDTO.getNhomHangId()).isPresent())
                thongSoKiThuatToUpdate.setNhomHang(nhomHangRepo.findById(thongSoKiThuatDTO.getNhomHangId()).get());
                return Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuatToUpdate));
            }
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public boolean delete(int id) {
        if(!thongSoKiThuatRepo.findById(id).isPresent()) return false;
        else return thongSoKiThuatRepo.delete(id)>0?true:false;
    }
}
