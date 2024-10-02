package egitim.nisa.services;


import egitim.nisa.models.dto.OgretmenDto;
import egitim.nisa.models.entities.Ogretmen;

import java.util.List;

public interface OgretmenService {


    Ogretmen findByOgretmenId(Long id);

    List<Ogretmen> tumKayit();

    Boolean ogretmenKaydet(OgretmenDto ogretmenDto);

    Boolean ogretmenGuncelle(OgretmenDto ogretmenDto);

    Boolean ogretmenSil(Long id);
}
