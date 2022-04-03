package com.example.thongsokythuatproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "thong_so_ky_thuat", name = "nhom_hang")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhomHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;

    @Column(name = "ma_nhom_hang")
    private String maNhomHang;

    @Column(name = "ten_nhom_hang")
    private String tenNhomHang;

    @Column(name = "xoa")
    private boolean xoa;

}
