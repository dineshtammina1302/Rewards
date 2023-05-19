package com.customer.trasactions.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.trasactions.entity.TransactionEntity;
import com.customer.trasactions.service.TransactionDTO;
import com.customer.trasactions.service.TransactionService;


@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	// Gets all rewards of all customers for given start and end date
	//localhost:8080/getRewards?startDate=2022-08-01&endDate=2023-03-29
	@GetMapping("/getRewards")
	public List<TransactionDTO> getRewards(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate) {
		
		LocalDate dateStart = LocalDate.parse(startDate);
		
		LocalDate dateEnd = LocalDate.parse(endDate);
		
		return transactionService.getRewards(dateStart, dateEnd);
	}

	//localhost:8080/getRewards/1056?startDate=2022-08-01&endDate=2023-03-29
	// Gets rewards for the given customer id and given start and end date
	@GetMapping("/getRewards/{customerId}")
	public List<TransactionDTO> getRewards(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @PathVariable(name = "customerId") int customerId) {
		
		LocalDate dateStart = LocalDate.parse(startDate);
		
		LocalDate dateEnd = LocalDate.parse(endDate);
		
		return transactionService.getRewards(dateStart, dateEnd, customerId);
	}

	//localhost:8080/transactions/
	// Gets all transactions
	@GetMapping("/transactions")
	public List<TransactionEntity> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	//localhost:8080/transactions/1043
	// Gets all transactions for the given customer id
	@GetMapping("/transactions/{customerId}")
	public List<TransactionEntity> getAllTransactions(@PathVariable(name = "customerId") int customerId) {
		return transactionService.getAllTransactions(customerId);
	}
}
