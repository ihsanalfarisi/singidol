package apap.tugasindividu.singidol.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "penampilan_konser")
public class PenampilanKonserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penampilan_konser", nullable = false)
    private Long idPenampilanKonser;

    @ManyToOne
    @JoinColumn(name = "id_konser", nullable = false)
    private KonserModel konser;

    @ManyToOne
    @JoinColumn(name = "id_idol", nullable = false)
    private IdolModel idol;

    @NotNull
    @Column(name = "jam_mulai_tampil", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime jamMulaiTampil;
}
