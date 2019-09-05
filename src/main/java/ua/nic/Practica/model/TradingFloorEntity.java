package ua.nic.Practica.model;

import javax.persistence.*;

@Entity
@Table(name = "trading_floor", schema = "public", catalog = "Practica")
public class TradingFloorEntity {
    private int id;
    private String mainName;
    private String description;
    private int locatedId;
    private double price;
    private Short discont;
    private Double numberOfSquareMeters;

    @Override
    public String toString () {
        return "TradingFloorEntity{" +
                "id=" + id +
                ", mainName='" + mainName + '\'' +
                ", description='" + description + '\'' +
                ", locatedId=" + locatedId +
                ", price=" + price +
                ", discont=" + discont +
                ", numberOfSquareMeters=" + numberOfSquareMeters +
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
    @Column(name = "main_name", nullable = false, length = 200)
    public String getMainName () {
        return mainName;
    }

    public void setMainName (String mainName) {
        this.mainName = mainName;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "located_id", nullable = false)
    public int getLocatedId () {
        return locatedId;
    }

    public void setLocatedId (int locatedId) {
        this.locatedId = locatedId;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "discont", nullable = true)
    public Short getDiscont () {
        return discont;
    }

    public void setDiscont (Short discont) {
        this.discont = discont;
    }

    @Basic
    @Column(name = "number_of_square_meters", nullable = true, precision = 0)
    public Double getNumberOfSquareMeters () {
        return numberOfSquareMeters;
    }

    public void setNumberOfSquareMeters (Double numberOfSquareMeters) {
        this.numberOfSquareMeters = numberOfSquareMeters;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradingFloorEntity that = (TradingFloorEntity) o;

        if (id != that.id) return false;
        if (locatedId != that.locatedId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (mainName != null ? !mainName.equals(that.mainName) : that.mainName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (discont != null ? !discont.equals(that.discont) : that.discont != null) return false;
        if (numberOfSquareMeters != null ? !numberOfSquareMeters.equals(that.numberOfSquareMeters) : that.numberOfSquareMeters != null)
            return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result;
        long temp;
        result = id;
        result = 31 * result + (mainName != null ? mainName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + locatedId;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (discont != null ? discont.hashCode() : 0);
        result = 31 * result + (numberOfSquareMeters != null ? numberOfSquareMeters.hashCode() : 0);
        return result;
    }
}
