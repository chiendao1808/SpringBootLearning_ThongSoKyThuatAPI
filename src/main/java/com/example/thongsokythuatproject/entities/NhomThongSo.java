package com.example.thongsokythuatproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "thong_so_ky_thuat", name = "nhom_thong_so")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NhomThongSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="ma_nhom_thong_so")
    private String maNhomThongSo;

    @Column(name="ten_nhom_thong_so")
    private String tenNhomThongSo;

    @ManyToOne
    @JoinColumn(name = "hang_hoa_id")
    private HangHoa hangHoa;

    @Column(name="xoa")
    private boolean xoa;

}
