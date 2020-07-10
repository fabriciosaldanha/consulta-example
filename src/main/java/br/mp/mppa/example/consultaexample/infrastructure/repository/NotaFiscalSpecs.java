package br.mp.mppa.example.consultaexample.infrastructure.repository;

import br.mp.mppa.example.consultaexample.domain.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaFiscalSpecs {
    public static Specification<NotaFiscal> numeroIgual(Integer numero) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(NotaFiscal_.numero), numero);
    }

    public static Specification<NotaFiscal> dataEmissaoMaiorIgual(LocalDate dataEmissao) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(NotaFiscal_.dataEmissao), dataEmissao);
    }

    public static Specification<NotaFiscal> dataEmissaoMenorIgual(LocalDate dataEmissao) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.dataEmissao), dataEmissao);
    }

    public static Specification<NotaFiscal> valorMaiorIgual(BigDecimal valor) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(NotaFiscal_.valor), valor);
    }

    public static Specification<NotaFiscal> valorMenorIgual(BigDecimal valor) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.valor), valor);
    }

    public static Specification<NotaFiscal> nomeEmpresa(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.empresa).get(Empresa_.nome), nome);
    }

    public static Specification<NotaFiscal> nomeCidadeEmpresa(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.empresa).get(Empresa_.cidade).get(Cidade_.nome), nome);
    }

    public static Specification<NotaFiscal> nomeEstadoEmpresa(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.empresa).get(Empresa_.cidade).get(Cidade_.estado).get(Estado_.nome), nome);
    }

    public static Specification<NotaFiscal> nomeCliente(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.cliente).get(Cliente_.nome), nome);
    }

    public static Specification<NotaFiscal> nomeCidadeCliente(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.cliente).get(Cliente_.cidade).get(Cidade_.nome), nome);
    }

    public static Specification<NotaFiscal> nomeEstadoCliente(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(NotaFiscal_.cliente).get(Cliente_.cidade).get(Cidade_.estado).get(Estado_.nome), nome);
    }
}