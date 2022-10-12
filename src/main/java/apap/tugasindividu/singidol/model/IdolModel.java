package apap.tugasindividu.singidol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "idol")

public class IdolModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idol",nullable = false)
    private Long idIdol;

    @NotNull
    @Column(name = "nama_idol", nullable = false)
    private String namaIdol;

    @NotNull
    @Column(name = "jumlah_anggota",nullable = false)
    private Integer jumlahAnggota;

    @NotNull
    @Column(name = "asal_negara",nullable = false)
    private String asalNegara;

    @NotNull
    @Column(name = "tanggal_debut",nullable = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalDebut;

    @OneToMany(mappedBy = "idol", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PenampilanKonserModel> listTampil;

}
