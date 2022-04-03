package com.example.thongsokythuatproject.dto;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class HangHoaDTO {

    private int id;

    private String ma;

    private  String maGiamGia;

    private String moTa;

    private String tenHangHoa;

    private float phanTramGiamGia;

    private Integer tichDiem;

    private String urlHinhAnh1;

    private String urlHinhAnh2;

    private String urlHinhAnh3;

    private int nhomHangId;

    private int thuongHieuId;

    private String maVach;

}
