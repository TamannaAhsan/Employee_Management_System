package com.example.office_assistant_personal.service.user;

import com.example.office_assistant_personal.DTO.UserDTO;
import com.example.office_assistant_personal.DTO.mapper.AutoUserMapper;
import com.example.office_assistant_personal.config.security.JwtService;
import com.example.office_assistant_personal.entity.company.Company;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.helper.AuthorityHelper;
import com.example.office_assistant_personal.repository.company.CompanyRepository;
import com.example.office_assistant_personal.repository.user.UserRepository;
import com.example.office_assistant_personal.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.office_assistant_personal.utils.Utility.isNotNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final JwtService jwtService;

    private final AuthorityHelper authorityHelper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO create(UserDTO userDTO) throws ApiSystemException, ApiNotAcceptableException {
        try {
            logger.info("Save Admin Information");
            if (isNotNull(userDTO.getId())) {
                throw new ApiNotAcceptableException("user.already.exist");
            }

            if(userRepository.existsUserByEmail(userDTO.getEmail())){
                throw new ApiNotAcceptableException("user.email.already.exist");

            }

            Company company = companyRepository.findById(userDTO.getCompany().getId())
                    .orElseThrow(()->new ApiSystemException("Company is not found"));
            userDTO.setCreatedAt(LocalDateTime.now());

            User user = AutoUserMapper.MAPPER.mapToUser(userDTO);
            user.setCompany(company);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRoleStatus(userDTO.getRoleStatus());

            var jwtToken = jwtService.generateToken(user);
            user.setToken(jwtToken);

            Date expirationDate = jwtService.extractExpiration(jwtToken);
            LocalDate expireDate = LocalDate.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault());
            user.setTokenExpireDate(expireDate);

            User savedUser = userRepository.save(user);
            UserDTO savedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);
            logger.info("Save Admin Information");
            return savedUserDTO;
        }catch (ApiNotAcceptableException e) {
            throw new ApiNotAcceptableException("user.email.already.exist");
        } catch (Exception e) {
            throw new ApiSystemException("user.not.created");
        }
    }
    @Override
    public UserDTO createAdmin(UserDTO userDTO) throws ApiSystemException, ApiNotAcceptableException  {
        try {
            logger.info("Save Admin Information");
            if (isNotNull(userDTO.getId())) {
                throw new ApiNotAcceptableException("user.already.exist");
            }

            if(userRepository.existsUserByEmail(userDTO.getEmail())){
                throw new ApiNotAcceptableException("user.email.already.exist");

            }

            Company company = companyRepository.findById(userDTO.getCompany().getId())
                    .orElseThrow(()->new ApiSystemException("Company is not found"));
            userDTO.setCreatedAt(LocalDateTime.now());

            User user = AutoUserMapper.MAPPER.mapToUser(userDTO);
            user.setCompany(company);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRoleStatus(userDTO.getRoleStatus());

            var jwtToken = jwtService.generateToken(user);
            user.setToken(jwtToken);

            Date expirationDate = jwtService.extractExpiration(jwtToken);
            LocalDate expireDate = LocalDate.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault());
            user.setTokenExpireDate(expireDate);

            User savedUser = userRepository.save(user);
            UserDTO savedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);
            logger.info("Save Admin Information");
            return savedUserDTO;
        }catch (ApiNotAcceptableException e) {
            throw new ApiNotAcceptableException("user.email.already.exist");
        } catch (Exception e) {
            throw new ApiSystemException("user.not.created");
        }

    }


    @Override
    public UserDTO update(UserDTO userDTO) throws ApiSystemException {
        try {
            User existingUser = userRepository.findById(userDTO.getId().longValue())
                    .orElseThrow(()->new ApiSystemException("User is not found"));
            Company company = companyRepository.findById(existingUser.getCompany().getId())
                    .orElseThrow(()->new RuntimeException("Company is not found"));
            userDTO.setUpdatedAt(LocalDateTime.now());

            User user = AutoUserMapper.MAPPER.mapToUser(userDTO);
            user.setCompany(company);
            user.setRoleStatus(userDTO.getRoleStatus());
            user.setAddress(userDTO.getAddress());
            user.setContact(userDTO.getContact());
            user.setEmail(userDTO.getEmail());
            user.setDesignation(userDTO.getDesignation());
            user.setReligion(userDTO.getReligion());
            user.setFirstname(userDTO.getFirstname());
            user.setLastname(userDTO.getLastname());
            User updatedUser = userRepository.save(user);
            UserDTO updatedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(updatedUser);

            logger.info("Update User Information");
            return updatedUserDTO;

        }catch (Exception e) {
            throw new ApiSystemException("user.not.updated");
        }
    }

    @Override
    public UserDTO resetPassword(Long id, String password) throws ApiSystemException {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(()->new ApiSystemException("User is not found"));
            existingUser.setPassword(password);
            UserDTO userDTO = AutoUserMapper.MAPPER.mapToUserDTO(existingUser);
            logger.info("Update Password Information");
            return userDTO;
        }catch (Exception e) {
            throw new ApiSystemException("user.password.not.updated");
        }
    }



    @Override
    public void delete(Long id) throws ApiSystemException {
        try {
            Optional<User> loggedInUser = authorityHelper.getCurrentLoggedInUser();
            if(id.equals(loggedInUser))
                new ApiSystemException("Applicant and current logged in users are not same");
            logger.info("Delete User Information");
            userRepository.deleteById(id);
        }catch (Exception e) {
            throw new ApiSystemException("user.not.deleted");
        }
    }

    @Override
    public List<UserDTO> getAll() {
       List<User> userList = userRepository.findAll();
       return userList.stream()
               .map(element-> AutoUserMapper.MAPPER.mapToUserDTO(element))
               .collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(Long id) throws ApiSystemException {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(()-> new ApiSystemException("User is not found"));
            if(id.equals(authorityHelper.getCurrentLoggedInUser()))
                new ApiSystemException("Applicant and current logged in users are not same");
            UserDTO userDTO = AutoUserMapper.MAPPER.mapToUserDTO(user);
            return userDTO;

        }catch (Exception e) {
            throw new ApiSystemException("user.not.found");
        }
    }

    @Override
    public User updateUser(User user) throws ApiSystemException, ApiNotAcceptableException {
        try {
            logger.info("Update User Information");
            //users.setPassword(encoder.encode(users.getPassword()));

            User saveUser = userRepository.findById(user.getId()).get();
            Utility.copyNonListAndIdAndCreatedDateProperties(user, saveUser);
            User updatedUser = userRepository.save(saveUser);
            logger.info("Update User Information");
            return updatedUser;
        } catch (DataIntegrityViolationException e) {
            throw new ApiNotAcceptableException("user.email.already.exist");
        } catch (Exception e) {
            throw new ApiSystemException("user.not.updated");
        }
    }
}
