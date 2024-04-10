package models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import java.util.List;
import util.Jpa;

/**
 * The persistent class for the Shares database table.
 *
 */
@NamedQueries({
	@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s"),
	@NamedQuery(name = "Share.findVideo", query = "SELECT o FROM Share o WHERE o.video.id=:id"),})
@Entity
@Table(name = "Shares")
public class Share implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date shareDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "videoId")
	private Video video;

	public Share() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getShareDate() {
		return this.shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
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

	public static List<Share> getShareVideo(Integer id) {
		EntityManager session = Jpa.getEntityManager();
		TypedQuery<Share> query = session.createNamedQuery("Share.findVideo", Share.class);
		query.setParameter("id", id);
		List<Share> list = query.getResultList();
		session.close();
		return list;
	}
}
