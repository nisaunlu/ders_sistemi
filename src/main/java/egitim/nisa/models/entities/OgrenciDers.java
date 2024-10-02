package egitim.nisa.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ogrenci_ders")
public class OgrenciDers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name ="ogrenci_id")
    private Ogrenci ogrenci;

    @ManyToOne
    @JoinColumn(name ="ogretmen_ders_id" , nullable = false)
    private OgretmenDers ogretmenDers;

    @Column(name="status")
    private boolean status;

}
