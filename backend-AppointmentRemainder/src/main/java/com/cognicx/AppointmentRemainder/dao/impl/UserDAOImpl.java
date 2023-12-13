package com.cognicx.AppointmentRemainder.dao.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognicx.AppointmentRemainder.dao.UserDAO;
import com.cognicx.AppointmentRemainder.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private Logger logger = Logger.getLogger(UserDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Optional<User> findByUsername(String username) throws Exception {
		StringBuilder sqlQry = null;
		Optional<User> userOptional = null;

		List<Object[]> result = null;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: findByUsername() : " + e);
		} finally {
		}
		return userOptional;
	}

	@Override
	public Boolean existsByUsername(String username) throws Exception {
		StringBuilder sqlQry = null;
		boolean user = false;
		List<Object[]> result = null;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByUsername() : " + e);
		} finally {
		}
		return user;
	}

	@Override
	public Boolean existsByEmail(String email) throws Exception {
		StringBuilder sqlQry = null;
		boolean user = false;
		List<Object[]> result = null;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByEmail() : " + e);
		} finally {
		}
		return user;
	}

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User save(User user) throws Exception {
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
		}

		return user;
	}

}