package egitim.nisa.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name ="ders")
public class Ders {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name ="id")
    private Long id;

    @Column(name = "ders_adi")
    private String dersAdi;

    @Column(name = "ders_kredisi")
    private Long dersKredisi;

    @Column(name = "kayit_eklenme_tarihi")
    private Date kayitEklenmeTarihi;

    @Column(name = "status")
    private boolean status;


}
