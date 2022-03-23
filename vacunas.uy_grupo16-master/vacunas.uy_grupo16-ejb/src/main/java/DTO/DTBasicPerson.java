package DTO;

import enumerations.Sex;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTBasicPerson extends DTBaseModel implements Serializable {
    private String idUruguay;
    private String ci;
    private Sex sex;
    private Date birthday;
    private String email;
    private List<String> roles;

    public DTBasicPerson() {
        super();
    }

    public DTBasicPerson(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, String idUruguay, String ci, Sex sex, Date birthday, String email, List<String> roles) {
        super(id, createDate, updateDate, deleteDate, userid);
        this.idUruguay = idUruguay;
        this.ci = ci;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.roles = roles;
    }

    public DTBasicPerson(String idUruguay, String ci, Sex sex, Date birthday, String email,List<String> roles ) {
    super();
    this.idUruguay = idUruguay;
    this.ci = ci;
    this.sex = sex;
    this.birthday = birthday;
    this.email = email;
    this.roles = roles;
   }

    public String getIdUruguay() {
        return idUruguay;
    }

    public void setIdUruguay(String idUruguay) {
        this.idUruguay = idUruguay;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
