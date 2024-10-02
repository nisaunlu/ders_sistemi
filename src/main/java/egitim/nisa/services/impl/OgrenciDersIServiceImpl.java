package egitim.nisa.services.impl;


import egitim.nisa.models.dto.OgrenciDersDto;
import egitim.nisa.models.dto.OgrenciDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.models.entities.Ogrenci;
import egitim.nisa.models.entities.OgrenciDers;
import egitim.nisa.models.entities.OgretmenDers;
import egitim.nisa.repositories.OgrenciDersRepo;
import egitim.nisa.services.OgrenciDersSevice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class OgrenciDersIServiceImpl implements OgrenciDersSevice{


    @Autowired
    OgrenciDersRepo ogrenciDersRepo;

    @Override
    public List<OgrenciDers> tumKayit(){
        List<OgrenciDers> ogrenciDersList = ogrenciDersRepo.findAll();
        return ogrenciDersList;
    }

    @Override
    public Boolean ogrenciDersKaydet(OgrenciDersDto ogrenciDersDto){
        try {
            OgrenciDers ogrenciDers = new OgrenciDers();
            ogrenciDers.setOgrenci(new Ogrenci());
            ogrenciDers.getOgrenci().setId(ogrenciDersDto.getOgrenciId());
            ogrenciDers.setOgretmenDers(new OgretmenDers());
            ogrenciDers.getOgretmenDers().setId(ogrenciDersDto.getOgretmenDersId());
            ogrenciDersRepo.saveAndFlush(ogrenciDers);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;

        }

    }

    @Override
    public Boolean ogrenciDersGuncelle(Long id, Ogrenci ogrenci, OgretmenDers ogretmenDers) {
    try {
        OgrenciDers ogrenciDers=ogrenciDersRepo.findByOgrenciDersId(id);
        if(ogrenciDers==null){
            return false;
        }
        ogrenciDers.setOgretmenDers(ogretmenDers);
        ogrenciDers.setOgrenci(ogrenci);
        ogrenciDersRepo.saveAndFlush(ogrenciDers);
        return true;
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }


    }


    @Override
    public OgrenciDers findByOgrenciDersId(Long id) {
        try{
            OgrenciDers ogrenciDers=ogrenciDersRepo.findByOgrenciDersId(id);
            return ogrenciDers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean ogrenciDersSil(Long id) {
        try {
            OgrenciDers ogrenciDers=ogrenciDersRepo.findByOgrenciDersId(id);
            if(ogrenciDers==null){
                return false;
            }
            ogrenciDers.setStatus(false);
            ogrenciDersRepo.saveAndFlush(ogrenciDers);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
