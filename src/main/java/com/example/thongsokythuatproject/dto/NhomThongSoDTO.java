package com.example.thongsokythuatproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NhomThongSoDTO {

    private int id;

    private String tenNhomThongSo;

    private String maNhomThongSo;

    private int hangHoaId ;
}
