package egitim.nisa.controllers;


import egitim.nisa.models.dto.OgrenciDto;
import egitim.nisa.models.entities.Ogrenci;
import egitim.nisa.services.OgrenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OgrenciController {

    @Autowired
    OgrenciService ogrenciService;


    @GetMapping(value ="/ogrenci/listele")
    public ResponseEntity ogrenciListele(){

       List<Ogrenci> ogrenciList = ogrenciService.tumKayit();

        return new ResponseEntity(ogrenciList, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/ogrenci/kaydet")
    public ResponseEntity ogrenciKaydet(@RequestBody OgrenciDto ogrenciDto){

        if(ogrenciDto.getOgrenciAdi()==null || ogrenciDto.getOgrenciAdi().isEmpty()){

            return new ResponseEntity("Öğrenci Adı Zorunlu",HttpStatus.ACCEPTED);
        }
        if(ogrenciDto.getOgrenciSoyadi()==null || ogrenciDto.getOgrenciSoyadi().isEmpty()){

            return new ResponseEntity("Öğrenci Soyadı Zorunlu",HttpStatus.ACCEPTED);
        }

        if(ogrenciService.ogrenciKaydet(ogrenciDto)){

            return new ResponseEntity("Kayıt Başarılı",HttpStatus.ACCEPTED);

        }
        else{
            return new ResponseEntity("Kayıt Başarısız Hata!!",HttpStatus.ACCEPTED);
        }

    }


    @PutMapping(value = "/ogrenci/guncelle")
    public ResponseEntity ogrenciGuncelle(@RequestBody OgrenciDto ogrenciDto) {
        Ogrenci ogrenci=ogrenciService.findByOgrenciId(ogrenciDto.getId());


        if (ogrenciService.ogrenciGuncelle(ogrenciDto)) {
            return new ResponseEntity("Güncelleme Başarılı",HttpStatus.ACCEPTED);

        }

        ogrenciService.findByOgrenciId(ogrenciDto.getId());


        return new ResponseEntity("Güncelleme Hatalı !",HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/ogrenci/sil")
    public ResponseEntity ogrenciSil(@RequestParam(value = "id") Long id) {
        if (ogrenciService.ogrenciSil(id)) {
            return new ResponseEntity("Öğrenci Silme Başarılı",HttpStatus.ACCEPTED);

        }

        return new ResponseEntity("Öğrenci Silme Hatalı !",HttpStatus.ACCEPTED);
    }

}
