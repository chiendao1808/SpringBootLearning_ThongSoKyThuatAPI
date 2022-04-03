package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ThuongHieuRepo extends JpaRepository<ThuongHieu,Integer> {

    @Query("from ThuongHieu  thuongHieu where thuongHieu.xoa =false")
    public Page<ThuongHieu> findAll(Pageable pageable);

    @Query(value = "from ThuongHieu thuongHieu where thuongHieu.id =?1 and thuongHieu.xoa =false")
    public Optional<ThuongHieu> findById(int id);

    @Query(value = "from ThuongHieu thuongHieu where thuongHieu.tenThuongHieu like concat('%',?1,'%') " +
            "and thuongHieu.xoa =false")
    public Page<ThuongHieu> findByTenThuongHieu(String tenThuongHieu, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ThuongHieu thuongHieu set thuongHieu.xoa =true where thuongHieu.id =?1")
    public int delete(int id);

}
