package com.loginapp.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loginapp.users.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository <Role, Integer>{

}
