package egitim.nisa.models.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String kullaniciAdi;
    private String sifre;
    private String email;
    private String sifre2;
    private String yeniSifre;
    private String yeniSifre2;


}
