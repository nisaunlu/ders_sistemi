package egitim.nisa.controllers;


import egitim.nisa.models.dto.KisiselBilgilerDto;
import egitim.nisa.models.entities.KisiselBilgiler;
import egitim.nisa.services.KisiselBilgilerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class KisiselBilgilerController {


    @Autowired
    KisiselBilgilerService kisiselBilgilerService;


    @GetMapping(value = "/kisisel-bilgiler/listele")
    public ResponseEntity kisiselBilgilerListele(){

        List<KisiselBilgiler> kisiselBilgilerList =kisiselBilgilerService.tumKayit();
        return new ResponseEntity(kisiselBilgilerList, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "kisisel-bilgiler/kaydet")
    public ResponseEntity kisiselBilgilerKaydet(@RequestBody KisiselBilgilerDto kisiselBilgilerDto){
       if(kisiselBilgilerDto.getIsimSoyisim()==null || kisiselBilgilerDto.getIsimSoyisim().isEmpty()){

           return new ResponseEntity("İsim Soyisim Girilmesi Zorunlu!", HttpStatus.ACCEPTED);
       }
        if(kisiselBilgilerDto.getTc()==null || kisiselBilgilerDto.getTc().isEmpty()){

            return new ResponseEntity("Tc Girilmesi Zorunlu!", HttpStatus.ACCEPTED);
        }
        if(kisiselBilgilerDto.getDogumTarihi()==null){

            return new ResponseEntity("Doğum Tarihi Girilmesi Zorunlu!", HttpStatus.ACCEPTED);
        }
        if(kisiselBilgilerDto.getTelefonNo()==null || kisiselBilgilerDto.getTelefonNo().isEmpty()){

            return new ResponseEntity("Telefon Numarası Girilmesi Zorunlu!", HttpStatus.ACCEPTED);
        }

        if(kisiselBilgilerService.kisiselBilgilerKaydet(kisiselBilgilerDto)){
            return new ResponseEntity("Kayıt Başarılı!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Kayıt Başarısız!", HttpStatus.ACCEPTED);
        }
    }

    @PutMapping(value = "/kisisel-bilgiler/guncelle")
    public ResponseEntity kisiselBilgilerGuncelle(@RequestBody KisiselBilgilerDto kisiselBilgilerDto){
        KisiselBilgiler kisiselBilgiler=kisiselBilgilerService.findByKisiselBilgilerId(kisiselBilgilerDto.getId());
        if(kisiselBilgilerService.kisiselBilgilerGuncelle(kisiselBilgilerDto)){
            return new ResponseEntity("Güncelleme Başarılı!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Güncelleme Hatalı!", HttpStatus.ACCEPTED);

    }

    @DeleteMapping(value = "/kisisel-bilgiler/sil")
    public ResponseEntity kisiselBilgilerSil(@RequestParam(value = "id") Long id){
        if(kisiselBilgilerService.kisiselBilgilerSil(id)){
            return new ResponseEntity("Kişisel Bilgiler Silme Başarılı!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Kişisel Bilgiler Silme Hatalı!", HttpStatus.ACCEPTED);
    }


}
