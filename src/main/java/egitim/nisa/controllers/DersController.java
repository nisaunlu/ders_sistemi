package egitim.nisa.controllers;


import egitim.nisa.models.dto.DersDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.services.DersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DersController {

    @Autowired
    DersService dersService;

    @GetMapping( value = "/ders/listele")
    public ResponseEntity dersListele(){

        List<Ders> dersList= dersService.tumKayit();

        return new ResponseEntity<>(dersList, HttpStatus.ACCEPTED);

    }
    @PostMapping(value = "/ders/kaydet")
    public ResponseEntity dersKaydet(@RequestBody DersDto dersDto){

        if(dersDto.getDersAdi()==null || dersDto.getDersAdi().isEmpty()){
            return new ResponseEntity("Ders Adı Zorunlu",HttpStatus.ACCEPTED);
        }
        if(dersDto.getDersKredisi()==0 || dersDto.getDersKredisi()==null){
            return new ResponseEntity("Ders Kredisi Zorunlu",HttpStatus.ACCEPTED);
        }

        if(dersService.dersKaydet(dersDto)){
            return new ResponseEntity("Kayıt Başarılı.",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity("Kayıt Başarısız Hata!!",HttpStatus.ACCEPTED);
        }
    }

    @PutMapping(value = "/ders/guncelle")
    public ResponseEntity dersGuncelle(@RequestBody DersDto dersDto){
        if(dersService.dersGuncelle(dersDto)){
            return new ResponseEntity("Ders Güncellendi.", HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("Güncelleme Hatalı!", HttpStatus.ACCEPTED);


    }

    @DeleteMapping(value = "/ders/sil")
    public ResponseEntity dersSil(@RequestParam(value = "id") Long id){

        if(dersService.dersSil(id)){
            return new ResponseEntity("Silme Başarılı!", HttpStatus.ACCEPTED);

        }
        return new ResponseEntity("Silme Hatalı", HttpStatus.ACCEPTED);
    }

}
