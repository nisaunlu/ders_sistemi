package egitim.nisa.repositories;


import egitim.nisa.models.entities.KisiselBilgiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KisiselBilgilerRepo extends JpaRepository<KisiselBilgiler,Long> {

        @Query(" select kb from KisiselBilgiler kb where kb.status=true order by kb.id desc")
        List<KisiselBilgiler> findAll();

        @Query("select kb from KisiselBilgiler kb where kb.status=true and kb.id= :kisiselBilgilerId order by kb.id desc ")
        KisiselBilgiler findByKisiselBigiler(@Param("kisiselBilgilerId") Long kisiselBilgilerId);
}
