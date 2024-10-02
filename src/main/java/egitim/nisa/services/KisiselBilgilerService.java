package egitim.nisa.services;

import egitim.nisa.models.dto.KisiselBilgilerDto;
import egitim.nisa.models.entities.KisiselBilgiler;

import java.util.List;

public interface KisiselBilgilerService {

    List<KisiselBilgiler> tumKayit();

    Boolean kisiselBilgilerKaydet(KisiselBilgilerDto kisiselBilgilerDto);

    Boolean kisiselBilgilerGuncelle(KisiselBilgilerDto kisiselBilgilerDto);

    KisiselBilgiler findByKisiselBilgilerId(Long id);

    Boolean kisiselBilgilerSil(Long id);
}
