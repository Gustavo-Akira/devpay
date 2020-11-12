package br.com.gustavoakira.devpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gustavoakira.devpay.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	Users findByLogin(String login);
	
	@Modifying
	@Query("UPDATE Users u set u.amount = u.amount - ?2 where u.login = ?1")
	void updateDecreaseAmount(String login, Double amount);
	
	@Modifying
	@Query("UPDATE Users u set u.amount = u.amount + ?2 where u.login = ?1")
	void updateIncreaseAmount(String login, Double amount);
}
