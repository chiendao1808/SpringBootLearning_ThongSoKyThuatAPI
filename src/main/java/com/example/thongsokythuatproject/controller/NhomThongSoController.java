package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.NhomThongSoDTO;
import com.example.thongsokythuatproject.entities.NhomThongSo;
import com.example.thongsokythuatproject.services.HangHoaService;
import com.example.thongsokythuatproject.services.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/nhom-thong-so")
public class NhomThongSoController {

    @Autowired
    private NhomThongSoService nhomThongSoService;

    @Autowired
    private HangHoaService hangHoaService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<NhomThongSo> nhomThongSoList = nhomThongSoService.findAll(pageable);
        return ResponseEntity.ok(nhomThongSoList);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id") int id)
    {
        Optional<NhomThongSo>  nhomThongSoOptional = nhomThongSoService.findById(id);
        return nhomThongSoOptional.isPresent()?ResponseEntity.ok(nhomThongSoOptional):ResponseEntity.ok("Không tìm thấy");
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findByTenThongSo(@RequestParam("name") String tenThongSo,Pageable pageable){
        Page<NhomThongSo> nhomThongSoPage = nhomThongSoService.findByTenThongSo(tenThongSo,pageable);
        return ResponseEntity.ok(nhomThongSoPage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NhomThongSo nhomThongSo,
                                  @RequestParam("good-id") int hangHoaId)
    {
        return hangHoaService.findById(hangHoaId).map(hangHoa -> {
            nhomThongSo.setHangHoa(hangHoa);
            return nhomThongSoService.save(nhomThongSo).isPresent()?ResponseEntity.ok("Thêm thành công")
                    :ResponseEntity.ok("Thêm thất bại");
        }).orElse(ResponseEntity.ok("Thêm thất bại"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody NhomThongSoDTO nhomThongSoDTO,
                                    @RequestParam("id") int nhomThongSoId,
                                    @RequestParam("good-id") int hangHoaId){
       return hangHoaService.findById(hangHoaId).map(hangHoa -> {
           nhomThongSoDTO.setId(nhomThongSoId);
           nhomThongSoDTO.setHangHoaId(hangHoaId);
           return nhomThongSoService.update(nhomThongSoDTO).isPresent()?ResponseEntity.ok("Thêm thành công")
                   : ResponseEntity.ok("Thêm thất bại");
       }).orElse(ResponseEntity.ok("Thêm thất bại"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") int id)
    {
        return nhomThongSoService.delete(id)?ResponseEntity.ok("Xóa thành công"):ResponseEntity.ok("Xóa thất bại");
    }

}
