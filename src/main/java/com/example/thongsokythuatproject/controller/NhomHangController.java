package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.NhomHangDTO;
import com.example.thongsokythuatproject.entities.NhomHang;
import com.example.thongsokythuatproject.services.NhomHangService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/nhom-hang")
public class NhomHangController {

    @Autowired
    private NhomHangService nhomHangService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<NhomHang> nhomHangPage= nhomHangService.findAll(pageable);
        return ResponseEntity.ok(nhomHangPage);
    }

    @GetMapping("find-by-id")
    public ResponseEntity<?> findById (@RequestParam(name = "id") int id)
    {
        return ResponseEntity.ok(nhomHangService.findById(id));
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findByTenNhomHang(@RequestParam("name") String tenNhomHang,Pageable pageable)
    {
        Page<NhomHang> nhomHangPage = nhomHangService.findByTenNhomHang(tenNhomHang,pageable);
        return ResponseEntity.ok(nhomHangPage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NhomHang nhomHang)
    {
        if(nhomHangService.saveNhomHang(nhomHang).isPresent())
            return ResponseEntity.ok("Thêm thành công");
        else return ResponseEntity.ok("Thêm thất bại");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody NhomHangDTO nhomHangDTO,@RequestParam(name = "id") int id)
    {
        nhomHangDTO.setId(id);
        NhomHang nhomHang = nhomHangService.findById(id).get();
        if(nhomHangDTO.getMaNhomHang() == null) nhomHangDTO.setMaNhomHang(nhomHang.getMaNhomHang());
        if(nhomHangDTO.getTenNhomHang() == null) nhomHangDTO.setTenNhomHang(nhomHang.getTenNhomHang());
        Optional<NhomHang> nhomHangOptional =nhomHangService.updateNhomHang(nhomHangDTO);
        if(nhomHangOptional.isPresent())
        {
            System.out.println(nhomHangDTO);
            return ResponseEntity.ok("Update thành công !");
        }
        else return ResponseEntity.ok("Update thất bại!");


    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") int id)
    {
        if(nhomHangService.deleteNhomHang(id)) return ResponseEntity.ok("Xóa thành công!");
        return  ResponseEntity.ok("Xóa thất bại!");
    }

}
