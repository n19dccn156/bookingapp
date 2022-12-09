package com.thanhsang.bookingapp.Repositories.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.User.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    
    public Optional<UserModel> findById(Long id);

    public Optional<UserModel> findByUsernameAndPassword(String username, String password);

    // @Query(value = "SELECT * FROM user where email = ?1 or username = ?2", nativeQuery = true)
    public List<UserModel> findAllByIdOrEmailOrUsername(Long id, String email, String username);

    @Query(value = "SELECT * FROM user where role_id = ?1", nativeQuery = true)
    public Page<UserModel> findAllByRoleId(String role_id, Pageable pageable);


}
