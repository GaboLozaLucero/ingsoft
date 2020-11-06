package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.dto.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao {
    public Address findByAddressId(Integer addressId);
    public void create(Address address);
}
