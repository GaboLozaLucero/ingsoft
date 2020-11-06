package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.dto.ContactRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactDao {
    public ContactRequest findByContactId(Integer contactId);
    public void create(ContactRequest contactRequest);
}
