package br.mp.mppa.example.consultaexample.domain.repository;

import br.mp.mppa.example.consultaexample.domain.model.NotaFiscal;
import com.sun.istack.Interned;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaNotaFiscalSpecificationRepository extends JpaRepository<NotaFiscal, Long>, JpaSpecificationExecutor<NotaFiscal> {
}
