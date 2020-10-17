package org.hibernate.envers.bugs;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Audited
@Entity
@Table(name = "foo")
public class Foo implements Serializable {
    @Id @Column(name = "foo_id") private String fooId;
    @ElementCollection
    @Column(name = "bar_id")
    @CollectionTable(
            name = "foos_bars_mapping",
            joinColumns = {
                    @JoinColumn(name = "bazId", referencedColumnName = "baz_id"),
                    @JoinColumn(name = "fooId", referencedColumnName = "foo_id")
            }
    )
    private Set<String> barIds;
    @Column(name = "baz_id")
    private String bazId;

    public Foo(String fooId, Set<String> barIds, String bazId) {
        this.fooId = fooId;
        this.barIds = barIds;
        this.bazId = bazId;
    }
}
