package ua.nic.Practica.model;

import javax.persistence.*;

@Entity
@Table(name = "located", schema = "public", catalog = "Practica")
public class LocatedEntity {
    private int id;
    private String country;
    private String region;
    private String district;
    private String street;
    private String houseNumber;
    private Short floor;
    private String roomNumber;

    @Override
    public String toString () {
        return "LocatedEntity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", floor=" + floor +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }

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
    @Column(name = "country", nullable = false, length = 50)
    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "region", nullable = false, length = 50)
    public String getRegion () {
        return region;
    }

    public void setRegion (String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "district", nullable = true, length = 50)
    public String getDistrict () {
        return district;
    }

    public void setDistrict (String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "street", nullable = true, length = 100)
    public String getStreet () {
        return street;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "house_number", nullable = true, length = 10)
    public String getHouseNumber () {
        return houseNumber;
    }

    public void setHouseNumber (String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "floor", nullable = true)
    public Short getFloor () {
        return floor;
    }

    public void setFloor (Short floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "room_number", nullable = true, length = 20)
    public String getRoomNumber () {
        return roomNumber;
    }

    public void setRoomNumber (String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocatedEntity that = (LocatedEntity) o;

        if (id != that.id) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (roomNumber != null ? !roomNumber.equals(that.roomNumber) : that.roomNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        return result;
    }
}
