package egitim.nisa.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name ="kisisel_bilgiler")
public class KisiselBilgiler {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "isim_soyisim")
    private String isimSoyisim;

    @Column(name = "tc")
    private String tc;

    @Column(name = "telefon_no")
    private String telefonNo;

    @Column(name = "dogum_tarihi")
    private Date dogumTarihi;

    @Column(name = "kayit_eklenme_tarihi")
    private Date kayitEklenmeTarihi;

    @Column(name = "status")
    private boolean status;
}
