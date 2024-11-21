package vn.iotstar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
	Optional<UserInfo> findByName(String username);
}
