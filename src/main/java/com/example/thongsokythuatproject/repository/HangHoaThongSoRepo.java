package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.HangHoaThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HangHoaThongSoRepo extends JpaRepository<HangHoaThongSo,Integer> {

    @Query(value = "from HangHoaThongSo  hHTS where hHTS.xoa=false ")
    public Page<HangHoaThongSo> findAll(Pageable pageable);

    @Query(value = "from HangHoaThongSo  hHTS where hHTS.id =?1 and hHTS.xoa =false ")
    public Optional<HangHoaThongSo> findById(int id);

    @Transactional
    @Modifying // need modifying
    @Query(value = "update HangHoaThongSo hHTS set hHTS.xoa=true " +
            "where hHTS.id=?1")
    public int delete(int id);



}
