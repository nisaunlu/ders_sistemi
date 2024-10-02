package egitim.nisa.repositories;

import egitim.nisa.models.entities.Ogrenci;
import egitim.nisa.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    @Query("select u from User u  where u.status=true ORDER BY u.id DESC " )
    List<User> findAll();

    @Query("select u from User u  where u.status=true and u.id = :userId order by u.id desc ")
    User findByUserId(@Param("userId") Long userId);

    @Query("select u from User u where u.status=true and u.email = :email order by u.id desc")
    User findByUserEmail(@Param("email") String email);


    @Query("select u from User u where u.status=true and u.activationToken = :token order by u.id desc")
    User findByActiveToken(@Param("token") String token);

    @Query("select u from User u where u.status=true and u.sifre = :sifre order by u.id desc")
    User findByUserSifre(@Param("sifre") String sifre);

    @Query("select u from User u where u.status=true and u.kullaniciAdi = :kullaniciAdi order by u.id desc")
    User findByUserKullanicAdi(@Param("kullaniciAdi") String kullaniciAdi);

    @Query("select u from User u where u.status=true and u.email= :email and u.sifre= :sifre")
    User findByUserEmailSifre(@Param("email") String email, @Param("sifre") String sifre);
}
