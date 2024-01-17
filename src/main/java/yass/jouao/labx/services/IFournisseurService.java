package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Fournisseur;

public interface IFournisseurService {
	List<Fournisseur> getAllFournisseursService();

	Optional<Fournisseur> getFournisseurByIdService(Long id);

	Fournisseur addFournisseurService(Fournisseur f);

	Fournisseur updateFournisseurService(Fournisseur f);

	void deleteFournisseurService(Long id);
}
