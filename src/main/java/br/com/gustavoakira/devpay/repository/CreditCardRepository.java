package br.com.gustavoakira.devpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gustavoakira.devpay.model.CreditCard;
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

}
