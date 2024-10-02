package egitim.nisa.repositories;


import egitim.nisa.models.entities.Ogrenci;
import egitim.nisa.models.entities.Ogretmen;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgretmenRepo extends JpaRepository<Ogretmen,Long> {

    @Query("select o from Ogretmen o where o.status=true order by o.id desc ")
    List<Ogretmen> findAll();

    @Query("select o from Ogretmen o where o.status = true and o.id = :ogretmenId ORDER BY o.id DESC " )
    Ogretmen findByOgretmenId(@Param("ogretmenId") Long ogretmenId);

}
