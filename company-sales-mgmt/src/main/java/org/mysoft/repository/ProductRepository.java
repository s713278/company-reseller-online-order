package org.mysoft.repository;

import org.mysoft.vo.ProductVO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductVO, Long>{

}
