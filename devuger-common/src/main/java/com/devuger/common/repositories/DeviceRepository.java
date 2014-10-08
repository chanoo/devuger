package com.devuger.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Device;
import com.devuger.common.entities.User;
import com.devuger.common.support.code.DeviceOs;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
	
	Device findByCreatedByAndOs(User user, DeviceOs os);

	Device findByToken(String token);

	List<Device> findByOs(DeviceOs os);
}