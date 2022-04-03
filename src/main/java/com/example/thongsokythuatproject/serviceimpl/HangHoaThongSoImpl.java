package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.HangHoaThongSoDTO;
import com.example.thongsokythuatproject.entities.HangHoaThongSo;
import com.example.thongsokythuatproject.repository.HangHoaRepo;
import com.example.thongsokythuatproject.repository.HangHoaThongSoRepo;
import com.example.thongsokythuatproject.repository.ThongSoChiTietRepo;
import com.example.thongsokythuatproject.repository.ThongSoKiThuatRepo;
import com.example.thongsokythuatproject.services.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class HangHoaThongSoImpl implements HangHoaThongSoService {

    @Autowired
    private HangHoaThongSoRepo hangHoaThongSoRepo;

    @Autowired
    private HangHoaRepo hangHoaRepo;

    @Autowired
    private ThongSoChiTietRepo thongSoChiTietRepo;

    @Autowired
    private ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Override
    public Page<HangHoaThongSo> findAll(Pageable pageable) {
        try
        {
            return hangHoaThongSoRepo.findAll(pageable);
        } catch (Exception exp) {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoaThongSo> findById(int id) {
        Optional<HangHoaThongSo> hangHoaThongSoOptional =hangHoaThongSoRepo.findById(id);
        return hangHoaThongSoOptional.isPresent()?hangHoaThongSoOptional:Optional.empty();
    }

    @Override
    public Optional<HangHoaThongSo> save(HangHoaThongSo hangHoaThongSo) {
        if(hangHoaThongSoRepo.findById(hangHoaThongSo.getId()).isPresent()) return Optional.empty();
        else {
            hangHoaThongSo.setXoa(false);
            return Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSo));
        }
    }

    @Override
    public Optional<HangHoaThongSo> update(HangHoaThongSoDTO hangHoaThongSoDTO) {
        try{
           if(!hangHoaThongSoRepo.findById(hangHoaThongSoDTO.getId()).isPresent()) return Optional.empty();
           HangHoaThongSo hangHoaThongSoToUpdate = hangHoaThongSoRepo.findById(hangHoaThongSoDTO.getId()).get();
           hangHoaThongSoToUpdate.setGiaTri(hangHoaThongSoDTO.getGiaTri());
           hangHoaThongSoToUpdate.setHangHoa(hangHoaRepo.findById(hangHoaThongSoDTO.getHangHoaId()).get());
           hangHoaThongSoToUpdate.setThongSoKiThuat(thongSoKiThuatRepo.findById(hangHoaThongSoDTO.getThongSoKiThuatId()).get());
           hangHoaThongSoToUpdate.setThongSoChiTiet(thongSoChiTietRepo.findById(hangHoaThongSoDTO.getThongSoChiTietId()).get());
           return Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSoToUpdate));
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(int id) {
        if(!hangHoaThongSoRepo.findById(id).isPresent()) return false;
        return hangHoaThongSoRepo.delete(id)>0?true:false;
    }
}
