package egitim.nisa.controllers;


import egitim.nisa.models.dto.OgretmenDersDto;
import egitim.nisa.models.dto.OgretmenDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.models.entities.Ogretmen;
import egitim.nisa.models.entities.OgretmenDers;
import egitim.nisa.services.DersService;
import egitim.nisa.services.OgrenciService;
import egitim.nisa.services.OgretmenDersService;
import egitim.nisa.services.OgretmenService;
import egitim.nisa.services.impl.DersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OgretmenDersController {

    @Autowired
    OgretmenDersService ogretmenDersService;

    @Autowired
    OgretmenService ogretmenService;

    @Autowired
    DersService dersService;



    @GetMapping(value = "ogretmen-ders/listele")
    public ResponseEntity ogretmenDersListele() {
        List<OgretmenDers> ogretmenDersList = ogretmenDersService.tumKayit();
        return new ResponseEntity(ogretmenDersList, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "ogretmen-ders/kaydet")
    public ResponseEntity ogretmenDersKaydet(@RequestBody OgretmenDersDto ogretmenDersDto){

        if(ogretmenDersDto.getOgretmenId()==null){
            return new ResponseEntity("Öğretmen Id Zorunlu",HttpStatus.BAD_REQUEST);
        }

        Ogretmen ogretmen = ogretmenService.findByOgretmenId(ogretmenDersDto.getOgretmenId());
        Ders ders= dersService.findByDersId(ogretmenDersDto.getDersId());

        if (ogretmen == null) {
            return new ResponseEntity("Ogretmen Id Gecerli Degil", HttpStatus.ACCEPTED);
        }
        if (ders == null) {
            return new ResponseEntity("Ders Id Gecerli Degil", HttpStatus.ACCEPTED);
        }

        if(ogretmenDersService.ogretmenDersKayit(ogretmenDersDto)) {
            return new ResponseEntity("Kayıt Başarılı", HttpStatus.ACCEPTED);

        } else {
            return new ResponseEntity("Kayıt Başarısız", HttpStatus.ACCEPTED);

        }

    }

    @PutMapping(value = "/ogretmen-ders/guncelle")
    public ResponseEntity ogretmenDersGuncelle(@RequestBody OgretmenDersDto ogretmenDersDto){

        if(ogretmenDersDto.getId()==null){
            return new ResponseEntity("Öğretmen-Ders Id Zorunlu",HttpStatus.BAD_REQUEST);
        }

        if(ogretmenDersDto.getOgretmenId()==null){
            return new ResponseEntity("Öğretmen Id Zorunlu",HttpStatus.BAD_REQUEST);
        }

        if(ogretmenDersDto.getDersId()==null){
            return new ResponseEntity("Ders Id Zorunlu",HttpStatus.BAD_REQUEST);
        }

        Ogretmen ogretmen = ogretmenService.findByOgretmenId(ogretmenDersDto.getOgretmenId());
        Ders ders= dersService.findByDersId(ogretmenDersDto.getDersId());

        if (ogretmen == null) {
            return new ResponseEntity("Ogretmen Id Gecerli Degil", HttpStatus.ACCEPTED);
        }
        if (ders == null) {
            return new ResponseEntity("Ders Id Gecerli Degil", HttpStatus.ACCEPTED);
        }

        if(ogretmenDersService.ogretmenDersGuncelle(ogretmenDersDto.getId(),ogretmen,ders)){
            return new ResponseEntity("Güncelleme Başarılı!", HttpStatus.ACCEPTED);
        }


        return new ResponseEntity("Güncelleme Hatalı!", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/ogretmen-ders/sil")
    public ResponseEntity ogretmenDersSil(@RequestParam(value = "id") Long id){
        if(ogretmenDersService.ogretmenDersSil(id)){
            return new ResponseEntity("Öğretmen-Ders Silme Başarılı!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Öğretmen-Ders Silme Hatalı!", HttpStatus.ACCEPTED);
    }

}
