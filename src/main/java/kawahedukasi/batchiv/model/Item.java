package kawahedukasi.batchiv.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.apache.poi.ss.usermodel.Cell;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
public class Item extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "itemSequence",sequenceName = "item_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "itemSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id" , nullable = false)
    public Long itemId;

    @Column(name = "name")
    public String name;

    @Column(name = "count")
    public Integer count;

    @Column(name = "price")
    public Double price;

    @Column(name = "type")
    public String type;

    @Column(name = "description")
    public String description;

    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
