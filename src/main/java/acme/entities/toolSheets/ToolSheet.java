
package acme.entities.toolSheets;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolSheet extends DomainEntity {

	private static final long	serialVersionUID	= 1l;
	/*
	 * The system must store tool sheets, each of which consists of
	 * a title, a description, the name of its provider, and their home page.
	 * Some tool sheets may have a number of stars in range zero (very bad)
	 * up to five (excellent).
	 */

	@NotBlank
	private String				title;

	@NotBlank
	private String				providerName;

	@NotBlank
	@Size(min = 1, max = 256)
	private String				description;

	@NotBlank
	@URL
	private String				homePage;

	@NotNull
	@Min(0)
	@Max(5)
	private Integer				stars;
}
