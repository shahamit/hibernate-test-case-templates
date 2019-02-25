package org.hibernate.lists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @OrderColumn(name = "DSC_ORDER", insertable = false, updatable = false) //removing the insertable & updatable properties resolves the error
    private List<Credentials> credentials = new ArrayList<>(); //If we change this mapping to a Set, everything works fine.

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PARAMETERS",
            joinColumns = @JoinColumn(name = "PARAMETER_ID"))
    @MapKeyColumn(name = "PARAMETER_NAME", insertable = false, updatable = false) //removing the insertable & updatable properties resolves the error
    @Column(name = "PARAMETER_VALUE")
    private Map<String, String> parameters;

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

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
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