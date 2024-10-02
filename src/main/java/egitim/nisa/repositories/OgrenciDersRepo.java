package egitim.nisa.repositories;


import egitim.nisa.models.entities.OgrenciDers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgrenciDersRepo extends JpaRepository<OgrenciDers, Long> {

    @Query("select od from OgrenciDers od order by od.id desc ")
    List<OgrenciDers> findAll();

    @Query("select od from OgrenciDers od where od.id= :ogrenciDersId order by od.id desc ")
    OgrenciDers findByOgrenciDersId(@Param("ogrenciDersId") Long ogrenciDersId);
}
