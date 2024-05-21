package edu.badpals.quarkusapp.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;


@Entity
@Table(name="t_ordenes")
public class Orden extends PanacheEntityBase {

    public Orden() {}

    public Orden(Usuaria user, Item item) {
        this.user = user;
        this.item = item;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ord_id")
    private long id;

    @OneToOne
    @JoinColumn(name="ord_user")
    private Usuaria user;

    @OneToOne
    @JoinColumn(name="ord_item")
    private Item item;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuaria getUser() {
        return user;
    }

    public void setUser(Usuaria user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", user=" + user +
                ", item=" + item +
                '}';
    }
}
