package br.mp.mppa.example.consultaexample.api.controller;

import br.mp.mppa.example.consultaexample.domain.model.NotaFiscal;
import br.mp.mppa.example.consultaexample.domain.service.ConsultaNotaFiscalService;
import br.mp.mppa.example.consultaexample.domain.service.ParametrosConsultaNotaFiscal;
import br.mp.mppa.example.consultaexample.domain.service.TipoConsultaNotaFiscal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping
@RestController
@AllArgsConstructor
public class ConsultaParametrizadaController {

    private ConsultaNotaFiscalService consultaNotaFiscalService;

    @GetMapping("/consultaNotaFiscal")
    public List<NotaFiscal> pesquisaHql(
            @RequestParam TipoConsultaNotaFiscal tipoConsulta,
            @RequestParam(required = false) Integer numero,
            @RequestParam(required = false) LocalDate dataEmissaoMaiorIgual,
            @RequestParam(required = false) LocalDate dataEmissaoMenorIgual,
            @RequestParam(required = false) BigDecimal valorMaiorIgual,
            @RequestParam(required = false) BigDecimal valorMenorIgual,
            @RequestParam(required = false) String nomeEmpresa,
            @RequestParam(required = false) String cidadeEmpresa,
            @RequestParam(required = false) String estadoEmpresa,
            @RequestParam(required = false) String nomeCliente,
            @RequestParam(required = false) String cidadeCliente,
            @RequestParam(required = false) String estadoCliente
    ){
        ParametrosConsultaNotaFiscal pcnf = ParametrosConsultaNotaFiscal
                .builder()
                .numero(numero)
                .dataEmissaoMaiorIgual(dataEmissaoMaiorIgual)
                .dataEmissaoMenorIgual(dataEmissaoMenorIgual)
                .valorMaiorIgual(valorMaiorIgual)
                .valorMenorIgual(valorMenorIgual)
                .nomeEmpresa(getStringOrNull(nomeEmpresa))
                .cidadeEmpresa(getStringOrNull(cidadeEmpresa))
                .estadoEmpresa(getStringOrNull(estadoEmpresa))
                .nomeCliente(getStringOrNull(nomeCliente))
                .cidadeCliente(getStringOrNull(cidadeCliente))
                .estadoCliente(getStringOrNull(estadoCliente))
                .build();
        return consultaNotaFiscalService.consultar(pcnf, tipoConsulta);
    }

    private String getStringOrNull(String value) {
        if (value == null){
            return null;
        }

        if (value.length() == 0){
            return null;
        }

        return value;

    }

//    @GetMapping("/criteria")
//    @GetMapping("/querydsl")
//    @GetMapping("/specification")
}
