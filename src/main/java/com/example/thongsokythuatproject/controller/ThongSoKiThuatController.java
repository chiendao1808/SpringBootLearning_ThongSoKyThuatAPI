package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.NhomThongSoDTO;
import com.example.thongsokythuatproject.dto.ThongSoKiThuatDTO;
import com.example.thongsokythuatproject.entities.ThongSoKiThuat;
import com.example.thongsokythuatproject.services.NhomHangService;
import com.example.thongsokythuatproject.services.NhomThongSoService;
import com.example.thongsokythuatproject.services.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/thong-so-ki-thuat")
public class ThongSoKiThuatController {

    @Autowired
    private ThongSoKiThuatService thongSoKiThuatService;

    @Autowired
    private NhomHangService nhomHangService;

    @Autowired
    private NhomThongSoService nhomThongSoService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable){
        return ResponseEntity.ok(thongSoKiThuatService.findAll(pageable));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id")int id)
    {
        return ResponseEntity.ok(thongSoKiThuatService.findById(id));
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> findByTenThongSo(@PathVariable(name = "name") String tenThongSo,Pageable pageable)
    {
        return ResponseEntity.ok(thongSoKiThuatService.findByTenThongSo(tenThongSo, pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThongSoKiThuat thongSoKiThuat,
                                  @RequestParam(name = "good-group-id") int nhomHangId,
                                  @RequestParam(name ="specs-group-id") int nhomThongSoId)
    {
       return nhomHangService.findById(nhomHangId).map(nhomHang -> {
            thongSoKiThuat.setNhomHang(nhomHang);
            return nhomThongSoService.findById(nhomThongSoId).map(nhomThongSo -> {
                thongSoKiThuat.setNhomThongSo(nhomThongSo);
              return thongSoKiThuatService.save(thongSoKiThuat).isPresent()?ResponseEntity.ok(("Th??m th??nh c??ng"))
                      :ResponseEntity.ok("Th??m th???t b???i");

            }).orElse(ResponseEntity.ok("Kh??ng t??m th???y nh??m th??ng s??? "));
        }).orElse(ResponseEntity.ok("Kh??ng t??m th???y nh??m h??ng "));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThongSoKiThuatDTO thongSoKiThuatDTO,
                                    @RequestParam(name = "id") int id,
                                    @RequestParam(name = "good-group-id") int nhomHangId,
                                    @RequestParam(name = "specs-group-id") int nhomThongSoId)
    {
        if(thongSoKiThuatService.findById(id).isPresent())
            thongSoKiThuatDTO.setId(id);
        else return ResponseEntity.ok("Th??ng s??? k??? thu???t kh??ng t???n t???i");
        return nhomHangService.findById(nhomHangId).map(nhomHang -> {
            thongSoKiThuatDTO.setNhomHangId(nhomHangId);
            return nhomThongSoService.findById(nhomThongSoId).map(nhomThongSo -> {
                thongSoKiThuatDTO.setNhomThongSoId(nhomThongSoId);
              return thongSoKiThuatService.update(thongSoKiThuatDTO).isPresent()?ResponseEntity.ok("Th??m th??nh c??ng")
                      :ResponseEntity.ok("Th??m th???t b???i");

            }).orElse(ResponseEntity.ok("Kh??ng t??m th???y nh??m th??ng s???"));
        }).orElse(ResponseEntity.ok("Kh??ng t??m th???y nh??m h??ng"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") int id)
    {
        return thongSoKiThuatService.delete(id)?ResponseEntity.ok("X??a th??nh c??ng")
                : ResponseEntity.ok("X??a th???t b???i");
    }




}
