package egitim.nisa.services.impl;


import egitim.nisa.models.dto.KisiselBilgilerDto;
import egitim.nisa.models.entities.KisiselBilgiler;
import egitim.nisa.repositories.KisiselBilgilerRepo;
import egitim.nisa.services.KisiselBilgilerService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class KisiselBilgilerServiceImpl implements KisiselBilgilerService {

    @Autowired
    KisiselBilgilerRepo kisiselBilgilerRepo;

    @Override
    public List<KisiselBilgiler> tumKayit() {
        List<KisiselBilgiler> kisiselBilgilerList = kisiselBilgilerRepo.findAll();
        return kisiselBilgilerList;


    }

    @Override
    public Boolean kisiselBilgilerKaydet(KisiselBilgilerDto kisiselBilgilerDto){
        try {
            KisiselBilgiler kisiselBilgiler = new KisiselBilgiler();
            kisiselBilgiler.setIsimSoyisim(kisiselBilgilerDto.getIsimSoyisim());
            kisiselBilgiler.setTc(kisiselBilgilerDto.getTc());
            kisiselBilgiler.setTelefonNo(kisiselBilgilerDto.getTelefonNo());
            kisiselBilgiler.setDogumTarihi(kisiselBilgilerDto.getDogumTarihi());
            kisiselBilgiler.setStatus(true);
            kisiselBilgiler.setKayitEklenmeTarihi(new Date());
            kisiselBilgilerRepo.saveAndFlush(kisiselBilgiler);
            return true;


        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public KisiselBilgiler findByKisiselBilgilerId(Long id) {
        try{
            KisiselBilgiler kisiselBilgiler= kisiselBilgilerRepo.findByKisiselBigiler(id);
            return kisiselBilgiler;
        }
       catch (Exception e){
            e.printStackTrace();
            return null;
       }
    }

    @Override
    public Boolean kisiselBilgilerGuncelle(KisiselBilgilerDto kisiselBilgilerDto) {
        try {
            KisiselBilgiler kisiselBilgiler=kisiselBilgilerRepo.findByKisiselBigiler(kisiselBilgilerDto.getId());
            if(kisiselBilgiler==null){

                return false;
            }
            kisiselBilgiler.setIsimSoyisim(kisiselBilgilerDto.getIsimSoyisim());
            kisiselBilgiler.setTc(kisiselBilgilerDto.getTc());
            kisiselBilgiler.setDogumTarihi(kisiselBilgilerDto.getDogumTarihi());
            kisiselBilgiler.setTelefonNo(kisiselBilgiler.getTelefonNo());
            kisiselBilgiler.setKayitEklenmeTarihi(new Date());
            kisiselBilgilerRepo.saveAndFlush(kisiselBilgiler);

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean kisiselBilgilerSil(Long id) {
        try {
            KisiselBilgiler kisiselBilgiler=kisiselBilgilerRepo.findByKisiselBigiler(id);
            if(kisiselBilgiler==null){
                return false;
            }
            kisiselBilgiler.setStatus(false);
            kisiselBilgilerRepo.saveAndFlush(kisiselBilgiler);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}