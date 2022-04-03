package com.example.thongsokythuatproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThongSoKiThuatDTO {
    private int id;

    private String tenThongSo;

    private int nhomHangId;

    private int nhomThongSoId;

}
