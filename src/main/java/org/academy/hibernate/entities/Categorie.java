package org.academy.hibernate.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
@Table(name="jpa05_hb_categorie")
public class Categorie implements Serializable {

	// champs
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@SuppressWarnings("unused")
	@Version
	private int version;

	@Column(length = 30)
	private String nom;

	// relation inverse Categorie (one) -> Article (many) de la relation Article (many) -> Categorie (one)
	// cascade insertion Categorie -> insertion Articles
	// cascade maj Categorie -> maj Articles
	// cascade suppression Categorie -> suppression Articles
	@OneToMany(mappedBy = "categorie", cascade = { CascadeType.ALL })
	private Set<Article> articles = new HashSet<Article>();

	// constructeurs
	public Categorie() {
	}

	// getters et setters
	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public int getVersion() {
		return version;
	}

	@SuppressWarnings("unused")
	private void setVersion(int version) {
		this.version = version;
	}

	// toString
	public String toString() {
		return String.format("Categorie[%d,%d,%s]", id, version, nom);
	}

	// association bidirectionnelle Categorie <--> Article
	public void addArticle(Article article) {
		// l'article est ajout� dans la collection des articles de la cat�gorie
		articles.add(article);
		// l'article change de cat�gorie
		article.setCategorie(this);
	}
}
