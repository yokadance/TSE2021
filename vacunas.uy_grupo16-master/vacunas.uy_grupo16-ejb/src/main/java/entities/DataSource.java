package entities;

import DTO.DTDataSource;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class DataSource extends BaseModel{

    private String name;
    private String url;
    @OneToOne (fetch = FetchType.EAGER)
    private Restriction restriction;

    public DataSource(){
        super();
    }

    public DataSource(Long id, Date createDate, Date updateDate, Date deleteDate, String user, String name, String url, Restriction restriction) {
        super(id, createDate, updateDate, deleteDate, user);
        this.name = name;
        this.url = url;
        this.restriction = restriction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }


    public DTDataSource getDTDataSource(){
            ModelMapper modelMapper = new ModelMapper();
            DTDataSource dt = modelMapper.map(this, DTDataSource.class);
            return dt;
    }
}
