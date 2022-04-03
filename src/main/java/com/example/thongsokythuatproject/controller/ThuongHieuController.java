package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.ThuongHieuDTO;
import com.example.thongsokythuatproject.entities.ThuongHieu;
import com.example.thongsokythuatproject.services.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thuong-hieu")
public class ThuongHieuController {

    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable){
        return ResponseEntity.ok(thuongHieuService.findAll(pageable));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int id){
        Optional<ThuongHieu> thuongHieuOptional= thuongHieuService.findById(id);
        if(thuongHieuOptional.isPresent()) return ResponseEntity.ok(thuongHieuOptional);
        else return ResponseEntity.ok("");
    }

    @GetMapping("/find-by-brand-name")
    public ResponseEntity<?> findByTenThuongHieu(@RequestParam(name = "brand_name") String tenThuongHieu,
                                                 Pageable pageable)
    {
        Page<ThuongHieu> thuongHieuPage =thuongHieuService.findByTenThuongHieu(tenThuongHieu,pageable);
        return ResponseEntity.ok(thuongHieuPage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThuongHieu thuongHieu)
    {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.saveThuongHieu(thuongHieu);
        if(thuongHieuOptional.isPresent()) return ResponseEntity.ok("Thêm thành công");
        else  return ResponseEntity.ok("Thêm thất bại");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThuongHieuDTO thuongHieuDTO,@RequestParam(name = "id")int id)
    {
        if(!thuongHieuService.findById(id).isPresent()) return ResponseEntity.ok("Không tìm thấy thông thương hiệu!");
        thuongHieuDTO.setId(id);
        ThuongHieu thuongHieu= thuongHieuService.findById(id).get();
        if(thuongHieuDTO.getTenThuongHieu() == null) thuongHieuDTO.setTenThuongHieu(thuongHieu.getTenThuongHieu());
        Optional<ThuongHieu> thuongHieuOptional =thuongHieuService.updateThuongHieu(thuongHieuDTO);
        if(thuongHieuDTO.isXoa()) thuongHieuDTO.setXoa(false);
        if(thuongHieuOptional.isPresent()) return ResponseEntity.ok("Update thành công");
        else return ResponseEntity.ok("Update thất bại");

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam(name = "id")int id){
        return thuongHieuService.deleteThuongHieu(id)?ResponseEntity.ok("Xóa thành công"):ResponseEntity.ok("Xóa thất bại!");
    }









}
