package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.HangHoaDTO;
import com.example.thongsokythuatproject.entities.HangHoa;
import com.example.thongsokythuatproject.repository.HangHoaRepo;
import com.example.thongsokythuatproject.services.HangHoaService;
import com.example.thongsokythuatproject.services.NhomHangService;
import com.example.thongsokythuatproject.services.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/hang-hoa")
public class HangHoaController {

    @Autowired
    private HangHoaService hangHoaService;

    @Autowired
    private NhomHangService nhomHangService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable)
    {
        try {
            return ResponseEntity.ok(hangHoaService.findAll(pageable));
        } catch (Exception exp)
        {
            return ResponseEntity.ok("");
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id") int id,Pageable pageable)
    {
        Optional<HangHoa> hangHoaOptional = hangHoaService.findById(id);
        return ResponseEntity.ok(hangHoaOptional);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> findByName(@PathVariable(name = "name") String name, Pageable pageable)
    {
        Page<HangHoa> hangHoa=hangHoaService.findByTenHangHoa(name,pageable);
        return ResponseEntity.ok(hangHoa);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HangHoa hangHoa,
                                        @RequestParam(name = "good-group-id")int nhomHangId,
                                        @RequestParam(name = "brand-id") int thuongHieuId)
    {
        return nhomHangService.findById(nhomHangId).map(nhomHang -> {
            hangHoa.setNhomHang(nhomHang);
            return thuongHieuService.findById(thuongHieuId).map(thuongHieu -> {
                hangHoa.setThuongHieu(thuongHieu);
                return hangHoaService.save(hangHoa).isPresent()?ResponseEntity.ok("Thêm thành công")
                        :ResponseEntity.ok("Thêm thất bại");
            }).orElse(ResponseEntity.ok("Không tìm thấy thương hiệu"));
        }).orElse(ResponseEntity.ok("Không tìm thấy nhóm hàng"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody HangHoaDTO hangHoaDTO,
                                    @RequestParam(name = "id") int id,
                                    @RequestParam(name="good-group-id") int nhomHangId,
                                    @RequestParam(name = "brand-id") int thuongHieuId)
    {
        if(!hangHoaService.findById(id).isPresent()) return ResponseEntity.ok("Không tìm thấy hàng hóa");
        else hangHoaDTO.setId(id);
        return nhomHangService.findById(nhomHangId).map(nhomHang -> {
            hangHoaDTO.setNhomHangId(nhomHangId);
            return thuongHieuService.findById(thuongHieuId).map(thuongHieu -> {
                hangHoaDTO.setThuongHieuId(thuongHieuId);
                return hangHoaService.update(hangHoaDTO).isPresent()?ResponseEntity.ok("Cập nhật thành công")
                        :ResponseEntity.ok("Cập nhật thất bại");
            }).orElse(ResponseEntity.ok("Không tìm thấy thương hiệu"));
        }).orElse(ResponseEntity.ok("Không tìm thấy nhóm hàng"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") int id)
    {
        return hangHoaService.delete(id)?ResponseEntity.ok("Xóa thành công"):ResponseEntity.ok("Xóa thất bại!");
    }
}

