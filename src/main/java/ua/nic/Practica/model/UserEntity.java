package ua.nic.Practica.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user", schema = "public", catalog = "Practica")
public class UserEntity {
    private int id;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private Date dateOfBirthday;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name", nullable = false, length = 30)
    public String getSecondName () {
        return secondName;
    }

    public void setSecondName (String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin () {
        return login;
    }

    public void setLogin (String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "date_of_birthday", nullable = true)
    public Date getDateOfBirthday () {
        return dateOfBirthday;
    }

    public void setDateOfBirthday (Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (dateOfBirthday != null ? !dateOfBirthday.equals(that.dateOfBirthday) : that.dateOfBirthday != null)
            return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateOfBirthday != null ? dateOfBirthday.hashCode() : 0);
        return result;
    }
}
