package egitim.nisa.services.impl;

import egitim.nisa.models.dto.UserDto;
import egitim.nisa.models.entities.User;
import egitim.nisa.repositories.UserRepo;
import egitim.nisa.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> tumKayit() {
        List<User> userList=userRepo.findAll();
        return userList;
    }

    private  static SecureRandom rnd = new SecureRandom();

    @Override
    public Boolean userKaydet(UserDto userDto) {
        try {

            User user=new User();
            user.setKullaniciAdi(userDto.getKullaniciAdi());
            user.setSifre(createMD5(userDto.getSifre()));
            user.setEmail(userDto.getEmail());
            user.setActivationToken(generateAuthCode(25));
            user.setStatus(true);
            user.setInsertDate(new Date());
            user.setIsActive(false);
            userRepo.saveAndFlush(user);
            return true;
        } catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean userGuncelle(UserDto userDto) {
        try {
            User user=userRepo.findByUserId(userDto.getId());
            if(user==null){
                return false;
            }

            user.setKullaniciAdi(userDto.getKullaniciAdi());
            user.setInsertDate(new Date());
            userRepo.saveAndFlush(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public Boolean userSifreGuncelle(UserDto userDto,User user) {
        try {

            user.setSifre(userDto.getYeniSifre());
            userRepo.saveAndFlush(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public User findByUserId(Long id) {
        try {
            User user=userRepo.findByUserId(id);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean userSil(Long id) {
        try {
            User user= userRepo.findByUserId(id);
            if(user==null){
                return false;
            }
            user.setStatus(false);
            userRepo.saveAndFlush(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public Boolean userActive(String token) {
        try {
            User user= userRepo.findByActiveToken(token);
            if(user==null){
                return false;
            }
            user.setStatus(true);
            user.setIsActive(true);
            user.setActivationToken(null);
            userRepo.saveAndFlush(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public User findByUserEmail(String email) {
        try {
            User user=userRepo.findByUserEmail(email);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public User findByUserEmailSifre(String email, String sifre) {
        try {
            User kullanici =userRepo.findByUserEmailSifre(email,sifre);
            return kullanici;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findBySifre(String sifre) {
        try {
            User user=userRepo.findByUserSifre(sifre);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByUserKullaniciAdi(String kullaniciAdi) {
        try {
            User user=userRepo.findByUserKullanicAdi(kullaniciAdi);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateAuthCode(int capacity) {

        String AB = "0123456789ABCDEFGHIJKLMNOPRSTUVYZWXQ";
        StringBuilder sb = new StringBuilder(capacity);
        for (int i = 0; i < capacity; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }



    public String createMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean userLogIn(UserDto userDto) {
        try {
            User kullanici=userRepo.findByUserEmailSifre(userDto.getEmail(), createMD5(userDto.getSifre()));
            if (kullanici == null) {
                return false;
            }
            kullanici.setAccessToken(generateAuthCode(15));
            kullanici.setLastUpdateDate(new Date());
            userRepo.saveAndFlush(kullanici);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
