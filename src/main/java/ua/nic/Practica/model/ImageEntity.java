package ua.nic.Practica.model;

import javax.persistence.*;

@Entity
@Table(name = "image", schema = "public", catalog = "Practica")
public class ImageEntity {
    private int id;
    private String expancion;

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
    @Column(name = "expancion", nullable = false, length = 10)
    public String getExpancion () {
        return expancion;
    }

    public void setExpancion (String expancion) {
        this.expancion = expancion;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (id != that.id) return false;
        if (expancion != null ? !expancion.equals(that.expancion) : that.expancion != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (expancion != null ? expancion.hashCode() : 0);
        return result;
    }
}
