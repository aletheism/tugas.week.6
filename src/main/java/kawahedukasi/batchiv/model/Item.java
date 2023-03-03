package kawahedukasi.batchiv.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "itemSequence",sequenceName = "item_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "itemSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id" , nullable = false)
    public Long itemId;

    @Column(name = "name" , nullable = false)
    public String name;

    @Column(name = "count" , nullable = false)
    public Integer count;

    @Column(name = "price" , nullable = false)
    public Double price;

    @Column(name = "type" , nullable = false)
    public String type;

    @Column(name = "description" , nullable = false)
    public String description;

    @Column(name = "created_at" , nullable = false)
    public String createdAt;

    @Column(name = "updated_at" , nullable = false)
    public String updatedAt;

}
