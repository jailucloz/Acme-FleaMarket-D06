
package acme.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.banners.Banner;

@ControllerAdvice
public class BannerControllerAdvice {

	@Autowired
	private BannerRepository repository;


	@ModelAttribute("banner")
	public Banner getBanner() {

		int i = (int) (Math.random() * this.repository.counter());
		PageRequest pageRequest = PageRequest.of(i, 1);

		List<Banner> list = this.repository.findManyBanners(pageRequest);
		return list.isEmpty() ? null : list.get(0);

	}

}
