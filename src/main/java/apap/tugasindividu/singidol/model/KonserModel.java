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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "konser")
public class KonserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_konser")
    private Long idKonser;

    @NotNull
    @Column(name = "nama_konser", nullable = false)
    private String namaKonser;

    @NotNull
    @Column(name = "total_pendapatan", nullable = false)
    private Long totalPendapatan;

    @NotNull
    @Column(name = "tempat_konser", nullable = false)
    private String tempatKonser;

    @NotNull
    @Column(name = "waktu", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuKonser;

    @OneToMany(mappedBy = "konser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TiketModel> listTiket;

    @OneToMany(mappedBy = "konser", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PenampilanKonserModel> listIdolKonser;
}
