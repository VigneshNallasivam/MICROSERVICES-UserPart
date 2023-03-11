package com.spring.userpart.service;

import com.spring.userpart.dto.LoginDTO;
import com.spring.userpart.dto.UserDTO;
import com.spring.userpart.exception.UserException;
import com.spring.userpart.model.UserModel;
import com.spring.userpart.repository.UserRepository;
import com.spring.userpart.utility.EmailSenderService;
import com.spring.userpart.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService
{
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    UserRepository userRepo;
    @Override
    public UserModel register(UserDTO userDTO) throws Exception
    {
        UserModel user=new UserModel(userDTO);
        String token=tokenUtil.createToken(user.getUserId());
        userRepo.save(user);
        emailSenderService.sendEmail(user.getEmail(),"Register Book Store", "Hai...."+user.getFirstName()+"\n You have been successfully registered!\n\n Your registered details are:\n\n User Id:  "+user.getUserId()+"\n First Name:  "+user.getFirstName()+"\n Last Name:  "+user.getLastName()+"\n Email:  "+user.getEmail()+"\n Address:  "+user.getAddress()+"\n DOB:  "+user.getDob()+"\n Token:  "+token);
        return user;
    }
    @Override
    public List<UserModel> getAll()
    {
        List<UserModel> list=userRepo.findAll();
        return list;
    }

    @Override
    public Optional<UserModel> getById(long id){
        Optional<UserModel> user=userRepo.findById(id);
        if (user.isPresent()){
            return user;
        }else {
            throw new UserException("Sorry! We cannot find the user id: "+id);
        }
    }
    @Override
    public UserModel getByEmailID(String email){
        UserModel userList=userRepo.findByEmail(email)  ;
        if(userList!=null){
            return userList;

        }else {
            throw new UserException("Sorry! We can not find the user email: "+email);
        }
    }
    @Override
    public UserModel forgotPassword(UserDTO userDTO, String email){
        UserModel user=userRepo.findByEmail(email);
        if (user!=null){
            user.setPassword(userDTO.getPassword());
            userRepo.save(user);
            emailSenderService.sendEmail(user.getEmail(),"Password Updated", "Hii...."+user.getFirstName()+" Your Password has been updated!\n\n Your New password: "+user.getPassword());
            return user;
        }else {
            throw new UserException("Sorry! We can not find the user email: "+email);
        }
    }
    @Override
    public UserModel updateUserByEmail(UserDTO userDTO, String email){
        UserModel user=userRepo.findByEmail(email);
        if(userRepo.findByEmail(email)!=null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            user.setDob(userDTO.getDob());
            user.setPassword(userDTO.getPassword());
            userRepo.save(user);
            emailSenderService.sendEmail(user.getEmail(), "Your details are updated!", "Hii...." + user.getFirstName() + " Your updated details are:\n\n First Name:  " + user.getFirstName() + "\n Last Name:  " + user.getLastName() + "\n Email:  " + user.getEmail() + "\n Address:  " + user.getAddress() + "\n DOB:  " + user.getDob());
            return user;
        }else {
            throw new UserException("Sorry! We can not find entered email: "+email);
        }
    }
    @Override
    public String login(LoginDTO loginDTO){
        Optional<UserModel> user= Optional.ofNullable(userRepo.findByEmail(loginDTO.getEmail()));
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword()) ){
            emailSenderService.sendEmail(user.get().getEmail(),"Logged in Successfully!", "Hii...."+user.get().getFirstName()+"\n\n You have successfully logged in into Book Store App!");
            return "Congratulations!! You have logged in successfully!";
        }else {
            throw new UserException("Sorry! Email or Password is incorrect!");
        }
    }
    @Override
    public String deleteById(long id)
    {
        if(userRepo.findById(id).isPresent())
        {
            userRepo.deleteById(id);
            return "Deleted!!";
        }
        else
        {
            throw new UserException("Id is not found to be Deleted!!");
        }
    }
    @Override
    public UserModel changePassword(LoginDTO loginDTO, String email)
    {
        UserModel userModel = userRepo.findByEmail(email);
        if(userRepo.findByEmail(email)!=null)
        {
            userModel.setPassword(loginDTO.getPassword());
            userRepo.save(userModel);
            return userModel;
        }
        else
        {
            throw new UserException("Email-ID Not found!!");

        }
    }
    @Override
    public UserModel updateById(UserDTO userDTO, long id)
    {
        UserModel userModel = userRepo.findById(id).get();
        if(userRepo.findById(id).isPresent())
        {
            userModel.setUserId(userDTO.getUserId());
            userModel.setFirstName(userDTO.getFirstName());
            userModel.setLastName(userDTO.getLastName());
            userModel.setEmail(userModel.getEmail());
            userModel.setAddress(userModel.getAddress());
            userModel.setDob(userModel.getDob());
            userModel.setPassword(userModel.getPassword());
            userRepo.save(userModel);
            return userModel;
        }
        else {
            throw new UserException("Entered id is not found!!");
        }

    }
}