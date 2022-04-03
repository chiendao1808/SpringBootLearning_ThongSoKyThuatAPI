package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.NhomThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NhomThongSoRepo extends JpaRepository<NhomThongSo,Integer> {

    @Query(value = "from NhomThongSo nhomThongSo where nhomThongSo.xoa =false")
    public Page<NhomThongSo> findAll(Pageable pageable);

    @Query(value = "from NhomThongSo  nhomThongSo where nhomThongSo.id =?1 and nhomThongSo.xoa =false ")
    public Optional<NhomThongSo> findById(int id);

    @Query(value = "from NhomThongSo nhomThongSo where nhomThongSo.tenNhomThongSo like concat('%',?1,'%')" +
            "and nhomThongSo.xoa=false ")
    public Page<NhomThongSo> findByTenNhomThongSo(String tenNhomThongSo, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update NhomThongSo nhomThongSo set nhomThongSo.xoa =true" +
            " where nhomThongSo.id=?1")
    public int delete(int id);
}
