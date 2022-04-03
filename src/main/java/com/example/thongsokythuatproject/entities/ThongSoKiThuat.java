package com.example.thongsokythuatproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "thong_so_ky_thuat",name = "thong_so_ki_thuat")
public class ThongSoKiThuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_thong_so")
    private String tenThongSo;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "nhom_thong_so_id")
    private NhomThongSo nhomThongSo;

    @Column(name = "xoa")
    private boolean xoa ;


}
