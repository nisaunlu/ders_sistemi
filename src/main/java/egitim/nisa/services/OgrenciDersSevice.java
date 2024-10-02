package egitim.nisa.services;


import egitim.nisa.models.dto.OgrenciDersDto;
import egitim.nisa.models.entities.*;

import java.util.Date;
import java.util.List;

public interface OgrenciDersSevice {

    List<OgrenciDers> tumKayit();

    Boolean ogrenciDersKaydet(OgrenciDersDto ogrenciDersDto);

    Boolean ogrenciDersGuncelle(Long id, Ogrenci ogrenci, OgretmenDers ogretmenDers);

    OgrenciDers findByOgrenciDersId(Long id);

    Boolean ogrenciDersSil(Long id);

}
