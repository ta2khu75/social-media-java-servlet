package models;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import util.Jpa;

/**
 * The persistent class for the Favorites database table.
 *
 */
@NamedQueries({
	@NamedQuery(name = "Favorite.findVideo", query = "SELECT o FROM Favorite o WHERE o.video.id=:id"),
	@NamedQuery(name = "Favorite.findUserVideo", query = "SELECT o FROM Favorite o WHERE o.video.id =:videoId AND o.user.email LIKE :userEmail"),
	@NamedQuery(name = "Object[].countFavoriteVideo", query = "SELECT f.video.title, COUNT(f), MAX(f.likeDate), MIN(f.likeDate) FROM Favorite f GROUP BY f.video.title"),
//	@NamedQuery(name = "Object[].countFavoriteVideo", query = "SELECT o.video.title, Count(o.id), Max(o.likeDate), Min(o.likeDate) FROM Favorite  GROUP BY o.video.title"),
	@NamedQuery(name = "Favorite.findUserVideoAll", query = "SELECT f.video FROM Favorite f WHERE f.user.email LIKE :email")})
@Entity
@Table(name = "Favorites")
public class Favorite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date likeDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "videoId")
	private Video video;

	public Favorite() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getLikeDate() {
		return this.likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public static Favorite getFavoriteUserVideo(int videoId, String userEmail) {
		EntityManager entityManager = Jpa.getEntityManager();
		TypedQuery<Favorite> query = entityManager.createNamedQuery("Favorite.findUserVideo", Favorite.class);
		query.setParameter("videoId", videoId);
		query.setParameter("userEmail", userEmail);

		Favorite favorite = null; // Khởi tạo biến favorite với giá trị null để xử lý trường hợp không tìm thấy kết quả

		try {
			favorite = query.getSingleResult(); // Thử lấy kết quả
		} catch (NoResultException e) {
			System.err.println("Không tìm thấy Favorite cho videoId " + videoId + " và userEmail " + userEmail);
		} finally {
			entityManager.close();
			return favorite;
		}

	}

	public static List<Video> getFavoriteUserVideoAll(String userEmail) {
		EntityManager entityManager = Jpa.getEntityManager();
		TypedQuery<Video> query = entityManager.createNamedQuery("Favorite.findUserVideoAll", Video.class);
		query.setParameter("email", userEmail);

		List<Video> videos = new ArrayList<>();
		// Khởi tạo biến favorite với giá trị null để xử lý trường hợp không tìm thấy kết quả

		try {
			videos = query.getResultList(); // Thử lấy kết quả
		} catch (NoResultException e) {
			System.err.println("Không tìm thấy Favorite cho videoId  và userEmail " + userEmail);
		} finally {
			entityManager.close();
			return videos;
		}
	}

	public static List<Object[]> getCountFavoriteVideo() {
		EntityManager entityManager = Jpa.getEntityManager();
		TypedQuery<Object[]> query = entityManager.createNamedQuery("Object[].countFavoriteVideo", Object[].class);
		List<Object[]> videos = new ArrayList<>();
		// Khởi tạo biến favorite với giá trị null để xử lý trường hợp không tìm thấy kết quả
		try {
			videos = query.getResultList(); // Thử lấy kết quả
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			return videos;
		}
	}

	public static List<Favorite> getFavoriteVideo(Integer id) {
		EntityManager session = Jpa.getEntityManager();
		TypedQuery<Favorite> query = session.createNamedQuery("Favorite.findVideo", Favorite.class);
		query.setParameter("id", id);
		List<Favorite> list = query.getResultList();
		session.close();
		return list;
	}
}
