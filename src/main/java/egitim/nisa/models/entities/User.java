package egitim.nisa.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CollectionId;

import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "kullanici_adi")
    private String kullaniciAdi;

    @Column(name = "sifre")
    private String sifre;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "activation_token")
    private String activationToken;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "insert_date")
    private Date insertDate;

    @Column(name = "activation_date")
    private Date activationDate;


}
