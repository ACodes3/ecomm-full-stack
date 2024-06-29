package si.unm.fis.prspr.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import si.unm.fis.prspr.ecom.entity.Items;

public interface ItemRepository extends JpaRepository<Items, Integer> {

}
