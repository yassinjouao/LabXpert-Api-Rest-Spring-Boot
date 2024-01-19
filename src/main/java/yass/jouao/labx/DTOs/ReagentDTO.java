package yass.jouao.labx.DTOs;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class ReagentDTO {
	public interface saveReagent {
	}

	public interface viewReagent {
	}

	public interface updateReagent {
	}
	@JsonView({ saveReagent.class, updateReagent.class })
	private long id;
	@JsonView({ saveReagent.class, viewReagent.class, updateReagent.class })
	private String name;
	@JsonView({ saveReagent.class, viewReagent.class, updateReagent.class })
	private String Description;
	@JsonView({ saveReagent.class, viewReagent.class, updateReagent.class })
	private Long stock;
	@JsonView({ saveReagent.class, viewReagent.class, updateReagent.class })
	private double price;
	@JsonView({ saveReagent.class, viewReagent.class, updateReagent.class })
	private LocalDate expirationDate;
	@JsonView({ saveReagent.class})
	private Long idFournisseur;
}