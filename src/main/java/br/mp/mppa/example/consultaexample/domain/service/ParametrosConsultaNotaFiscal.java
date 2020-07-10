package br.mp.mppa.example.consultaexample.domain.service;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ParametrosConsultaNotaFiscal {
    private Integer numero;
    private LocalDate dataEmissaoMaiorIgual;
    private LocalDate dataEmissaoMenorIgual;
    private BigDecimal valorMaiorIgual;
    private BigDecimal valorMenorIgual;
    private String nomeEmpresa;
    private String cidadeEmpresa;
    private String estadoEmpresa;
    private String nomeCliente;
    private String cidadeCliente;
    private String estadoCliente;
}
