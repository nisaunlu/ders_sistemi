package egitim.nisa.repositories;


import egitim.nisa.models.entities.Ders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DersRepo extends JpaRepository<Ders,Long> {

        @Query("select d from Ders d where d.status = true order by d.id desc ")
        List<Ders> findAll();

        @Query("select d from Ders d where d.status = true and d.id= :dersId ORDER BY d.id DESC ")
        Ders findByDersId(@Param("dersId") Long dersId);
}
