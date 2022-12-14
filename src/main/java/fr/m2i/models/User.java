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

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer _id;

	@Basic
	@Column(name="pseudo")
	private String _pseudo;

	@Basic
	@Column(name="password")
	private String _password;

	@OneToMany(targetEntity = Commentaires.class, mappedBy="id_user")
	private List<Commentaires> _commentaire = new ArrayList<>();



	//	public static Boolean isConnected = false;

	public User() {

	}

	public User(String pseudo, String password) {
		this._pseudo = pseudo;
		this._password = password;
	}

	public String get_pseudo() {
		return _pseudo;
	}
	public void set_pseudo(String _pseudo) {
		this._pseudo = _pseudo;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public List<Commentaires> get_commentaire() {
		return _commentaire;
	}

	public void set_commentaire(List<Commentaires> _commentaire) {
		this._commentaire = _commentaire;
	}


}
