package ua.nic.Practica.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subscriber", schema = "public", catalog = "Practica")
public class SubscriberEntity {
    private int id;
    private Date date;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @Override
    public String toString () {
        return "SubscriberEntity{" +
                "id=" + id +
                ", date=" + date +
                ", email='" + email + '\'' +
                '}';
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriberEntity that = (SubscriberEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
