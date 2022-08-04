package fr.m2i.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer _id;

	@Basic
	@Column(name="title")
	private String _title;

	@Basic
	@Column(name="description")
	private String _description;

	@Basic
	@Column(name="img")
	private String _img;

	public News() {

	}
	public News(String title, String description, String img) {
		this._title = title;
		this._description = description;
		this._img = img;
	}

	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
	}
	public String get_description() {
		return _description;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	public String get_img() {
		return _img;
	}
	public void set_img(String _img) {
		this._img = _img;
	}

}
