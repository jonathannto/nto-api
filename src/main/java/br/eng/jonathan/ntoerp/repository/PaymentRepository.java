package br.eng.jonathan.ntoerp.repository;

import br.eng.jonathan.ntoerp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}