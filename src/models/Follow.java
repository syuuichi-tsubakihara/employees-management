package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follow")
@NamedQueries({
    @NamedQuery(
            name = "getfollows",
            query = "SELECT f FROM Follow AS f WHERE f.follow = :login ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getfollowCount",
            query = "SELECT COUNT(f) FROM Follow AS f"
            ),
    @NamedQuery(
            name = "getAllfollowReport",
            query = "SELECT r FROM Report As r WHERE r.employee IN (SELECT f.follower FROM Follow As f WHERE f.follow = :login)"
            ),
    @NamedQuery(
            name = "getMyfollowReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee IN (SELECT f.follower FROM Follow As f WHERE f.follow = :login)"
            ),
    @NamedQuery(
            name = "dropFollow",
            query = "SELECT f From Follow AS f WHERE f.follower = :femployee AND f.follow = :login"
            )
})

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    private Employee follow;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private Employee follower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFollow() {
        return follow;
    }

    public void setFollow(Employee employee ) {
        this.follow = employee;
    }

    public Employee getFollower() {
        return follower;
    }

    public void setFollower(Employee employee ) {
        this.follower = employee;
    }


}
