package egitim.nisa.services.impl;

import egitim.nisa.models.dto.DersDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.repositories.DersRepo;
import egitim.nisa.services.DersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class DersServiceImpl implements DersService {

    @Autowired
    DersRepo dersRepo;

    @Override
    public List<Ders> tumKayit(){

        List<Ders> dersList = dersRepo.findAll();

        return dersList;

    }

    @Override
    public Boolean dersKaydet(DersDto dersDto){
        try {
            Ders ders = new Ders();
            ders.setDersAdi(dersDto.getDersAdi());
            ders.setDersKredisi(dersDto.getDersKredisi());
            ders.setKayitEklenmeTarihi(new Date());
            ders.setStatus(true);
            dersRepo.saveAndFlush(ders);
            return true;
        }
        catch (Exception e){

            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Ders findByDersId(Long id){
        try {
            Ders ders= dersRepo.findByDersId(id);

            return ders;
        }
       catch (Exception e){
            e.printStackTrace();
            return null;
       }
    }

    @Override
    public Boolean dersGuncelle(DersDto dersDto) {
        try {
            Ders ders= dersRepo.findByDersId(dersDto.getId());
            if(ders==null){

                return false;

            }

            ders.setDersAdi(dersDto.getDersAdi());
            ders.setDersKredisi(dersDto.getDersKredisi());
            ders.setKayitEklenmeTarihi(new Date());
            dersRepo.saveAndFlush(ders);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public Boolean dersSil(Long id) {
        try {
            Ders ders=dersRepo.findByDersId(id);
            if(ders==null){
                return false;
            }
            ders.setStatus(false);
            dersRepo.saveAndFlush(ders);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }
}
