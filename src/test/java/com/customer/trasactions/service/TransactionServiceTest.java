package com.customer.trasactions.service;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.trasactions.entity.TransactionEntity;
import com.customer.trasactions.repository.TransactionRepository;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
	
	@InjectMocks 
	public TransactionService trasactionService;
	
	@Mock
	public TransactionRepository transactionRepository;
	
	
	@Test
	public void testSave() {
		
		TransactionEntity transaction = new TransactionEntity();
		transaction.setCustomerId(1);
		transaction.setAmount(450);
		
		 String date = "2023-05-03";
		 
		transaction.setDate(LocalDate.parse(date));
		

		trasactionService.save(transaction);
		verify(transactionRepository).save(any(TransactionEntity.class));
		
		
		transaction.setAmount(95);
		trasactionService.save(transaction);
		verify(transactionRepository, times(2)).save(any(TransactionEntity.class));
	}
	
	@Test
	public void testSaveAll() {
		
		TransactionEntity transaction1 = new TransactionEntity();
		transaction1.setCustomerId(1);
		transaction1.setAmount(450);
		 String date = "2023-05-02";
		 
		transaction1.setDate(LocalDate.parse(date));
		
		TransactionEntity transaction2 = new TransactionEntity();
		transaction2.setCustomerId(2);
		transaction2.setAmount(88);
		 date = "2023-05-03";
		 
		transaction2.setDate(LocalDate.parse(date));
		

		List<TransactionEntity> theList = List.of(transaction1, transaction2);
		trasactionService.saveAll(theList);
		verify(transactionRepository).saveAll(any(List.class));
	}
	
	@Test
	public void testgetAllTransactions() {
		
		Integer data[] = new Integer[] {200,300};
		List<Integer[]> theList = new ArrayList<>();
		theList.add(data);
		
		LocalDate startDate = LocalDate.parse("2022-09-01");
		LocalDate endDate =  LocalDate.parse("2023-03-01");
		when(transactionRepository.getTransactions(startDate, endDate)).thenReturn(theList);
		
		List<TransactionDTO> result = trasactionService.getRewards(startDate, endDate);
		
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void testgetAllTransactionsOfCustomer() {
		
		Integer data[] = new Integer[] {200,300};
		List<Integer[]> theList = new ArrayList<>();
		theList.add(data);
		
		LocalDate startDate = LocalDate.parse("2022-09-01");
		LocalDate endDate =  LocalDate.parse("2023-03-01");
		when(transactionRepository.getTransactions(startDate, endDate, 200)).thenReturn(theList);
		
		List<TransactionDTO> result = trasactionService.getRewards(startDate, endDate,200);
		
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void getAllTransactions() {
		
		TransactionEntity transaction = getTransactionObject();

		when(transactionRepository.findAll()).thenReturn(List.of(transaction));
		
		
		List<TransactionEntity> result = trasactionService.getAllTransactions();
		assertTrue(result.size() > 0);

	}
	
	
	@Test
	public void getAllTransactionsByCustomerId() {
		
		TransactionEntity transaction = getTransactionObject();
		 
		when(transactionRepository.findByCustomerId(1)).thenReturn(List.of(transaction));
		
		
		List<TransactionEntity> result = trasactionService.getAllTransactions(1);
		assertTrue(result.size() > 0);

	}

	private TransactionEntity getTransactionObject() {
		 
		TransactionEntity transactionEntity =	new TransactionEntity();
		transactionEntity.setCustomerId(1);
		transactionEntity.setAmount(450);
		String date = "2023-05-03";
		transactionEntity.setDate(LocalDate.parse(date));
		return transactionEntity;
	}
}
