package egitim.nisa.controllers;


import egitim.nisa.models.dto.OgrenciDersDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.models.entities.Ogrenci;
import egitim.nisa.models.entities.OgrenciDers;
import egitim.nisa.models.entities.OgretmenDers;
import egitim.nisa.services.DersService;
import egitim.nisa.services.OgrenciDersSevice;
import egitim.nisa.services.OgrenciService;
import egitim.nisa.services.OgretmenDersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@Controller
public class OgrenciDersController {

    @Autowired
    OgrenciDersSevice ogrenciDersSevice;

    @Autowired
    OgrenciService ogrenciService;

    @Autowired
    OgretmenDersService ogretmenDersService;

    @Autowired
    DersService dersService;

    @GetMapping(value = "/ogrenci/ders/listele")
    public ResponseEntity ogrenciDersListele(){

        List<OgrenciDers> ogrenciDersList =ogrenciDersSevice.tumKayit();

        return new ResponseEntity(ogrenciDersList , HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/ogrenci-ders/kaydet")
    public ResponseEntity ogrenciDersKaydet(@RequestBody  OgrenciDersDto ogrenciDersDto){
      Ogrenci ogrenci= ogrenciService.findByOgrenciId(ogrenciDersDto.getOgrenciId());
        OgretmenDers ogretmenDers=ogretmenDersService.findByOgretmenDersId(ogrenciDersDto.getOgretmenDersId());


        if(ogrenci==null){
            return new ResponseEntity<>("Öğrenci Id Geçerli Değil!", HttpStatus.ACCEPTED);
        }
        if(ogretmenDers==null){
            return new ResponseEntity<>("Öğretmen Ders Id Geçersiz!", HttpStatus.ACCEPTED);
        }
        if(ogrenciDersSevice.ogrenciDersKaydet(ogrenciDersDto)){
            return new ResponseEntity("Kayıt Başarılı!", HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity("Kayıt Başarısız!", HttpStatus.ACCEPTED);
        }

    }


    @PutMapping(value = "/ogrenci-ders/guncelle")
    public ResponseEntity ogrenciDersGuncelle(@RequestBody OgrenciDersDto ogrenciDersDto){

        if(ogrenciDersDto.getId()==null){
            return new ResponseEntity("Öğrenci-Ders Id Zorunlu",HttpStatus.ACCEPTED);
        }

        if(ogrenciDersDto.getOgretmenDersId()==null){
            return new ResponseEntity("Öğretmen-Ders Id Zorunlu",HttpStatus.ACCEPTED);
        }

        if(ogrenciDersDto.getOgrenciId()==null){
            return new ResponseEntity("Öğrenci Id Zorunlu",HttpStatus.ACCEPTED);
        }
        Ogrenci ogrenci=ogrenciService.findByOgrenciId(ogrenciDersDto.getOgrenciId());
        OgretmenDers ogretmenDers=ogretmenDersService.findByOgretmenDersId(ogrenciDersDto.getOgretmenDersId());

        if(ogrenci==null){
            return new ResponseEntity("Öğrenci Id Geçerli Değil!", HttpStatus.ACCEPTED);
        }

        if(ogretmenDers==null){
            return new ResponseEntity("Öğretmen-Ders Id Geçerli Değil!", HttpStatus.ACCEPTED);
        }

        if(ogrenciDersSevice.ogrenciDersGuncelle(ogrenciDersDto.getId(),ogrenci,ogretmenDers)){
            return new ResponseEntity("Güncelleme Başarılı!", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity("Güncelleme Hatalı!", HttpStatus.ACCEPTED);

    }

    @DeleteMapping(value = "/ogrenci-ders/sil")
    public ResponseEntity ogrenciDersSil(@RequestParam(value = "id") Long id){
        if(ogrenciDersSevice.ogrenciDersSil(id)){
            return new ResponseEntity("Öğrenci-Ders Silme Başarılı!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Öğrenci-Ders Silme Hatalı!", HttpStatus.ACCEPTED);
    }
}
