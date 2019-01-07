package org.mysoft.repository;

import org.mysoft.vo.OrderVO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderVO, Long>{
	//void orderCountBySellerId(String sellerId);
	//void findOrderForReStock();
}
