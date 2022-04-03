package com.example.thongsokythuatproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HangHoaThongSoDTO {

    private int id;

    private int hangHoaId;

    private int thongSoKiThuatId;

    private int thongSoChiTietId;

    private String giaTri;
}
