package egitim.nisa.controllers;


import egitim.nisa.models.entities.Ogretmen;
import egitim.nisa.models.dto.OgretmenDto;
import egitim.nisa.services.OgrenciService;
import egitim.nisa.services.OgretmenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OgretmenController {

    @Autowired
    OgretmenService ogretmenService;

        @GetMapping(value = "/ogretmen/listele")
        public ResponseEntity ogretmenListele() {
            List<Ogretmen> ogretmenList = ogretmenService.tumKayit();
            return new ResponseEntity(ogretmenList, HttpStatus.ACCEPTED);

        }

        @PostMapping(value = "/ogretmen/kaydet")
       public ResponseEntity ogretmenKaydet(@RequestBody OgretmenDto ogretmenDto) {

            if(ogretmenDto.getOgretmenAdi()==null || ogretmenDto.getOgretmenAdi().isEmpty()){
                return new ResponseEntity("Öğretmen Adı Zorunlu",HttpStatus.ACCEPTED);
            }
            if(ogretmenDto.getOgretmenSoyadi()==null || ogretmenDto.getOgretmenSoyadi().isEmpty()){
                return new ResponseEntity("Öğretmen Soyadı Zorunlu",HttpStatus.ACCEPTED);
            }


            if(ogretmenService.ogretmenKaydet(ogretmenDto)){
                return new ResponseEntity("Kayıt Başarılı",HttpStatus.ACCEPTED);

            } else {
                return new ResponseEntity("Kayıt Başarısız Hata!!",HttpStatus.ACCEPTED);
            }

        }

        @PutMapping(value = "/ogretmen/guncelle")
      public ResponseEntity ogretmenGuncelle(@RequestBody OgretmenDto ogretmenDto){
        if(ogretmenService.ogretmenGuncelle(ogretmenDto)){

            return new ResponseEntity("Güncelleme Başarılı." , HttpStatus.ACCEPTED);

        }
        return new ResponseEntity("Güncelleme Hatalı!", HttpStatus.ACCEPTED);


        }

        @DeleteMapping(value = "/ogretmen/sil")
        public ResponseEntity ogretmenSil(@RequestParam(value = "id") Long id){
            if(ogretmenService.ogretmenSil(id)){
                return new ResponseEntity("Öğretmen Silme Başarılı!", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity("Öğretmen Silme Hatalı!", HttpStatus.ACCEPTED);
        }

}