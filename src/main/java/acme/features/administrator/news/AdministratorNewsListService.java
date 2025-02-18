
package acme.features.administrator.news;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.news.News;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNewsListService implements AbstractListService<Administrator, News> {

	@Autowired
	AdministratorNewsRepository repository;


	@Override
	public boolean authorise(final Request<News> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<News> request, final News entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
	}

	@Override
	public Collection<News> findMany(final Request<News> request) {
		assert request != null;

		Collection<News> result;

		result = this.repository.findMany();

		return result;
	}

}
