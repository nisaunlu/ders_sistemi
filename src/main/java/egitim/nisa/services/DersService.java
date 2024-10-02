package egitim.nisa.services;

import egitim.nisa.models.dto.DersDto;
import egitim.nisa.models.dto.OgrenciDto;
import egitim.nisa.models.entities.Ders;

import java.util.List;

public interface DersService {


    Ders findByDersId(Long id);

    List<Ders> tumKayit();

    Boolean dersKaydet(DersDto dersDto);

    Boolean dersGuncelle(DersDto dersDto);

    Boolean dersSil(Long id);


}
