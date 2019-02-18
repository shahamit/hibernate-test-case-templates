package org.hibernate.lists;

import javax.persistence.*;

/**
 * @author shahamit
 */
@Entity
@Table(name = "DS_CREDENTIALS")
public class Credentials {

    @Id
    @Column(name = "DSC_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DSC_USER_ID")
    private String credId;

    @Column(name = "DSC_PASSWORD")
    private String encryptedPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCredId() {
        return credId;
    }

    public void setCredId(String credId) {
        this.credId = credId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}