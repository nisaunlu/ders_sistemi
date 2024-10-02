package egitim.nisa.models.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ogretmen_ders")
public class OgretmenDers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name ="ogretmen_id" , nullable = false)
    private Ogretmen ogretmen;

    @ManyToOne
    @JoinColumn(name ="ders_id" , nullable = false)
    private Ders ders;

    @Column(name="status")
    private boolean status;


}
