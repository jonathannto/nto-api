package br.eng.jonathan.ntoerp.repository;

import br.eng.jonathan.ntoerp.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>, JpaSpecificationExecutor<PaymentType> {
}
