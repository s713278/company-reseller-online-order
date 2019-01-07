package org.mysoft.repository;

import org.mysoft.vo.SellerVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CrudRepository<SellerVO, Long> {

}
