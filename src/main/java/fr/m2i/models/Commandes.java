package fr.m2i.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="commandes")
public class Commandes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer _id;

	@Basic
	@Column(name="title")
	private String _tilte;

	@ManyToOne @JoinColumn(name="id_client")
	private Clients id_client;


	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String get_tilte() {
		return _tilte;
	}
	public void set_tilte(String _tilte) {
		this._tilte = _tilte;
	}
	public Clients getId_client() {
		return id_client;
	}
	public void setId_client(Clients id_client) {
		this.id_client = id_client;
	}
}
