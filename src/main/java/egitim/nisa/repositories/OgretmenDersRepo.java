package egitim.nisa.repositories;


import egitim.nisa.models.entities.OgretmenDers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgretmenDersRepo extends JpaRepository<OgretmenDers,Long> {

    @Query("select ods from OgretmenDers ods where ods.status=true order by ods.id desc")
    List<OgretmenDers> findAll();

    @Query("select ods from OgretmenDers ods where ods.id= :ogretmenDersId order by ods.id desc ")
    OgretmenDers findByOgretmenDersId(@Param("ogretmenDersId") Long ogretmenDersId);
}
