package com.example.thongsokythuatproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(schema = "thong_so_ky_thuat", name = "thuong_hieu")
@AllArgsConstructor
@NoArgsConstructor
public class ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // recommended for MySQL
    private int id ;

    @Column(name = "ten_thuong_hieu")
    private String tenThuongHieu;

    @Column(name="xoa")
    private boolean xoa;
}
