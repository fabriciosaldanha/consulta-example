package br.mp.mppa.example.consultaexample.domain.repository;

import br.mp.mppa.example.consultaexample.domain.model.NotaFiscal;
import br.mp.mppa.example.consultaexample.domain.service.ParametrosConsultaNotaFiscal;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;

@Repository
public class ConsultaNotaFiscalHqlRepository {

    @PersistenceContext
    private EntityManager em;

    public List<NotaFiscal> consultar(ParametrosConsultaNotaFiscal pcnf) {
        StringBuilder sb = new StringBuilder(
                "SELECT nf " +
                        "FROM NotaFiscal nf " +
                        "INNER JOIN FETCH nf.empresa empresa " +
                        "INNER JOIN FETCH empresa.cidade cidadeEmpresa " +
                        "INNER JOIN FETCH cidadeEmpresa.estado estadoEmpresa " +
                        "INNER JOIN FETCH nf.cliente cliente " +
                        "INNER JOIN FETCH cliente.cidade cidadeCliente " +
                        "INNER JOIN FETCH cidadeCliente.estado estadoCliente " +
                        "where 0 = 0 ");

        HashMap<String, Object> parametrosValores = new HashMap<>();

        if (pcnf.getNumero() != null) {
            sb.append(" AND nf.numero = :numero");
            parametrosValores.put("numero", pcnf.getNumero());
        }
        if (pcnf.getDataEmissaoMaiorIgual() != null) {
            sb.append(" AND nf.dataEmissao >= :dataEmissaoMaiorIgual");
            parametrosValores.put("dataEmissaoMaiorIgual", pcnf.getDataEmissaoMaiorIgual());
        }
        if (pcnf.getDataEmissaoMenorIgual() != null) {
            sb.append(" AND nf.dataEmissao <= :dataEmissaoMenorIgual");
            parametrosValores.put("dataEmissaoMenorIgual", pcnf.getDataEmissaoMenorIgual());
        }
        if (pcnf.getValorMaiorIgual() != null) {
            sb.append(" AND nf.valor >= :valorMaiorIgual");
            parametrosValores.put("valorMaiorIgual", pcnf.getValorMaiorIgual());
        }
        if (pcnf.getValorMenorIgual() != null) {
            sb.append(" AND nf.valor <= :valorMenorIgual");
            parametrosValores.put("valorMenorIgual", pcnf.getValorMenorIgual());
        }
        if (StringUtils.hasLength(pcnf.getNomeEmpresa())) {
            sb.append(" AND UPPER(empresa.nome) LIKE UPPER( :nomeEmpresa )");
            parametrosValores.put("nomeEmpresa", "%" + pcnf.getNomeEmpresa().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getCidadeEmpresa())) {
            sb.append(" AND UPPER(cidadeEmpresa.nome) LIKE UPPER( :cidadeEmpresa )");
            parametrosValores.put("cidadeEmpresa", "%" + pcnf.getCidadeEmpresa().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getEstadoEmpresa())) {
            sb.append(" AND UPPER(estadoEmpresa.nome) LIKE UPPER( :estadoEmpresa )");
            parametrosValores.put("estadoEmpresa", "%" + pcnf.getEstadoEmpresa().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getNomeCliente())) {
            sb.append(" AND UPPER(cliente.nome) LIKE UPPER( :nomeCliente )");
            parametrosValores.put("nomeCliente", "%" + pcnf.getNomeCliente().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getCidadeCliente())) {
            sb.append(" AND UPPER(cidadeCliente.nome) LIKE UPPER( :cidadeCliente )");
            parametrosValores.put("cidadeCliente", "%" + pcnf.getCidadeCliente().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getEstadoCliente())) {
            sb.append(" AND UPPER(estadoCliente.nome) LIKE UPPER( :estadoCliente )");
            parametrosValores.put("estadoCliente", "%" + pcnf.getEstadoCliente().replaceAll("[ ]", "%") + "%");
        }

        TypedQuery<NotaFiscal> query = em.createQuery(sb.toString(), NotaFiscal.class);

        for (String parametro : parametrosValores.keySet()) {
            query.setParameter(parametro, parametrosValores.get(parametro));
        }

        return query.getResultList();
    }
}