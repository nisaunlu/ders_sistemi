package egitim.nisa.services.impl;

import egitim.nisa.models.dto.OgrenciDto;
import egitim.nisa.models.entities.KisiselBilgiler;
import egitim.nisa.models.entities.Ogrenci;
import egitim.nisa.repositories.KisiselBilgilerRepo;
import egitim.nisa.repositories.OgrenciRepo;
import egitim.nisa.services.OgrenciService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OgrenciServiceImpl implements OgrenciService {

    @Autowired
    OgrenciRepo ogrenciRepo;

    @Autowired
    KisiselBilgilerRepo kisiselBilgilerRepo;



    @Override
    public List<Ogrenci> tumKayit() {
        List<Ogrenci> ogrenciList = ogrenciRepo.findAll();
        return ogrenciList;
    }

    @Override
    public Boolean ogrenciKaydet(OgrenciDto ogrenciDto){
        try{

            KisiselBilgiler kisiselBilgiler = new KisiselBilgiler();
            kisiselBilgiler.setIsimSoyisim(ogrenciDto.getKisiselBilgilerDto().getIsimSoyisim());
            kisiselBilgiler.setTc(ogrenciDto.getKisiselBilgilerDto().getTc());
            kisiselBilgiler.setTelefonNo(ogrenciDto.getKisiselBilgilerDto().getTelefonNo());
            kisiselBilgiler.setDogumTarihi(ogrenciDto.getKisiselBilgilerDto().getDogumTarihi());
            kisiselBilgiler.setStatus(true);
            kisiselBilgiler.setKayitEklenmeTarihi(new Date());
            kisiselBilgilerRepo.saveAndFlush(kisiselBilgiler);

            Ogrenci ogrenci = new Ogrenci();
            ogrenci.setOgrenciAdi(ogrenciDto.getOgrenciAdi());
            ogrenci.setOgrenciSoyadi(ogrenciDto.getOgrenciSoyadi());
            ogrenci.setStatus(true);
            ogrenci.setKayitEklenmeTarihi(new Date());
            ogrenci.setKisiselBilgiler(kisiselBilgiler);
            ogrenciRepo.saveAndFlush(ogrenci);

            return true;

        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean ogrenciGuncelle(OgrenciDto ogrenciDto) {
        try {
            Ogrenci ogrenci = ogrenciRepo.findByOgrenciId(ogrenciDto.getId());
            if (ogrenci == null) {
                return false;
            }

            ogrenci.setOgrenciAdi(ogrenciDto.getOgrenciAdi());
            ogrenci.setOgrenciSoyadi(ogrenciDto.getOgrenciSoyadi());
            ogrenci.setKayitEklenmeTarihi(new Date());
            ogrenciRepo.saveAndFlush(ogrenci);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean ogrenciSil(Long id) {
        try {
            Ogrenci ogrenci = ogrenciRepo.findByOgrenciId(id);
            if (ogrenci == null) {
                return false;
            }

            ogrenci.setStatus(false);
            ogrenciRepo.saveAndFlush(ogrenci);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Ogrenci findByOgrenciId(Long id){
        try {
            Ogrenci ogrenci=ogrenciRepo.findByOgrenciId(id);
            return ogrenci;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
