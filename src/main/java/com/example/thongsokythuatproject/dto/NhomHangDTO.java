package com.example.thongsokythuatproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NhomHangDTO {

    private int id;

    private String maNhomHang;

    private String tenNhomHang;

    private boolean xoa;
}
