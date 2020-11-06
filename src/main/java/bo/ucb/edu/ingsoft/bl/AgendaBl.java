package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.AddressDao;
import bo.ucb.edu.ingsoft.dao.ContactDao;
import bo.ucb.edu.ingsoft.dao.PhoneDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaBl {
    private ContactDao contactDao;
    private TransactionDao transactionDao;
    private AddressDao addressDao;
    private PhoneDao phoneDao;

    @Autowired
    public AgendaBl(ContactDao contactDao, TransactionDao transactionDao, AddressDao addressDao, PhoneDao phoneDao) {
        this.contactDao = contactDao;
        this.transactionDao = transactionDao;
        this.addressDao = addressDao;
        this.phoneDao = phoneDao;
    }

    public ContactRequest findContactById(Integer contactId) {
        return  contactDao.findByContactId(contactId);
    }

    public ContactRequest createContact(ContactRequest contactRequest, Transaction transaction, Phone phone, Address address) {
        contactRequest.setTxId(transaction.getTxId());
        contactRequest.setTxUserId(transaction.getTxUserId());
        contactRequest.setTxHost(transaction.getTxHost());
        contactRequest.setTxDate(transaction.getTxDate());
        contactDao.create(contactRequest);
        Integer getLastId = transactionDao.getLastInsertId();
        contactRequest.setContactId(getLastId);

        address.setTxId(transaction.getTxId());
        address.setTxHost(transaction.getTxHost());
        address.setTxUserId(transaction.getTxUserId());
        address.setTxDate(transaction.getTxDate());
        address.setStreet(contactRequest.getStreet());
        address.setCity(contactRequest.getCity());
        address.setCountry(contactRequest.getCountry());
        address.setContactId(contactRequest.getContactId());
        addressDao.create(address);

        phone.setPhoneNumber(contactRequest.getPhoneNumber());
        phone.setContactId(contactRequest.getContactId());
        phone.setTxId(transaction.getTxId());
        phone.setTxHost(transaction.getTxHost());
        phone.setTxUserId(transaction.getTxUserId());
        phone.setTxDate(transaction.getTxDate());
        phoneDao.create(phone);

        return contactRequest;
    }

}
