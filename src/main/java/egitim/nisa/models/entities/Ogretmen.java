package egitim.nisa.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ogretmen")
public class Ogretmen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Long id;

    @Column(name="ogretmen_adi")
    private String ogretmenAdi;

    @Column(name="ogretmen_soyadi")
    private String ogretmenSoyadi;

    @Column(name="kayit_eklenme_tarihi")
    private Date kayitEklenmeTarihi;

    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name ="kisisel_bilgiler_id" )
    private KisiselBilgiler kisiselBilgiler;
}
