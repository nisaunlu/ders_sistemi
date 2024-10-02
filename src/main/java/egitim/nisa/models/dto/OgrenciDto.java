package egitim.nisa.models.dto;

import lombok.Data;

@Data
public class OgrenciDto {

    private Long id;
    private String ogrenciAdi;
    private String ogrenciSoyadi;
    private KisiselBilgilerDto kisiselBilgilerDto;

}
