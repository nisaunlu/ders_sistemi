package egitim.nisa.services;

import egitim.nisa.models.dto.UserDto;
import egitim.nisa.models.entities.User;

import java.util.List;

public interface UserService {

    List<User> tumKayit();

    Boolean userKaydet(UserDto userDto);

    Boolean userGuncelle(UserDto userDto);

    User findByUserId(Long id);

    Boolean userSil(Long id);

    Boolean userActive(String token);

    User findByUserEmail(String email);

    Boolean userSifreGuncelle(UserDto userDto,User user);

    String createMD5(String str);

    User findBySifre(String sifre);

    User findByUserKullaniciAdi(String kullaniciAdi);

    Boolean userLogIn(UserDto userDto);

    User findByUserEmailSifre(String email, String sifre);
}
