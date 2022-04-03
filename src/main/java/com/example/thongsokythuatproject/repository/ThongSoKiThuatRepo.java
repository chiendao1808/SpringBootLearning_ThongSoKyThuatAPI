package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.ThongSoKiThuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ThongSoKiThuatRepo extends JpaRepository<ThongSoKiThuat, Integer> {

    @Query(value = "from ThongSoKiThuat tsKiThuat where tsKiThuat.xoa =false ")
    public Page<ThongSoKiThuat> findAll(Pageable pageable);

    @Query(value = "from ThongSoKiThuat tsKiThuat where tsKiThuat.id=?1 and tsKiThuat.xoa=false ")
    public Optional<ThongSoKiThuat> findById(int id);

    @Query(value = "from ThongSoKiThuat tsKiThuat where tsKiThuat.tenThongSo like concat('%',?1,'%')" +
            " and tsKiThuat.xoa =false ")
    public Page<ThongSoKiThuat> findByTenThongSo(String tenThongSo,Pageable pageable);

    @Query(value = "from ThongSoKiThuat tsKiThuat where tsKiThuat.nhomThongSo.id =?1 " +
            "and tsKiThuat.xoa =false")
    public Page<ThongSoKiThuat> findByNhomThongSo(int nhomThonSoId,Pageable pageable);

    @Query(value = "from ThongSoKiThuat  tsKiThuat where tsKiThuat.nhomHang.id=?1 and tsKiThuat.xoa =false")
    public Page<ThongSoKiThuat> findByNhomHang(int nhomHangId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update ThongSoKiThuat tsKitThuat set tsKitThuat.xoa =true " +
            " where tsKitThuat.id =?1")
    public int delete(int id);


}
