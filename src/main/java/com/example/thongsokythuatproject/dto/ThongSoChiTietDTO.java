package com.example.thongsokythuatproject.dto;

import com.example.thongsokythuatproject.entities.ThongSoKiThuat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThongSoChiTietDTO {

    private int id;

    private String ten;

    private String giaTri;

    private int thongSoKiThuatId;


}
