
package acme.features.buyer.request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.requests.RequestItem;
import acme.entities.roles.Buyer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/buyer/request-item/")
public class BuyerRequestController extends AbstractController<Buyer, RequestItem> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BuyerRequestListMineService	listMineService;

	@Autowired
	private BuyerRequestShowService		showService;

	@Autowired
	private BuyerRequestCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
