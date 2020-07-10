package br.mp.mppa.example.consultaexample.domain.repository;

import br.mp.mppa.example.consultaexample.domain.model.*;
import br.mp.mppa.example.consultaexample.domain.service.ParametrosConsultaNotaFiscal;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultaNotaFiscalQueryDslRepository {

    @PersistenceContext
    private EntityManager em;

    public List<NotaFiscal> consultar(ParametrosConsultaNotaFiscal pcnf) {
        QNotaFiscal notaFiscal = QNotaFiscal.notaFiscal;
        QEmpresa empresa = QEmpresa.empresa;
        QCidade cidadeEmpresa = new QCidade("cidadeEmpresa");
        QEstado estadoEmpresa = new QEstado("estadoEmpresa");
        QCliente cliente = QCliente.cliente;
        QCidade cidadeCliente = new QCidade("cidadeCliente");
        QEstado estadoCliente = new QEstado("estadoCliente");

        List<Predicate> predicates = new ArrayList<>();
        if (pcnf.getNumero() != null) {
            predicates.add(notaFiscal.numero.eq(pcnf.getNumero()));
        }
        if (pcnf.getDataEmissaoMaiorIgual() != null) {
            predicates.add(notaFiscal.dataEmissao.goe(pcnf.getDataEmissaoMaiorIgual()));
        }
        if (pcnf.getDataEmissaoMenorIgual() != null) {
            predicates.add(notaFiscal.dataEmissao.loe(pcnf.getDataEmissaoMenorIgual()));
        }
        if (pcnf.getValorMaiorIgual() != null) {
            predicates.add(notaFiscal.valor.goe(pcnf.getValorMaiorIgual()));
        }
        if (pcnf.getValorMenorIgual() != null) {
            predicates.add(notaFiscal.valor.loe(pcnf.getValorMenorIgual()));
        }
        if (StringUtils.hasLength(pcnf.getNomeEmpresa())) {
            predicates.add(empresa.nome.likeIgnoreCase("%" + pcnf.getNomeEmpresa().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getCidadeEmpresa())) {
            predicates.add(cidadeEmpresa.nome.likeIgnoreCase("%" + pcnf.getCidadeEmpresa().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getEstadoEmpresa())) {
            predicates.add(estadoEmpresa.nome.likeIgnoreCase("%" + pcnf.getEstadoEmpresa().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getNomeCliente())) {
            predicates.add(cliente.nome.likeIgnoreCase("%" + pcnf.getNomeCliente().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getCidadeCliente())) {
            predicates.add(cidadeCliente.nome.likeIgnoreCase("%" + pcnf.getCidadeCliente().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getEstadoCliente())) {
            predicates.add(estadoCliente.nome.likeIgnoreCase("%" + pcnf.getEstadoCliente().replaceAll("[ ]", "%") + "%"));
        }

        JPAQuery<NotaFiscal> query = new JPAQuery<>(em);
        query.from(notaFiscal)
                .innerJoin(notaFiscal.empresa, empresa).fetchJoin()
                .innerJoin(empresa.cidade, cidadeEmpresa).fetchJoin()
                .innerJoin(cidadeEmpresa.estado, estadoEmpresa).fetchJoin()
                .innerJoin(notaFiscal.cliente, cliente).fetchJoin()
                .innerJoin(cliente.cidade, cidadeCliente).fetchJoin()
                .innerJoin(cidadeCliente.estado, estadoCliente).fetchJoin()
                .select(notaFiscal)
                .where(predicates.toArray(new Predicate[0]));

        return query.fetch();
    }
}