package egitim.nisa.services;

import egitim.nisa.models.dto.OgrenciDto;
import egitim.nisa.models.entities.Ogrenci;

import java.util.List;

public interface OgrenciService {


    List<Ogrenci> tumKayit();

    Boolean ogrenciKaydet(OgrenciDto ogrenciDto);

    Boolean ogrenciGuncelle(OgrenciDto ogrenciDto);

    Boolean ogrenciSil(Long id);

    Ogrenci findByOgrenciId(Long id);
}
