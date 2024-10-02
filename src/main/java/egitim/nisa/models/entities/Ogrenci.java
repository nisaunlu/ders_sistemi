package egitim.nisa.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ogrenci")
public class Ogrenci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Long id;

    @Column(name="ogrenci_adi")
    private String ogrenciAdi;

    @Column(name="ogrenci_soyadi")
    private String ogrenciSoyadi;

    @Column(name="kayit_eklenme_tarihi")
    private Date kayitEklenmeTarihi;

    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "kisisel_bilgiler_id")
    private KisiselBilgiler kisiselBilgiler;

}
