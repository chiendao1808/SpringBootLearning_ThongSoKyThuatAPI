package com.example.thongsokythuatproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThuongHieuDTO {
     private int id;

     private String tenThuongHieu;

     private boolean xoa;
}
