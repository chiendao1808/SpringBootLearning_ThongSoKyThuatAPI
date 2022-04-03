package com.example.thongsokythuatproject.repository;

import com.example.thongsokythuatproject.entities.HangHoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HangHoaRepo extends JpaRepository<HangHoa,Integer> {

    @Query(value = "from HangHoa hanghoa where hanghoa.xoa=false")
    public Page<HangHoa> findAll(Pageable pageable);

    @Query(value = "from HangHoa  hanghoa where hanghoa.id =?1 and hanghoa.xoa =false ")
    public Optional<HangHoa> findById(int id);

    @Query("from HangHoa hanghoa where hanghoa.tenHangHoa like concat('%',?1,'%') and hanghoa.xoa =false")
    public Page<HangHoa> findByTenHangHoa(String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update HangHoa hangHoa set hangHoa.xoa =true where hangHoa.id =?1")
    public int delete(int id);


}
