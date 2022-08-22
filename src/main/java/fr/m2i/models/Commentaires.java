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
@Table(name="commentaires")
public class Commentaires {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer _id;

	@Basic
	@Column(name="content")
	private String _content;

	@ManyToOne @JoinColumn(name="id_news")
	private News id_news;

	@ManyToOne @JoinColumn(name="id_user")
	private User id_user;

	public Commentaires() {

	}

	public Commentaires(String content, News idNews, User idUser) {
		this._content = content;
		this.id_news = idNews;
		this.id_user = idUser;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}

	public News getId_news() {
		return id_news;
	}

	public void setId_news(News id_news) {
		this.id_news = id_news;
	}

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}



}
