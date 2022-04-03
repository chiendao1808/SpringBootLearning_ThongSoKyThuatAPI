package com.example.thongsokythuatproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "hang_hoa", schema = "thong_so_ky_thuat")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HangHoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ma_giam_gia")
    private String maGiamGia;

    @Column(name = "mo_ta")
    private String moTa ;

    @Column(name = "phan_tram_giam_gia")
    private float phanTramGiamGia;

    @Column(name = "ten_hang_hoa")
    private String tenHangHoa ;

    @Column(name = "tich_diem")
    private Integer tichDiem;

    @Column(name="url_hinh_anh_1")
    private String urlHinhAnh1;

    @Column(name="url_hinh_anh_2")
    private String urlHinhAnh2;

    @Column(name="url_hinh_anh_3")
    private String urlHinhAnh3;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    @Column(name="ma_vach")
    private String maVach;

    @Column(name="xoa")
    private boolean xoa;
}
