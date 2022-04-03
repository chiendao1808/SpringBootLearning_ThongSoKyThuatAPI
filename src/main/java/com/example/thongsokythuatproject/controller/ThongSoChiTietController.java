package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.ThongSoChiTietDTO;
import com.example.thongsokythuatproject.entities.ThongSoChiTiet;
import com.example.thongsokythuatproject.services.ThongSoChiTietService;
import com.example.thongsokythuatproject.services.ThongSoKiThuatService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thong-so-chi-tiet")
public class ThongSoChiTietController {

    @Autowired
    private ThongSoChiTietService thongSoChiTietService;

    @Autowired
    private ThongSoKiThuatService thongSoKiThuatService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable)
    {
        return ResponseEntity.ok(thongSoChiTietService.findAll(pageable));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id")int id){
        return ResponseEntity.ok(thongSoChiTietService.findById(id));
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> findByTen(@PathVariable(name = "name")String tenThongSo, Pageable pageable)
    {
        return ResponseEntity.ok(thongSoChiTietService.findByTen(tenThongSo,pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThongSoChiTiet thongSoChiTiet,
                                  @RequestParam(name = "tech-id")int thongSoKiThuatId)
    {
        return thongSoKiThuatService.findById(thongSoKiThuatId).map(thongSoKiThuat -> {
            thongSoChiTiet.setThongSoKiThuat(thongSoKiThuat);
            return thongSoChiTietService.saveThongSoChiTiet(thongSoChiTiet).isPresent()?ResponseEntity.ok("Thêm thành công")
                    :ResponseEntity.ok("Thêm thất bại");
        }).orElse(ResponseEntity.ok("Thêm thất bại"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThongSoChiTietDTO thongSoChiTietDTO,
                                    @RequestParam (name="id") int thongSoChiTietId,
                                    @RequestParam(name = "tech-id") int thongSoKiThuatId)
    {
        return thongSoKiThuatService.findById(thongSoKiThuatId).map(thongSoKiThuat -> {
            thongSoChiTietDTO.setId(thongSoChiTietId);
            thongSoChiTietDTO.setThongSoKiThuatId(thongSoKiThuatId);
            return thongSoChiTietService.updateThongSoChiTiet(thongSoChiTietDTO).isPresent()?ResponseEntity.ok("Cập nhật thành công")
                    : ResponseEntity.ok("Cập nhật thất bại");
        }).orElse(ResponseEntity.ok("Cập nhật thất bại"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam(name = "id") int id)
    {
        return thongSoChiTietService.deleteThongSoChiTiet(id)?ResponseEntity.ok("Xóa thành công")
                : ResponseEntity.ok("Xóa thất bại");
    }


}
