package br.mp.mppa.example.consultaexample.domain.repository;

import br.mp.mppa.example.consultaexample.domain.model.*;
import br.mp.mppa.example.consultaexample.domain.service.ParametrosConsultaNotaFiscal;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ConsultaNotaFiscalSqlRepository {

    @PersistenceContext
    private EntityManager em;

    public List<NotaFiscal> consultar(ParametrosConsultaNotaFiscal pcnf) {
        StringBuilder sb = new StringBuilder(
                "SELECT nf.id as a, " +
                        "nf.numero as b, " +
                        "nf.data_emissao as c, " +
                        "nf.valor as d, " +
                        "cli.id as e, " +
                        "cli.nome as f, " +
                        "cid_cli.id as g, " +
                        "cid_cli.nome as h, " +
                        "est_cid_cli.id as i, " +
                        "est_cid_cli.nome as j, " +
                        "emp.id as k, " +
                        "emp.nome as l, " +
                        "cid_emp.id as m, " +
                        "cid_emp.nome as n, " +
                        "est_cid_emp.id as o, " +
                        "est_cid_emp.nome as p " +
                        "FROM nota_fiscal nf " +
                        "INNER JOIN empresa emp on nf.empresa_id = emp.id " +
                        "INNER JOIN cidade cid_emp on emp.cidade_id = cid_emp.id " +
                        "INNER JOIN estado est_cid_emp on cid_emp.estado_id = est_cid_emp.id " +
                        "INNER JOIN cliente cli on nf.cliente_id = cli.id " +
                        "INNER JOIN cidade cid_cli on cli.cidade_id = cid_cli.id " +
                        "INNER JOIN estado est_cid_cli on cid_cli.estado_id = est_cid_cli.id " +
                        "where 0=0");

        HashMap<String, Object> parametrosValores = new HashMap<>();

        if (pcnf.getNumero() != null) {
            sb.append(" AND nf.numero = :numero ");
            parametrosValores.put("numero", pcnf.getNumero());
        }
        if (pcnf.getDataEmissaoMaiorIgual() != null) {
            sb.append(" AND nf.data_emissao >= :dataEmissaoMaiorIgual ");
            parametrosValores.put("dataEmissaoMaiorIgual", pcnf.getDataEmissaoMaiorIgual());
        }
        if (pcnf.getDataEmissaoMenorIgual() != null) {
            sb.append(" AND nf.data_emissao <= :dataEmissaoMenorIgual ");
            parametrosValores.put("dataEmissaoMenorIgual", pcnf.getDataEmissaoMenorIgual());
        }
        if (pcnf.getValorMaiorIgual() != null) {
            sb.append(" AND nf.valor >= :valorMaiorIgual ");
            parametrosValores.put("valorMaiorIgual", pcnf.getValorMaiorIgual());
        }
        if (pcnf.getValorMenorIgual() != null) {
            sb.append(" AND nf.valor <= :valorMenorIgual ");
            parametrosValores.put("valorMenorIgual", pcnf.getValorMenorIgual());
        }
        if (StringUtils.hasLength(pcnf.getNomeEmpresa())) {
            sb.append(" AND UPPER(emp.nome) LIKE UPPER( :nomeEmpresa ) ");
            parametrosValores.put("nomeEmpresa", "%" + pcnf.getNomeEmpresa().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getCidadeEmpresa())) {
            sb.append(" AND UPPER(cid_emp.nome) LIKE UPPER( :cidadeEmpresa ) ");
            parametrosValores.put("cidadeEmpresa", "%" + pcnf.getCidadeEmpresa().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getEstadoEmpresa())) {
            sb.append(" AND UPPER(est_cid_emp.nome) LIKE UPPER( :estadoEmpresa ) ");
            parametrosValores.put("estadoEmpresa", "%" + pcnf.getEstadoEmpresa().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getNomeCliente())) {
            sb.append(" AND UPPER(cli.nome) LIKE UPPER( :nomeCliente ) ");
            parametrosValores.put("nomeCliente", "%" + pcnf.getNomeCliente().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getCidadeCliente())) {
            sb.append(" AND UPPER(cid_cli.nome) LIKE UPPER( :cidadeCliente ) ");
            parametrosValores.put("cidadeCliente", "%" + pcnf.getCidadeCliente().replaceAll("[ ]", "%") + "%");
        }
        if (StringUtils.hasLength(pcnf.getEstadoCliente())) {
            sb.append(" AND UPPER(est_cid_cli.nome) LIKE UPPER( :estadoCliente ) ");
            parametrosValores.put("estadoCliente", "%" + pcnf.getEstadoCliente().replaceAll("[ ]", "%") + "%");
        }

        Query query = em.createNativeQuery(sb.toString());

        if (!parametrosValores.isEmpty()) {
            for (String parametro : parametrosValores.keySet()) {
                query.setParameter(parametro, parametrosValores.get(parametro));
            }
        }

        List<NotaFiscal> resultado = new ArrayList<>();
        for (Object obj : query.getResultList()) {
            Object[] tupla = (Object[]) obj;
            Estado estadoCliente = new Estado(((BigInteger) tupla[8]).longValue(), (String) tupla[9]);
            Cidade cidadeCliente = new Cidade(((BigInteger) tupla[6]).longValue(), (String) tupla[7], estadoCliente);
            Cliente cliente = new Cliente(((BigInteger) tupla[4]).longValue(), (String) tupla[5], cidadeCliente);
            Estado estadoEmpresa = new Estado(((BigInteger) tupla[14]).longValue(), (String) tupla[15]);
            Cidade cidadeEmpresa = new Cidade(((BigInteger) tupla[12]).longValue(), (String) tupla[13], estadoEmpresa);
            Empresa empresa = new Empresa(((BigInteger) tupla[10]).longValue(), (String) tupla[11], cidadeEmpresa);
            NotaFiscal notaFiscal = new NotaFiscal(((BigInteger) tupla[0]).longValue(), (Integer) tupla[1], ((Date) tupla[2]).toLocalDate(), (BigDecimal) tupla[3], empresa, cliente);
            resultado.add(notaFiscal);
        }
        return resultado;
    }
}