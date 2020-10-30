
package acme.components;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select b from Banner b where exists(select cc from CreditCard cc where cc.banner.id = b.id and cc.expirationDate > CURRENT_TIMESTAMP)")
	List<Banner> findManyBanners(PageRequest pageRequest);

	@Query("select count(b) from Banner b where exists(select cc from CreditCard cc where cc.banner.id = b.id and cc.expirationDate > CURRENT_TIMESTAMP)")
	int counter();

}
