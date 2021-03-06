package com.example.thongsokythuatproject.controller;

import com.example.thongsokythuatproject.dto.HangHoaThongSoDTO;
import com.example.thongsokythuatproject.entities.HangHoa;
import com.example.thongsokythuatproject.entities.HangHoaThongSo;
import com.example.thongsokythuatproject.services.HangHoaService;
import com.example.thongsokythuatproject.services.HangHoaThongSoService;
import com.example.thongsokythuatproject.services.ThongSoChiTietService;
import com.example.thongsokythuatproject.services.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hang-hoa-thong-so")
public class HangHoaThongSoController {

    @Autowired
    private HangHoaThongSoService hangHoaThongSoService;

    @Autowired
    private HangHoaService hangHoaService;

    @Autowired
    private ThongSoKiThuatService thongSoKiThuatService;

    @Autowired
    private ThongSoChiTietService thongSoChiTietService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Pageable pageable)
    {
        return ResponseEntity.ok(hangHoaThongSoService.findAll(pageable));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id") int id)
    {
        return ResponseEntity.ok(hangHoaThongSoService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HangHoaThongSo hangHoaThongSo,
                                  @RequestParam(name = "good-id")int hangHoaId,
                                  @RequestParam(name = "specs-good-id") int thongSoKiThuatId,
                                  @RequestParam(name = "detail-specs-id") int thongSoChiTietId)
    {
        return hangHoaService.findById(hangHoaId).map(hangHoa -> {
            hangHoaThongSo.setHangHoa(hangHoa);
            return thongSoChiTietService.findById(thongSoChiTietId).map(thongSoChiTiet -> {
                hangHoaThongSo.setThongSoChiTiet(thongSoChiTiet);
                return thongSoKiThuatService.findById(thongSoKiThuatId).map(thongSoKiThuat ->{
                    hangHoaThongSo.setThongSoKiThuat(thongSoKiThuat);
                    return hangHoaThongSoService.save(hangHoaThongSo).isPresent()?ResponseEntity.ok("Th??m th??nh c??ng")
                            : ResponseEntity.ok("Th??m th???t b???i");
                }).orElse(ResponseEntity.ok("Kh??ng t??m th???y th??ng s??? k??? thu???t"));
            }).orElse(ResponseEntity.ok("Kh??ng t??m th???y th??ng s??? chi ti???t"));
        }).orElse(ResponseEntity.ok("Kh??ng t??m th???y h??ng h??a "));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody HangHoaThongSoDTO hangHoaThongSoDTO,
                                    @RequestParam(name ="id") int id,
                                    @RequestParam(name = "good-id")int hangHoaId,
                                    @RequestParam(name = "specs-good-id") int thongSoKiThuatId,
                                    @RequestParam(name = "detail-specs-id") int thongSoChiTietId)
    {
        if(!hangHoaThongSoService.findById(id).isPresent()) return ResponseEntity.ok("H??ng H??a th??ng s??? kh??ng t??m th???y!");
        else hangHoaThongSoDTO.setId(id);
        return hangHoaService.findById(hangHoaId).map(hangHoa -> {
            hangHoaThongSoDTO.setHangHoaId(hangHoaId);
            return thongSoChiTietService.findById(thongSoChiTietId).map(thongSoChiTiet -> {
                hangHoaThongSoDTO.setThongSoChiTietId(thongSoChiTietId);
                return thongSoKiThuatService.findById(thongSoKiThuatId).map(thongSoKiThuat ->{
                    hangHoaThongSoDTO.setThongSoKiThuatId(thongSoKiThuatId);
                    return hangHoaThongSoService.update(hangHoaThongSoDTO).isPresent()?ResponseEntity.ok("C???p nh???t th??nh c??ng")
                            : ResponseEntity.ok("C???p nh???t th???t b???i");
                }).orElse(ResponseEntity.ok("Kh??ng t??m th???y th??ng s??? k??? thu???t"));
            }).orElse(ResponseEntity.ok("Kh??ng t??m th???y th??ng s??? chi ti???t"));
        }).orElse(ResponseEntity.ok("Kh??ng t??m th???y h??ng h??a "));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam(name = "id") int id)
    {
        return hangHoaThongSoService.delete(id)?ResponseEntity.ok("X??a th??nh c??ng")
                :ResponseEntity.ok("X??a th???t b???i");
    }
}
