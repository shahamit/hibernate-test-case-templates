package org.hibernate.lists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shahamit
 */
@Entity
@Table(name = "DS_GENERIC")
@Access(AccessType.FIELD)
public class GenericConnection {

    @Id
    @Column(name = "DS_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DSG_URL")
    private String url;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "DSC_DS_ID", nullable = false)
    @OrderColumn(name = "DSC_ORDER", updatable = false, insertable = false)
    private List<Credentials> credentials = new ArrayList<>(); //If we change this mapping to a Set, everything works fine.

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Credentials> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credentials> credentials) {
        this.credentials = credentials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}