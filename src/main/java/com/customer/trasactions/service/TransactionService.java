package com.customer.trasactions.service;

import com.customer.trasactions.entity.TransactionEntity;
import com.customer.trasactions.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void save(TransactionEntity transaction) {
    	updateRewards(transaction);
        transactionRepository.save(transaction);
    }

    private void updateRewards(TransactionEntity transaction) {
        double transactionAmount = transaction.getAmount();
        double rewardPoints = 0;

        /* A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point 
         * for every dollar spent between $50 and $100 in each transaction.
         */
        if (transactionAmount > 100) {
            rewardPoints = (transactionAmount - 100) * 2 + 50;
        }
        else if (transactionAmount <= 100 && transactionAmount >= 50) {
            rewardPoints = (transactionAmount - 50);
        }

        transaction.setRewards((int) rewardPoints);
	}

	public List<TransactionDTO> getRewards(LocalDate startDate, LocalDate endDate, int customerId) {
        List<Integer[]> theList = transactionRepository.getTransactions(startDate, endDate, customerId);

        return convertToModel(theList);
    }

    /*
     * convert transactions to Transactions DTO
     */
    private List<TransactionDTO> convertToModel(List<Integer[]> theList) {
        return theList.stream().map(data -> {
            TransactionDTO trasactionDetails = new TransactionDTO();
            trasactionDetails.setCustomerId((int) data[0]);
            trasactionDetails.setRewards((int) data[1]);

            return trasactionDetails;
        }).collect(Collectors.toList());
    }
    
	public List<TransactionEntity> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public List<TransactionEntity> getAllTransactions(int customerId) {
		return transactionRepository.findByCustomerId(customerId);
	}

	public List<TransactionDTO> getRewards(LocalDate dateStart, LocalDate dateEnd) {
        List<Integer[]> theList = transactionRepository.getTransactions(dateStart, dateEnd);

        return convertToModel(theList);
	}

	public void saveAll(List<TransactionEntity> transactionList) {
		for (TransactionEntity transactionEntity : transactionList) {
			updateRewards(transactionEntity);
		}
		
		transactionRepository.saveAll(transactionList);
	}
}
