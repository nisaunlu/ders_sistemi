package egitim.nisa.services.impl;



import egitim.nisa.models.dto.OgretmenDersDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.models.entities.Ogretmen;
import egitim.nisa.models.entities.OgretmenDers;
import egitim.nisa.repositories.OgretmenDersRepo;
import egitim.nisa.services.OgretmenDersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class OgretmenDersServiceImpl implements OgretmenDersService {

    @Autowired
    OgretmenDersRepo ogretmenDersRepo;

    @Override
    public List<OgretmenDers> tumKayit(){
        List<OgretmenDers> ogretmenDersList = ogretmenDersRepo.findAll();
        return ogretmenDersList;


    }

    @Override
    public Boolean ogretmenDersKayit(OgretmenDersDto ogretmenDersDto){
        try {
            OgretmenDers ogretmenDers = new OgretmenDers();
            ogretmenDers.setOgretmen(new Ogretmen());
            ogretmenDers.getOgretmen().setId(ogretmenDersDto.getOgretmenId());
            ogretmenDers.setDers(new Ders());
            ogretmenDers.getDers().setId(ogretmenDersDto.getDersId());
            ogretmenDersRepo.saveAndFlush(ogretmenDers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OgretmenDers findByOgretmenDersId(Long id){
        try {
            OgretmenDers ogretmenDers=ogretmenDersRepo.findByOgretmenDersId(id);
            return ogretmenDers;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Boolean ogretmenDersGuncelle(Long id, Ogretmen ogretmen, Ders ders) {
        try {
            OgretmenDers ogretmenDers=ogretmenDersRepo.findByOgretmenDersId(id);
            if(ogretmenDers==null){
                return false;
            }
            ogretmenDers.setOgretmen(ogretmen);
            ogretmenDers.setDers(ders);
            ogretmenDersRepo.saveAndFlush(ogretmenDers);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean ogretmenDersSil(Long id) {
        try {
            OgretmenDers ogretmenDers=ogretmenDersRepo.findByOgretmenDersId(id);
            if(ogretmenDers==null){
                return false;
            }
            ogretmenDers.setStatus(false);
            ogretmenDersRepo.saveAndFlush(ogretmenDers);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
