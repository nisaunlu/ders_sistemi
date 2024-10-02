package egitim.nisa.controllers;


import egitim.nisa.models.dto.UserDto;
import egitim.nisa.models.entities.User;
import egitim.nisa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserContoller {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/listele")
    public ResponseEntity userListele(){
        List<User> userList= userService.tumKayit();


        return new ResponseEntity(userList, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "/user/kaydet")
    public ResponseEntity userKaydet(@RequestBody UserDto userDto){
        User email=userService.findByUserEmail(userDto.getEmail());
        if(email!=null){
            return new ResponseEntity("Bu e-mail adresiyle zaten bir hesap var. ", HttpStatus.ACCEPTED);
        }

        if(userDto.getKullaniciAdi()==null || userDto.getKullaniciAdi().isEmpty()){
            return new ResponseEntity("Kullanıcı Adı Zorunlu!", HttpStatus.ACCEPTED);
        }
        if(userDto.getSifre()==null || userDto.getSifre().isEmpty()){
            return new ResponseEntity("Şifre Zorunlu!", HttpStatus.ACCEPTED);
        }
        if(!userDto.getSifre().equals(userDto.getSifre2())){
            return new ResponseEntity("Girdiğiniz Şifreler Birbirinden Farklı!", HttpStatus.ACCEPTED);
        }
        if(userDto.getEmail()==null || userDto.getEmail().isEmpty()){
            return new ResponseEntity("Email Zorunlu!", HttpStatus.ACCEPTED);
        }

        if(userService.userKaydet(userDto)){

            return new ResponseEntity("Kayıt Başarılı!", HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity("Kayıt Hatalı!", HttpStatus.ACCEPTED);
        }

    }
    @PutMapping(value = "/user-kullanici-adi/guncelle")
    public ResponseEntity userGuncelle(@RequestBody UserDto userDto){
        User user=userService.findByUserId(userDto.getId());


    if(userService.userGuncelle(userDto)){
        return new ResponseEntity("Güncelleme Başarılı!", HttpStatus.ACCEPTED);
    }
    else{
        return new ResponseEntity("Güncelleme Hatalı!", HttpStatus.ACCEPTED);
    }
    }

    @PutMapping(value = "/user-sifre/guncelle")
    public ResponseEntity userSifreGuncelle(@RequestBody UserDto userDto){

        User user=userService.findByUserEmail(userDto.getEmail());
        if(user == null){
            return new ResponseEntity("Kullanıcı Bulunamadı!", HttpStatus.ACCEPTED);
        }
        if (!user.getSifre().equals(userDto.getSifre())){
            return new ResponseEntity("Eski Şifrenizi Hatalı Girdiniz!", HttpStatus.ACCEPTED);
        }

        if(!userDto.getYeniSifre().equals(userDto.getYeniSifre2())){
            return new ResponseEntity("Girdiğiniz Yeni Şifreler Birbirinden Farklı!", HttpStatus.ACCEPTED);
        }

        if(userService.userSifreGuncelle(userDto,user)){
            return new ResponseEntity("Güncelleme Başarılı!", HttpStatus.ACCEPTED);

        }
        else{
            return new ResponseEntity("Güncelleme Hatalı!", HttpStatus.ACCEPTED);
        }


    }

    @GetMapping(value = "/user/active")
    public ResponseEntity userActive(@RequestParam(value = "activationCode") String activationCode){

        if(userService.userActive(activationCode)){
            return new ResponseEntity("Doğrulama Başarılı!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Doğrulama Hatalı!", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/user/sil")
    public ResponseEntity userSil(@RequestParam(value = "id") Long id){
        if(userService.userSil(id)){
            return new ResponseEntity("Silme Başarılı!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Silme Hatalı!", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity userLogIn(@RequestBody UserDto userDto){
        if(userDto.getEmail()==null || userDto.getEmail().isEmpty()){
            return new ResponseEntity("Kullanıcı Adı Zorunlu!", HttpStatus.ACCEPTED);
        }
        if(userDto.getSifre()==null || userDto.getSifre().isEmpty()) {
            return new ResponseEntity("Şifre Zorunlu!", HttpStatus.ACCEPTED);
        }

        if(userService.userLogIn(userDto)){

            return new ResponseEntity("LogIn Başarılı!", HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity("LogIn Hatalı!", HttpStatus.ACCEPTED);
        }

    }


}
