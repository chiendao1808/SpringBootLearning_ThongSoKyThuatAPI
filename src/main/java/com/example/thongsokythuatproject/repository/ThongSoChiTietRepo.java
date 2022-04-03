package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.ThongSoChiTiet;
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
public interface ThongSoChiTietRepo extends JpaRepository<ThongSoChiTiet,Integer> {

    @Query(value = "from ThongSoChiTiet tsChiTiet where tsChiTiet.xoa =false")
    public Page<ThongSoChiTiet> findAll(Pageable pageable);

    @Query(value = "from ThongSoChiTiet tsChiTiet where tsChiTiet.id =?1 and tsChiTiet.xoa =false ")
    public Optional<ThongSoChiTiet> findById(int id);

    @Query(value = "from ThongSoChiTiet tsChiTiet where tsChiTiet.ten like concat('%',?1,'%') and tsChiTiet.xoa=false")
    public Page<ThongSoChiTiet> findByTen(String tenThongSo,Pageable pageable);

    @Query("from ThongSoChiTiet tsChiTiet where tsChiTiet.thongSoKiThuat.id =?1 and tsChiTiet.xoa=false")
    public Page<ThongSoChiTiet> findByThongSoKiThuat(int thongSoKiThuatId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ThongSoChiTiet tsChiTiet set tsChiTiet.xoa=true where tsChiTiet.id =?1")
    public int delete(int id);





}
