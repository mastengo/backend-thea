package edu.caece.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.caece.app.domain.UserIncome;

public interface IUserIncomeRepository extends JpaRepository<UserIncome, Long> {

}
