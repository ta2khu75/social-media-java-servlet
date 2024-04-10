package models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import util.Jpa;

/**
 * The persistent class for the Videos database table.
 *
 */
@NamedQueries({
	@NamedQuery(name = "Video.findTitle", query = "SELECT DISTINCT o.video FROM Favorite o where o.video.title LIKE :id"),
	@NamedQuery(name = "Video.findUser", query = "SELECT o.video FROM Favorite o where o.user.email=:email"),
	@NamedQuery(name = "Video.findNoFavorite", query = "SELECT o FROM Video o WHERE o.favorites IS NOT EMPTY"),
	@NamedQuery(name = "Video.findFavorite", query = "SELECT o FROM Video o WHERE o.favorites IS EMPTY"),
	@NamedQuery(name = "Video.findDate", query = "SELECT o.video FROM Favorite o where o.likeDate between :fromDate AND :toDate"),
	@NamedQuery(name = "Video.findMonth", query = "SELECT o.video FROM Favorite o where month(o.likeDate) IN (:month)")
})
@Entity
@Table(name = "Videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	private String title;

	private String video;

	private int views;

	//bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "video")
	private List<Favorite> favorites;

	//bi-directional many-to-one association to Share
	@OneToMany(mappedBy = "video")
	private List<Share> shares;

	public Video() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setVideo(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setVideo(null);

		return favorite;
	}

	public List<Share> getShares() {
		return this.shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public Share addShare(Share share) {
		getShares().add(share);
		share.setVideo(this);

		return share;
	}

	public Share removeShare(Share share) {
		getShares().remove(share);
		share.setVideo(null);

		return share;
	}

	public static int getIndexById(List<Video> array, int id) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getId() == id) { // Assuming getIdObject() returns the ID of the object
				return i; // Return the index if the ID matches
			}
		}
		return -1; // Return -1 if the ID is not found in the array
	}

	public static List<Video> getPageVideo(int page, int pageSize) {
		EntityManager entityManager = Jpa.getEntityManager();
		String hql = "FROM Video";
		Query query = entityManager.createQuery(hql);
		query.setFirstResult((page - 1) * pageSize); // Vị trí bắt đầu của kết quả
		query.setMaxResults(pageSize); // Số lượng kết quả cần lấy
		List<Video> results = query.getResultList();
		return results;
	}
}
