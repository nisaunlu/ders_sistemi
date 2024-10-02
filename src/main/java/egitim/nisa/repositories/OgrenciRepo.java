package egitim.nisa.repositories;

import egitim.nisa.models.entities.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgrenciRepo extends JpaRepository<Ogrenci,Long> {

    @Query("select o from Ogrenci o where o.status = true ORDER BY o.id DESC " )
    List<Ogrenci> findAll();

    @Query("select o from Ogrenci o where o.status=true and o.id = :ogrenciId order by o.id desc ")
    Ogrenci findByOgrenciId(@Param("ogrenciId") Long ogrenciId);
}
