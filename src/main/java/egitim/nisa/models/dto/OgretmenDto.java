package egitim.nisa.models.dto;

import lombok.Data;

@Data
public class OgretmenDto {

    private Long id;
    private String ogretmenAdi;
    private String ogretmenSoyadi;
    private KisiselBilgilerDto kisiselBilgilerDto;
}
