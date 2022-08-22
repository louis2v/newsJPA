package fr.m2i.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.m2i.servlets.Commande;

@Entity
@Table(name="clients")
public class Clients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer _id;

	@Basic
	@Column(name="nom")
	private String _nom;

	@Basic
	@Column(name="prenom")
	private String _prenom;

	@OneToMany(targetEntity = Commandes.class, mappedBy="id_client")
	private List<Commande> commande = new ArrayList<>();



	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String get_nom() {
		return _nom;
	}
	public void set_nom(String _nom) {
		this._nom = _nom;
	}
	public String get_prenom() {
		return _prenom;
	}
	public void set_prenom(String _prenom) {
		this._prenom = _prenom;
	}

	public List<Commande> get_commandes(){
		return commande;
	}

	public void set_commandes(List<Commande> commandes) {
		this.commande = commandes;
	}
}
