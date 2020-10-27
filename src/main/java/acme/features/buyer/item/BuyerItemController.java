
package acme.features.buyer.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.items.Item;
import acme.entities.roles.Buyer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller

@RequestMapping("/buyer/item/")
public class BuyerItemController extends AbstractController<Buyer, Item> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	private BuyerItemShowService		showService;

	@Autowired
	private BuyerItemListMineService	listMineService;


	// Constructors --------------------------------------------------------------------

	@PostConstruct
	private void initialisate() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}

}
