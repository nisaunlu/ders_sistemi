package egitim.nisa.services;

import egitim.nisa.models.dto.OgretmenDersDto;
import egitim.nisa.models.entities.Ders;
import egitim.nisa.models.entities.Ogretmen;
import egitim.nisa.models.entities.OgretmenDers;

import java.util.List;

public interface OgretmenDersService {
    List<OgretmenDers> tumKayit();

    Boolean ogretmenDersKayit(OgretmenDersDto ogretmenDersDto);

    OgretmenDers findByOgretmenDersId(Long id);

    Boolean ogretmenDersGuncelle(Long id , Ogretmen ogretmen, Ders ders);

    Boolean ogretmenDersSil(Long id);
}
