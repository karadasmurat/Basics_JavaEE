package web.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import ejb.CartService;
import entity.Cart;
import entity.Item;

@Named
public class CartBean {

	private static final Logger LOGGER = Logger.getLogger(CartBean.class.getName());

	@EJB
	CartService cartService;

	private Cart cart1;
	private Item item1;
	private Item item2;
	private int pageID;

	public CartBean() {
		LOGGER.log(Level.INFO, "Initializing CartBean");
	}

	@PostConstruct
	public void initialize() {
		cart1 = new Cart("CART20");
		item1 = new Item("ITEM20", cart1);
		item2 = new Item("ITEM21", cart1);
		cart1.addItem(item1);
		cart1.addItem(item2);
		pageID = 1;
	}

	public String persist() {
		LOGGER.log(Level.INFO, "CartBean.persist()");
		cartService.persistCart(cart1);
		return "response";
	}

	public Cart getCart1() {
		return cart1;
	}

	public void setCart1(Cart cart1) {
		this.cart1 = cart1;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public Item getItem1() {
		return item1;
	}

	public void setItem1(Item item1) {
		this.item1 = item1;
	}



}
