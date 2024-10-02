package egitim.nisa.models.dto;

import lombok.Data;

import java.util.Date;


@Data
public class KisiselBilgilerDto {
    private Long id;
    private String isimSoyisim;
    private String tc;
    private String telefonNo;
    private Date dogumTarihi;

}
