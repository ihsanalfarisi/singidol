package apap.tugasindividu.singidol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tiket")
public class TiketModel {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tiket", nullable = false)
    private Long idTiket;

    @NotNull
    @Column(name = "nomor_tiket", nullable = false)
    private Long nomorTiket;

    @NotNull
    @Column(name = "tanggal_pembelian", nullable = false)
    private String tanggalPembelian;

    @NotNull
    @Column(name = "asal_negara", nullable = false)
    private String asalNegara;

    @NotNull
    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDateTime tanggalLahir;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_konser", referencedColumnName = "id_konser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KonserModel konser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "id_tipe", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipeModel tipe;
}
