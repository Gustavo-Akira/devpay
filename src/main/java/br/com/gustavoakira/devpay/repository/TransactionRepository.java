package br.com.gustavoakira.devpay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gustavoakira.devpay.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	@Query("FROM Transaction t where t.userTo = ?1 and t.userFrom =?2")
	Page<Transaction> findByFrom_LoginOrTo_Login(String loginFrom, String loginTo, Pageable paginacao);
}
