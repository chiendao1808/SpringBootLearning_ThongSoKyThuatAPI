package com.example.thongsokythuatproject.serviceimpl;

import com.example.thongsokythuatproject.dto.HangHoaDTO;
import com.example.thongsokythuatproject.entities.HangHoa;
import com.example.thongsokythuatproject.entities.NhomHang;
import com.example.thongsokythuatproject.repository.HangHoaRepo;
import com.example.thongsokythuatproject.repository.NhomHangRepo;
import com.example.thongsokythuatproject.repository.ThuongHieuRepo;
import com.example.thongsokythuatproject.services.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.rmi.server.ExportException;
import java.util.Optional;


@Service
public class HangHoaServiceImpl implements HangHoaService {

    @Autowired
    private HangHoaRepo hangHoaRepo;

    @Autowired
    private NhomHangRepo nhomHangRepo;

    @Autowired
    private ThuongHieuRepo thuongHieuRepo;

    @Override
    public Page<HangHoa> findAll(Pageable pageable) {
        try {
            return hangHoaRepo.findAll(pageable);
        } catch (Exception exp)
        {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoa> findById(int id) {
        try{
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(id);
            if(hangHoaOptional.isPresent()) return hangHoaOptional;
            else return Optional.empty();
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<HangHoa> findByTenHangHoa(String name, Pageable page) {
        try
        {
            return hangHoaRepo.findByTenHangHoa(name,page);
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoa> save(HangHoa hangHoa) {
        try{
        if (hangHoaRepo.findById(hangHoa.getId()).isPresent())
            return Optional.empty();
       return Optional.ofNullable(hangHoaRepo.save(hangHoa));

    } catch (Exception exp)
        {
            exp.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<HangHoa> update(HangHoaDTO hangHoaDTO) {
        try {
            if (!hangHoaRepo.findById(hangHoaDTO.getId()).isPresent()) return Optional.empty();
            else {
                HangHoa hangHoaToUpdate = hangHoaRepo.findById(hangHoaDTO.getId()).get();
                hangHoaToUpdate.setTenHangHoa(hangHoaDTO.getTenHangHoa());
                hangHoaToUpdate.setMa(hangHoaDTO.getMa());
                hangHoaToUpdate.setMaGiamGia(hangHoaDTO.getMaGiamGia());
                hangHoaToUpdate.setMoTa(hangHoaDTO.getMoTa());
                hangHoaToUpdate.setPhanTramGiamGia(hangHoaDTO.getPhanTramGiamGia());
                hangHoaToUpdate.setTichDiem(hangHoaDTO.getTichDiem());
                hangHoaToUpdate.setUrlHinhAnh1(hangHoaDTO.getUrlHinhAnh1());
                hangHoaToUpdate.setUrlHinhAnh2(hangHoaDTO.getUrlHinhAnh2());
                hangHoaToUpdate.setUrlHinhAnh3(hangHoaDTO.getUrlHinhAnh3());
                if(nhomHangRepo.findById(hangHoaDTO.getNhomHangId()).isPresent())
                hangHoaToUpdate.setNhomHang(nhomHangRepo.findById(hangHoaDTO.getNhomHangId()).get());
                if(thuongHieuRepo.findById(hangHoaDTO.getThuongHieuId()).isPresent());
                hangHoaToUpdate.setThuongHieu(thuongHieuRepo.findById(hangHoaDTO.getThuongHieuId()).get());
                return Optional.ofNullable(hangHoaRepo.save(hangHoaToUpdate));
            }
        } catch (Exception exp)
        {
            exp.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(int id) {
        return hangHoaRepo.delete(id)>0?true:false;
    }
}
