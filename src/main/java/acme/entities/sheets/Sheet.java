
package acme.entities.sheets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;

import acme.entities.items.Item;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sheet extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	/*
	 * The specification sheet consists of one or more sections,
	 * each of which has an indexer (a positive integer), a title,
	 * a description, and an optional photo. Note that the indexers
	 * need not be consecutive, but they must be unique within each
	 * specification sheet. Note too, that an item requires at least a
	 * section in its specification sheet.
	 */

	@NotBlank
	@Positive
	@Column(unique = true)
	private Integer				indexer;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@URL
	private String				photo;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Item				item;

}
