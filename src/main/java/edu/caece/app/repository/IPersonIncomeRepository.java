package edu.caece.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.caece.app.domain.PersonIncome;

public interface IPersonIncomeRepository extends JpaRepository<PersonIncome, Long>{

}
