package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.NhomHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NhomHangRepo extends JpaRepository<NhomHang,Integer> {

    @Query(value = "from NhomHang nhomHang where nhomHang.xoa =false")
    public Page<NhomHang> findAll(Pageable pageable);

    @Query(value = "from NhomHang nhomHang where nhomHang.id=?1 and nhomHang.xoa=false ")
    public Optional<NhomHang> findById(int id);

    @Query(value = "from NhomHang nhomHang where nhomHang.tenNhomHang like concat('%',?1,'%')" +
            "and nhomHang.xoa =false ")
    public Page<NhomHang> findByTenNhomHang(String tenNhomHang,Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update HangHoa hangHoa set hangHoa.xoa =true where hangHoa.id =?1")
    public int delete(int id);




}
