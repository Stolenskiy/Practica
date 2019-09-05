package ua.nic.Practica.model;

import javax.persistence.*;

@Entity
@Table(name = "images", schema = "public", catalog = "Practica")
public class ImagesEntity {
    private int id;
    private Integer tradingFloorId;
    private Integer imageId;

    @Override
    public String toString () {
        return "ImagesEntity{" +
                "id=" + id +
                ", trainingFloorId=" + tradingFloorId +
                ", imageId=" + imageId +
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
    @Column(name = "trading_floor_id", nullable = true)
    public Integer getTradingFloorId () {
        return tradingFloorId;
    }

    public void setTradingFloorId (Integer trainingFloorId) {
        this.tradingFloorId = trainingFloorId;
    }

    @Basic
    @Column(name = "image_id", nullable = true)
    public Integer getImageId () {
        return imageId;
    }

    public void setImageId (Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagesEntity that = (ImagesEntity) o;

        if (id != that.id) return false;
        if (tradingFloorId != null ? !tradingFloorId.equals(that.tradingFloorId) : that.tradingFloorId != null)
            return false;
        if (imageId != null ? !imageId.equals(that.imageId) : that.imageId != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (tradingFloorId != null ? tradingFloorId.hashCode() : 0);
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        return result;
    }
}
