package com.globits.da.utils.validation.excel;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.utils.validation.exception.IllegalRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class ExcelValidator {

    @Autowired
    private CommuneRepository communeRepo;

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public static boolean validateEmail(String email, List<String> errorMessages, int rowNum) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null || !pattern.matcher(email).matches()) {
            errorMessages.add("Invalid email format at element " + rowNum);
            return false;
        }
        return true;
    }

    public static boolean validatePhone(String phone, List<String> errorMessages, int rowNum) {
        if (phone == null || !phone.matches("^[0-9]{1,11}$")) {
            errorMessages.add("Invalid phone format at element " + rowNum);
            return false;
        }
        return true;
    }

    public static boolean validateAge(int age, List<String> errorMessages, int rowNum) {
        if (age < 0) {
            errorMessages.add("Invalid age at element " + rowNum + ": must be non-negative");
            return false;
        }
        return true;
    }

}


