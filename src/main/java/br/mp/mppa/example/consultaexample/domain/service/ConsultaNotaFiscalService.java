package br.mp.mppa.example.consultaexample.domain.service;

import br.mp.mppa.example.consultaexample.domain.model.NotaFiscal;
import br.mp.mppa.example.consultaexample.domain.repository.*;
import br.mp.mppa.example.consultaexample.infrastructure.repository.NotaFiscalSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static br.mp.mppa.example.consultaexample.domain.service.TipoConsultaNotaFiscal.*;

@Service
@AllArgsConstructor
public class ConsultaNotaFiscalService {

    private final ConsultaNotaFiscalSqlRepository consultaNotaFiscalSqlRepository;
    private final ConsultaNotaFiscalHqlRepository consultaNotaFiscalHqlRepository;
    private final ConsultaNotaFiscalCriteriaRepository consultaNotaFiscalCriteriaRepository;
    private final ConsultaNotaFiscalQueryDslRepository consultaNotaFiscalQueryDslRepository;
    private final ConsultaNotaFiscalSpecificationRepository consultaNotaFiscalSpecificationRepository;


    public List<NotaFiscal> consultar(ParametrosConsultaNotaFiscal pcnf, TipoConsultaNotaFiscal tipoConsultaNotaFiscal) {

        if (SQL.equals(tipoConsultaNotaFiscal)) {
            return consultaNotaFiscalSqlRepository.consultar(pcnf);
        } else if (HQL.equals(tipoConsultaNotaFiscal)) {
            return consultaNotaFiscalHqlRepository.consultar(pcnf);
        } else if (CRITERIA.equals(tipoConsultaNotaFiscal)) {
            return consultaNotaFiscalCriteriaRepository.consultar(pcnf);
        } else if (SPECIFICATION.equals(tipoConsultaNotaFiscal)) {
            return consultaNotaFiscalSpecification(pcnf);
        } else if (QUERYDSL.equals(tipoConsultaNotaFiscal)) {
            return consultaNotaFiscalQueryDslRepository.consultar(pcnf);
        }
        return null;
    }

    private List<NotaFiscal> consultaNotaFiscalSpecification(ParametrosConsultaNotaFiscal pcnf) {
        Specification<NotaFiscal> where = Specification
                .where(pcnf.getNumero() != null ? NotaFiscalSpecs.numeroIgual(pcnf.getNumero()) : null)
                .and(pcnf.getDataEmissaoMaiorIgual() != null ? NotaFiscalSpecs.dataEmissaoMaiorIgual(pcnf.getDataEmissaoMaiorIgual()) : null)
                .and(pcnf.getDataEmissaoMenorIgual() != null ? NotaFiscalSpecs.dataEmissaoMenorIgual(pcnf.getDataEmissaoMenorIgual()) : null)
                .and(pcnf.getValorMaiorIgual() != null ? NotaFiscalSpecs.valorMaiorIgual(pcnf.getValorMaiorIgual()) : null)
                .and(pcnf.getValorMenorIgual() != null ? NotaFiscalSpecs.valorMenorIgual(pcnf.getValorMenorIgual()) : null)
                .and(StringUtils.hasLength(pcnf.getNomeEmpresa()) ? NotaFiscalSpecs.nomeEmpresa(pcnf.getNomeEmpresa()) : null)
                .and(StringUtils.hasLength(pcnf.getCidadeEmpresa()) ? NotaFiscalSpecs.nomeCidadeEmpresa(pcnf.getCidadeEmpresa()) : null)
                .and(StringUtils.hasLength(pcnf.getEstadoEmpresa()) ? NotaFiscalSpecs.nomeEstadoEmpresa(pcnf.getEstadoEmpresa()) : null)
                .and(StringUtils.hasLength(pcnf.getNomeCliente()) ? NotaFiscalSpecs.nomeCliente(pcnf.getNomeCliente()) : null)
                .and(StringUtils.hasLength(pcnf.getCidadeCliente()) ? NotaFiscalSpecs.nomeCidadeCliente(pcnf.getCidadeCliente()) : null)
                .and(StringUtils.hasLength(pcnf.getEstadoCliente()) ? NotaFiscalSpecs.nomeEstadoCliente(pcnf.getEstadoCliente()) : null);

        return consultaNotaFiscalSpecificationRepository.findAll(where);
    }
}