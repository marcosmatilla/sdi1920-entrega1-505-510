package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {
	@Query("SELECT i FROM Invitation i WHERE i.id = ?1")
	Invitation findInvitationById(Long id);

	@Query("SELECT i FROM Invitation i WHERE i.receiver.email = ?1")
	Page<Invitation> getInvitationsOf(Pageable pageable, String email);

	@Query("SELECT r FROM Invitation r WHERE r.receiver = ?1 ORDER BY r.id ASC ")
	Page<Invitation> findAllByUser(Pageable pageable, User user);
}
