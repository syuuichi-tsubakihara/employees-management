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
            name = "getAllfollow",
            query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getfollowCount",
            query = "SELECT COUNT(f) FROM Follow AS f"
            ),
    @NamedQuery(
            name = "getAllfollowReport",
            query = "SELECT f FROM Follow AS f WHERE f.employee = :employee ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getMyfollowReportsCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.employee = :employee"
            )
})

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setReport(Report report) {
        this.report = report;

    }


}