package egitim.nisa.services.impl;


import egitim.nisa.models.dto.KisiselBilgilerDto;
import egitim.nisa.models.dto.OgretmenDto;
import egitim.nisa.models.entities.KisiselBilgiler;
import egitim.nisa.models.entities.Ogretmen;
import egitim.nisa.repositories.KisiselBilgilerRepo;
import egitim.nisa.repositories.OgretmenRepo;
import egitim.nisa.services.OgretmenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OgretmenServiceImpl implements OgretmenService {


    @Autowired
    OgretmenRepo ogretmenRepo;

    @Autowired
    KisiselBilgilerRepo kisiselBilgilerRepo;

    @Override
    public List<Ogretmen> tumKayit(){
        List<Ogretmen> ogretmenList= ogretmenRepo.findAll();
        return ogretmenList;
    }

    @Override
    public Boolean ogretmenKaydet(OgretmenDto ogretmenDto) {
        try {
            KisiselBilgiler kisiselBilgiler= new KisiselBilgiler();
            kisiselBilgiler.setIsimSoyisim(ogretmenDto.getKisiselBilgilerDto().getIsimSoyisim());
            kisiselBilgiler.setTc(ogretmenDto.getKisiselBilgilerDto().getTc());
            kisiselBilgiler.setTelefonNo(ogretmenDto.getKisiselBilgilerDto().getTelefonNo());
            kisiselBilgiler.setDogumTarihi(ogretmenDto.getKisiselBilgilerDto().getDogumTarihi());
            kisiselBilgiler.setStatus(true);
            kisiselBilgiler.setKayitEklenmeTarihi(new Date());
            kisiselBilgilerRepo.saveAndFlush(kisiselBilgiler);



            Ogretmen ogretmen = new Ogretmen();
            ogretmen.setOgretmenAdi(ogretmenDto.getOgretmenAdi());
            ogretmen.setOgretmenSoyadi(ogretmenDto.getOgretmenSoyadi());
            ogretmen.setStatus(true);
            ogretmen.setKayitEklenmeTarihi(new Date());
            ogretmen.setKisiselBilgiler(kisiselBilgiler);
            ogretmenRepo.saveAndFlush(ogretmen);


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public Ogretmen findByOgretmenId(Long id) {
        try {
           //Optional<Ogretmen>  ogretmen = ogretmenRepo.findById(id);
            //return ogretmen.get();

           Ogretmen ogretmen2 = ogretmenRepo.findByOgretmenId(id);

            return ogretmen2;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Boolean ogretmenGuncelle(OgretmenDto ogretmenDto){
        try{
            Ogretmen ogretmen= ogretmenRepo.findByOgretmenId(ogretmenDto.getId());
            if(ogretmen==null){
                return false;
            }


            ogretmen.setOgretmenAdi(ogretmenDto.getOgretmenAdi());
            ogretmen.setOgretmenSoyadi(ogretmenDto.getOgretmenSoyadi());
            ogretmen.setKayitEklenmeTarihi(new Date());
            ogretmenRepo.saveAndFlush(ogretmen);
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean ogretmenSil(Long id) {
        try {
            Ogretmen ogretmen=ogretmenRepo.findByOgretmenId(id);
            if(ogretmen==null){
                return false;
            }
            ogretmen.setStatus(false);
            ogretmenRepo.saveAndFlush(ogretmen);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
