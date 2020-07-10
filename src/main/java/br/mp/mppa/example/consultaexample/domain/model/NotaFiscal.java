package br.mp.mppa.example.consultaexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private LocalDate dataEmissao;

    private BigDecimal valor;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Cliente cliente;
}
