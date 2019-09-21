package ua.nic.Practica.model;

import javax.persistence.*;

@Entity
@Table(name = "customer", schema = "public", catalog = "Practica")
public class CustomerEntity {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String message;
    private Integer tradingFloorId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 40)
    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 15)
    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 1500)
    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "trading_floor_id", nullable = true)
    public Integer getTradingFloorId () {
        return tradingFloorId;
    }

    public void setTradingFloorId (Integer tradingFloorId) {
        this.tradingFloorId = tradingFloorId;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (tradingFloorId != null ? !tradingFloorId.equals(that.tradingFloorId) : that.tradingFloorId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (tradingFloorId != null ? tradingFloorId.hashCode() : 0);
        return result;
    }
}
