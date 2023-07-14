package br.com.banco.repository;

import br.com.banco.dto.TransferenciaRequestFilter;
import br.com.banco.model.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query("select t from br.com.banco.model.Transferencia t " +
            " where " +
            "   t.contaId = :#{#filter.contaId} " +
            "   And (:#{#filter.nomeOperadorTransacao} IS NULL OR LOWER(t.nomeOperadorTransacao) LIKE LOWER(concat('%',:#{#filter.nomeOperadorTransacao},'%')))" +
            "   And (:#{#filter.dataInicial} IS NULL OR t.dataTransferencia>= :#{#filter.dataInicial})" +
            "   And (:#{#filter.dataFinal} IS NULL OR t.dataTransferencia<= :#{#filter.dataFinal})")
    Page<Transferencia> getTransferencias(TransferenciaRequestFilter filter, Pageable pageable);

    @Query("select SUM(t.valor) from br.com.banco.model.Transferencia t " +
            " where " +
            "   t.contaId = :#{#filter.contaId} " +
            "   And (:#{#filter.dataInicial} IS NULL OR t.dataTransferencia>= :#{#filter.dataInicial})" +
            "   And (:#{#filter.dataFinal} IS NULL OR t.dataTransferencia<= :#{#filter.dataFinal})")
    Double getValorTotal(TransferenciaRequestFilter filter);


}
