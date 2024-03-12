import java.util.List;

import dao.CustomerDAO;
import utility.PasswordHasher;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public CustomerDTO getCustomerById(long id) {
        return customerDAO.get(id);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAll();
    }

    public void createCustomer(CustomerDTO customerDTO, String password) {
        // Hash the password
        String hashedPassword = PasswordHasher.hashPassword(password);
        // Set the hashed password in the DTO
        customerDTO.setPasswordHash(hashedPassword);
        // Persist the customer DTO
        customerDAO.save(customerDTO);
    }

    public boolean updateCustomerPassword(long customerId, String newPassword) {
        CustomerDTO customer = customerDAO.get(customerId);
        if (customer != null) {
            // Hash the new password
            String newHashedPassword = PasswordHasher.hashPassword(newPassword);
            // Update the customer with the new hashed password
            customer.setPasswordHash(newHashedPassword);
            customerDAO.update(customer, new String[]{newHashedPassword});
            return true;
        }
        return false;
    }

    public void deleteCustomer(long id) {
        CustomerDTO customer = customerDAO.get(id);
        if (customer != null) {
            customerDAO.delete(customer);
        }
    }
}
