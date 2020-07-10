package br.mp.mppa.example.consultaexample.domain.repository;

import br.mp.mppa.example.consultaexample.domain.model.*;
import br.mp.mppa.example.consultaexample.domain.service.ParametrosConsultaNotaFiscal;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultaNotaFiscalCriteriaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<NotaFiscal> consultar(ParametrosConsultaNotaFiscal pcnf) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> criteriaQuery = cb.createQuery(NotaFiscal.class);
        Root<NotaFiscal> notaFiscal = criteriaQuery.from(NotaFiscal.class);
        Join<NotaFiscal, Empresa> empresa = (Join) notaFiscal.fetch(NotaFiscal_.empresa, JoinType.INNER);
        Join<Empresa, Cidade> cidadeEmpresa = (Join) empresa.fetch(Empresa_.cidade, JoinType.INNER);
        Join<Cidade, Estado> estadoEmpresa = (Join) cidadeEmpresa.fetch(Cidade_.estado, JoinType.INNER);
        Join<NotaFiscal, Cliente> cliente = (Join) notaFiscal.fetch(NotaFiscal_.cliente, JoinType.INNER);
        Join<Cliente, Cidade> cidadeCliente = (Join) cliente.fetch(Cliente_.cidade, JoinType.INNER);
        Join<Cidade, Estado> estadoCliente = (Join) cidadeCliente.fetch(Cidade_.estado, JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (pcnf.getNumero() != null) {
            predicates.add(cb.equal(notaFiscal.get(NotaFiscal_.numero), pcnf.getNumero()));
        }
        if (pcnf.getDataEmissaoMaiorIgual() != null) {
            predicates.add(cb.greaterThanOrEqualTo(notaFiscal.get(NotaFiscal_.dataEmissao), pcnf.getDataEmissaoMaiorIgual()));
        }
        if (pcnf.getDataEmissaoMenorIgual() != null) {
            predicates.add(cb.lessThanOrEqualTo(notaFiscal.get(NotaFiscal_.dataEmissao), pcnf.getDataEmissaoMenorIgual()));
        }
        if (pcnf.getValorMaiorIgual() != null) {
            predicates.add(cb.greaterThanOrEqualTo(notaFiscal.get(NotaFiscal_.valor), pcnf.getValorMaiorIgual()));
        }
        if (pcnf.getValorMenorIgual() != null) {
            predicates.add(cb.lessThanOrEqualTo(notaFiscal.get(NotaFiscal_.valor), pcnf.getValorMenorIgual()));
        }
        if (StringUtils.hasLength(pcnf.getNomeEmpresa())) {
            predicates.add(cb.like(cb.upper(empresa.get(Empresa_.nome)), "%" + pcnf.getNomeEmpresa().toUpperCase().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getCidadeEmpresa())) {
            predicates.add(cb.like(cb.upper(cidadeEmpresa.get(Cidade_.nome)), "%" + pcnf.getCidadeEmpresa().toUpperCase().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getEstadoEmpresa())) {
            predicates.add(cb.like(cb.upper(estadoEmpresa.get(Estado_.nome)), "%" + pcnf.getEstadoEmpresa().toUpperCase().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getNomeCliente())) {
            predicates.add(cb.like(cb.upper(cliente.get(Cliente_.nome)), "%" + pcnf.getNomeCliente().toUpperCase().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getCidadeCliente())) {
            predicates.add(cb.like(cb.upper(cidadeCliente.get(Cidade_.nome)), "%" + pcnf.getCidadeCliente().toUpperCase().replaceAll("[ ]", "%") + "%"));
        }
        if (StringUtils.hasLength(pcnf.getEstadoCliente())) {
            predicates.add(cb.like(cb.upper(estadoCliente.get(Estado_.nome)), "%" + pcnf.getEstadoCliente().toUpperCase().replaceAll("[ ]", "%") + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<NotaFiscal> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}